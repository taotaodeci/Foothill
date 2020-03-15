import java.util.*;

// for random card generation and a test main
public class Foothill
{
   public static void main(String[] args)
   {
      int k;
      
      Card first = new Card('A', Card.Suit.spades);
      Card second = new Card('4', Card.Suit.hearts);
      Card third = new Card('T', Card.Suit.clubs);
      
      System.out.println( "should all be 0:\n"); 
      System.out.println( first.compareTo( first ) );
      System.out.println( second.compareTo( second ) );
      System.out.println( third.compareTo( third ) );
      
      System.out.println( "\nshould all be < 0:\n"); 
      System.out.println( second.compareTo( first ) );
      System.out.println( second.compareTo( third ) );
      System.out.println( third.compareTo( first ) );
      
      System.out.println( "\nshould all be > 0:\n"); 
      System.out.println( first.compareTo( second ) );
      System.out.println( third.compareTo( second ) );
      System.out.println( first.compareTo( third ) );
      
      System.out.println( "\nSome random cards:\n");
      for ( k = 0; k < 50; k++ )
      {
         System.out.print( generateRandomCard().toString() + "  ");
      }
      System.out.println();
      System.out.println();
      
      LinkedList<Card> myList = new LinkedList<>();
      for (int i = 0; i < 5; ++i) {
         Card x = generateRandomCard();
         insert(myList, x);
         insert(myList, x);
      }
      displayList(myList);
      System.out.println();
      
      Card card1 = myList.get(1);
      Card card2 = myList.get(3);
      Card card3 = myList.get(5);
      while (remove(myList, card1));
      while (remove(myList, card2));
      displayList(myList);
      System.out.println();
      
      System.out.println(removeAll(myList, card1));
      System.out.println(removeAll(myList, card2));
      System.out.println(removeAll(myList, card3));
   }

   static void displayList(LinkedList<Card> myList) {
      ListIterator<Card> iter;
      for (iter = myList.listIterator(); iter.hasNext(); )
         System.out.println(iter.next().toString());
   }

   // "global" static Foothill methods 
   static Card generateRandomCard()
   {
      // if firstTime = true, use clock to seed, else fixed seed for debugging
      Card.Suit suit;
      char val;

      int suitSelector, valSelector;

      // get random suit and value
      suitSelector = (int) (Math.random() * 4);
      valSelector = (int) (Math.random() * 13);

      // pick suit
      suit = turnIntIntoSuit(suitSelector);
      val = turnIntIntoVal(valSelector);

      return new Card(val, suit);
   }

   // note:  this method not needed if we use int for suits instead of enum
   static Card.Suit turnIntIntoSuit(int k)
   {
      return Card.Suit.values()[k];  // 
   }

   static char turnIntIntoVal(int k)
   {
      String legalVals = "23456789TJQKA";
      
      if (k < 0 | k >= legalVals.length())
         return '?';
      return legalVals.charAt(k);
   }
   
   static void insert(LinkedList<Card> myList, Card x) {
      ListIterator<Card> iter;

      for (iter = myList.listIterator(); iter.hasNext(); )
      {
        Card listX = iter.next();
        if (x.compareTo(listX) <= 0)
        {
           iter.previous(); // back up one
           break;
        }
      }
      iter.add(x);
   }
   
   static boolean remove(LinkedList<Card> myList, Card x) {
      ListIterator<Card> iter;

      for (iter = myList.listIterator(); iter.hasNext(); )
         if (iter.next().compareTo(x) == 0)
         {
            iter.remove();
            return true;   // we found, we removed, we return
         }
      return false;
   }
   
   static boolean removeAll(LinkedList<Card> myList, Card x) {
      boolean found = false;
      while ( remove(myList, x) ) {
         found = true;
      }
      return found;
   }
}


class Card implements Comparable<Card>
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
   
   // for sort  
   protected static char[] valueRanks = { '2', '3', '4', '5', '6', '7', '8', '9', 
      'T', 'J', 'Q', 'K', 'A'};
   protected static Suit[] suitRanks = {Suit.clubs, Suit.diamonds, Suit.hearts, 
      Suit.spades};
   protected static final int NUM_VALS = 13; 

   // sort member methods
   public int compareTo(Card other)
   {
      if (this.value == other.value)
         return ( getSuitRank(this.suit) - getSuitRank(other.suit) );

      return ( 
            getValueRank(this.value) 
            - getValueRank(other.value) 
            );
   }

   public static int getSuitRank(Suit st)
   {
      int k;

      for (k = 0; k < 4; k++) 
         if (suitRanks[k] == st)
            return k;

      // should not happen
      return 0;
   }

   public  static int getValueRank(char val)
   {
      int k;

      for (k = 0; k < NUM_VALS; k++) 
         if (valueRanks[k] == val)
            return k;

      // should not happen
      return 0;
   }
}
