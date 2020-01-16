public class cs1bassignment_1
{
   public static void main(String[] args)
   {
      Card card1, card2, card3;

      card1 = new Card();
      card2 = new Card('1');
      card3 = new Card('j', Card.Suit.clubs);

      System.out.println(card1.toString());
      System.out.println(card2.toString());
      System.out.println(card3.toString());
      card1.set('1', Card.Suit.clubs);
      card2.set('Q', Card.Suit.spades);

      System.out.println(card1.toString());
      System.out.println(card2.toString());
      System.out.println(card3.toString());
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
   
   public boolean getCardError()
   {
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
