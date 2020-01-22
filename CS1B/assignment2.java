import java.util.Random;
import java.util.Scanner; 

public class Foothill
{
   public static void main(String[] args)
   {
      //---------- phase 1 ------------
      Deck deck = new Deck(2);
      dealAll(deck);
      
      deck = new Deck(2);
      deck.shuffle();
      dealAll(deck);

      deck = new Deck(1);
      dealAll(deck);
      
      deck = new Deck(1);
      deck.shuffle();
      dealAll(deck);

      //---------- phase 2 ------------
      Scanner scanner = new Scanner(System.in);
      int players = 0;
      while (players < 1 || players > 10) {
         System.out.println("How many hands? (1 - 10, please): ");
         if (scanner.hasNext()) {
            players = scanner.nextInt();
         }
      }
      
      // Unshuffle before dealing
      deck = new Deck(1);
      Hand[] hands = new Hand[players];
      for (int i = 0; i < players; ++i) {
         hands[i] = new Hand();
      }
      for (int i = 0; ; ++i) {
         Card card = deck.dealCard();
         if (card != null) {
            hands[i%players].takeCard(card);
         } else {
            break;
         }
      }
      System.out.println("Here are our hands, from unshuffled deck:");
      for (int i = 0; i < players; ++i) {
         System.out.println(hands[i].toString());
      }
      
      // Shuffle before dealing
      deck = new Deck(1);
      deck.shuffle();
      hands = new Hand[players];
      for (int i = 0; i < players; ++i) {
         hands[i] = new Hand();
      }
      for (int i = 0; ; ++i) {
         Card card = deck.dealCard();
         if (card != null) {
            hands[i%players].takeCard(card);
         } else {
            break;
         }
      }
      System.out.println("Here are our hands, from SHUFFLED deck:");
      for (int i = 0; i < players; ++i) {
         System.out.println(hands[i].toString());
      }
   }
   
   private static void dealAll(Deck deck) {
      while (true) {
         Card card = deck.dealCard();
         if (card != null) {
            System.out.print(card.toString() + " / ");
         } else {
            break;
         }
      }
      System.out.println();
   }
}

class Deck {
   private static final int MAX_PACKS = 6;
   private static final int NUM_CARDS_PER_PACK = 52;
   private static final int MAX_CARDS_PER_DECK = MAX_PACKS * NUM_CARDS_PER_PACK;
   
   private static Card[] masterPack;
   
   private Card[] cards;
   private int topCard;
   private int numPacks;
   
   public Deck() {
      this(1);
   }
   
   public Deck(int numPacks) {
      allocateMasterPack();
      initializePack(numPacks);
   }
   
   public boolean initializePack(int numPacks) {
      if (numPacks > MAX_PACKS || numPacks < 0) {
         return false;
      }
      cards = new Card[numPacks * NUM_CARDS_PER_PACK];
      topCard = 0;
      for (int i = 0; i < numPacks; ++i) {
         for (int j = 0; j < NUM_CARDS_PER_PACK; ++j) {
            cards[topCard++] = masterPack[j];
         }
      }
      return true;
   }
   
   public void shuffle() {
      Random rand = new Random();
      for (int i = 0; i < topCard; ++i) {
         int j = rand.nextInt(topCard);
         Card tmp = cards[i];
         cards[i] = cards[j];
         cards[j] = tmp;
      }
   }
   
   public Card dealCard() {
      if (topCard == 0) {
         return null;
      }
      return new Card(cards[--topCard]);
   }
   
   public int getTopCard() {
      return topCard;
   }
   
   public Card inspectCard(int k) {
      if (k < 0 || k >= topCard) {
         return new Card('0');
      }
      return new Card(cards[k]);
   }
   
   private static void allocateMasterPack() {
      if (masterPack != null) {
         return;
      }
      masterPack = new Card[NUM_CARDS_PER_PACK];
      for (int i = 0; i < NUM_CARDS_PER_PACK; ++i) {
         masterPack[i] = new Card();
      }
      for (int k = 0; k < 4; k++)
      {
         Card.Suit st;
         // set the suit for this loop pass
         switch(k)
         {
         case 0:
            st = Card.Suit.clubs; break;
         case 1:
            st = Card.Suit.diamonds; break;
         case 2:
            st = Card.Suit.hearts; break;
         case 3:
            st = Card.Suit.spades; break;
         default:
            // should not happen but ...
            st = Card.Suit.spades; break;
        }

         int j;
         char val;
         // now set all the values for this suit
         for (val = '2', j = 0; val <= '9'; val++, j++) {
            masterPack[13 * k + j].set(val, st);
         }
         masterPack[13 * k + 8].set('T', st);
         masterPack[13 * k + 9].set('J', st);
         masterPack[13 * k + 10].set('Q', st);
         masterPack[13 * k + 11].set('K', st);
         masterPack[13 * k + 12].set('A', st);
      }
   }
}

class Hand
{
   private static final int MAX_CARDS = 30;
   private Card[] myCards;
   private int numCards;

   public Hand() {
      resetHand();
   }

   public void resetHand() {
         myCards = new Card[MAX_CARDS];
         numCards = 0;
      }

   public boolean takeCard(Card card)
   {
      if (numCards >= MAX_CARDS)
      {
         return false;
      }
      if (!card.getCardError()) {
          myCards[numCards] = new Card(card);
          numCards++;
      }
      return true;
   }

   public String toString()
   {
      StringBuffer sb = new StringBuffer();
      sb.append("Hand = ( ");
      for (int i = 0; i < numCards; i++) {
         sb.append(myCards[i].toString());
         if (i+1 < numCards) {
            sb.append(',');
         }
      }
      sb.append(" )");
      return sb.toString();
   }

   public Card playCard()
   {
       if (numCards == 0) {
           return null;
       }
      numCards--;
      return myCards[numCards];

   }

   public Card inspectCard(int k)
   {
      if (k >= numCards)
      {
         return new Card('0');
      }
      return myCards[k];
   }
}

class Card
{
   public enum Suit
   {
      clubs, diamonds, hearts, spades
   }

   // private data
   private char value;
   private Suit suit;
   private boolean cardError;

   // 4 overloaded constructors
   public Card(char value, Suit suit)
   {
      set(value, suit);
   }

   public Card(char value)
   {
      this(value, Suit.spades);
   }

   public Card()
   {
      this('A', Suit.spades);
   }
   
   public Card(Card card)
   {
      this(card.getVal(), card.getSuit());
   }

   // mutator
   public boolean set(char value, Suit suit)
   {
      char upVal; // for upcasing char
      cardError = false; // return value
      upVal = Character.toUpperCase(value);
      // suit is enum, so no test needed: all suits allowed
      if (isValid(upVal, suit))
      {
         this.suit = suit;
         this.value = upVal;
      } else
      {
         cardError = true;
      }
      return !cardError;
   }

   private static boolean isValid(char value, Suit suit)
   {
      boolean valid = true;
      if (suit == Suit.clubs || suit == Suit.diamonds || suit == Suit.hearts || suit == Suit.spades)
      {
      } else
      {
         valid = false;
      }

      if (value == 'A' || value == 'K' || value == 'Q' || value == 'J' || value == 'T'
            || (value >= '2' && value <= '9'))
      {
      } else
      {
         valid = false;
      }
      return valid;

   }

   // accessors
   public char getVal()
   {
      return value;
   }

   public Suit getSuit()
   {
      return suit;
   }

   public boolean getCardError() {
      return cardError;
   }
   
   // stringizer
   public String toString()
   {

      String retVal;
      if (cardError == true)
      {
         return "invalid";
      } else
      {
         // convert from char to String
         retVal = String.valueOf(value);

         if (suit == Suit.spades)
            retVal += " of Spades";
         else if (suit == Suit.hearts)
            retVal += " of Hearts";
         else if (suit == Suit.diamonds)
            retVal += " of Diamonds";
         else if (suit == Suit.clubs)
            retVal += " of Clubs";

         return retVal;
      }
   }
}
