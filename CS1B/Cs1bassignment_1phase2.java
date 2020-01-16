package assigment_1;

public class Cs1bassignment_1phase2
{

   public static void main(String[] args)
   {
      Card card1, card2, card3;
      card1 = new Card('3', Card.Suit.clubs);
      card2 = new Card('T', Card.Suit.clubs);
      card3 = new Card('9', Card.Suit.hearts);
      Hand hand1 = new Hand();
      
      for (int i = 0; ; ++i) {
         boolean succeeded = false;
         if(i%3==0) {
            succeeded = hand1.takeCard(card1);
         }else if(i%3==1) {
            succeeded = hand1.takeCard(card2);
         }else if(i%3==2) {
            succeeded = hand1.takeCard(card3);
         }
         if (!succeeded) {
            break;
         }
      }
      System.out.println("Hand full\nAfter deal");
      System.out.println("Hand = ( " + hand1.toString() + " )");
      
      System.out.println("Testing inspectCard(10)");
      Card inspectedCard = hand1.inspectCard(10);
      if (inspectedCard != null) {
         System.out.println(inspectedCard.toString());
      }
      
      while(true)
      {
         Card playingCard = hand1.playCard();
         if (playingCard == null) {
            break;
         }
         System.out.println("Playing " + playingCard.toString());
      }
      
      System.out.println("Testing inspectCard(10)");
      inspectedCard = hand1.inspectCard(10);
      if (inspectedCard != null) {
         System.out.println(inspectedCard.toString());
      }
      
      System.out.println("After playing all cards");
      System.out.println("Hand = ( " + hand1.toString() + " )");
   }
}

class Hand
{
   static final int MAX_CARDS = 30;
   private Card[] myCards;
   private int numCards;

   // don't know how to do
   public Hand() {
      resetHand();
   }

   // don't know how to do
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
      if (Card.isValid(card.getVal(), card.getSuit())) {
          myCards[numCards] = card;
          numCards++;
      }
      return true;
   }

   // don't know how to do
   public String toString()
   {
      StringBuffer sb = new StringBuffer();
      for (int i = 0; i < numCards; i++) {
         sb.append(myCards[i].toString());
         if (i+1 < numCards) {
            sb.append(',');
         }
      }
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
      boolean cardError = false;
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

   static boolean isValid(char value, Suit suit)
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
