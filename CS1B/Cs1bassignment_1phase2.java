package assigment_1;

import Card.Suit;

public class Cs1bassignment_1phase2
{

   public static void main(String[] args)
   {
      Card card1, card2, card3;
      card1 = new Card('3', Card.Suit.clubs);
      card2 = new Card('T', Card.Suit.clubs);
      card3 = new Card('9', Card.Suit.hearts);
      Hand hand1 = new Hand();
      
      for (int a = 0;a <= Hand.MAX_CARDS;a++ ) {
         if(a%3==0) {
            hand1.takeCard(card1);
         }if(a%3==1) {
            hand1.takeCard(card2);
         }if(a%3==2) {
            hand1.takeCard(card3);
         }
      }
      System.out.println(hand1.toString())
   }while(hand1.numCards>=0)

   {
      hand1.numCards--;
      hand1.playCard()
      System.out.println(myCards[numCards]);
      
     if( !inspectCard(numCards)) {
        System.out.println("After playing all cards" + 
              "\n Hand =  (  )");
     }else {
       hand1.void resetHand();
}}

class Hand
{
   static int MAX_CARDS = 30;
   private Card[] myCards;
   private int numCards;

   // don't know how to do
   public Hand() {
         myCards[numCards];
      }

   // don't know how to do
   public void resetHand() {
         myCards[]= myCards[0]
      }

   public boolean takeCard(Card card)
   {
      if (numCards >= MAX_CARDS)
      {
         return false;
      }
      myCards[numCards] = card;
      numCards++;
      return true;
   }

   // don't know how to do
   String toString()
   {

      for (int i = 0; i < MAX_CARDS - 1; i++)
         System.out.println(myCards[i] + ",");

   }

   Card playCard()
   {
      numCards--;
      return myCards[numCards];

   }

   boolean inspectCard(int k)
   {
      boolean cardError = false;
      if (k > 0)
      {
         cardError = true;
      }
      return cardError;
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
