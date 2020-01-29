import java.util.Scanner;

/**
 * Name: Qinlin Shi
 * This program uses Automation to propogate new generations and print to console.
 */

/**
 * Foothill contains main method which creates an Automation class and run a sample.
 */
public class Foothill
{
   public static void main(String[] args)
   {
      int rule, k;
      String strUserIn;
      
      Scanner inputStream = new Scanner(System.in);
      Automaton aut;

      // get rule from user
      do
      {
         System.out.print("Enter Rule ("  
               + Automaton.MIN_RULE + " - " +  Automaton.MAX_RULE + "): ");
         // get the answer in the form of a string:
         strUserIn = inputStream.nextLine();
         // and convert it to a number so we can compute:
         rule = Integer.parseInt(strUserIn);

      } while (rule < Automaton.MIN_RULE || rule > Automaton.MAX_RULE);

      // create automaton with this rule and single central dot
      aut = new Automaton(rule);
      aut.setDisplayWidth(79);

      // now show it
      System.out.println("   start");
      for (k = 0; k < 50; k++)
      {
         System.out.println( aut.toStringCurrentGen() );
         aut.propagateNewGeneration();
      }
      inputStream.close();
      System.out.println("   end");
   }
}

/**
 * Automation implements methods to propogate string to next generation
 */
class Automaton
{
   // class constants
   public final static int RULES_SIZE = 8;
   public final static int BITS_IN_RULE_SIZE 
      = (int)(Math.log(RULES_SIZE) / Math.log(2));
   public final static int MIN_DISPLAY_WIDTH = 21;
   public final static int MAX_DISPLAY_WIDTH = 121;
   public final static int DFLT_DISPLAY_WIDTH = 79;
   public final static int MIN_RULE = 0;
   public final static int MAX_RULE = (int)Math.pow(2,RULES_SIZE) - 1; // 255
   public final static int DFLT_RULE = 1;
   public final static String ON_STR = "*";
   public final static String OFF_STR = " ";
   
   // private members
   private boolean rule[];   // allocate rule[8] in constructor!
   private String thisGen;   // same here
   String extremeBit; // bit, "*" or " ", implied everywhere "outside"
   int displayWidth;  // an odd number so it can be perfectly centered
   
   // public constructors, mutators, etc. (need to be written)
   public Automaton(int newRule) {
       setRule(newRule);
       resetFirstGen();
   }
   public void resetFirstGen() {
       thisGen = ON_STR;
       extremeBit = OFF_STR;
   }
   public boolean setRule(int newRule) {
       if (newRule < MIN_RULE || newRule > MAX_RULE) return false;
       rule = new boolean[RULES_SIZE];
       for (int i = 0; i < RULES_SIZE; ++i, newRule >>= 1) {
           rule[i] = ((newRule & 1) == 1);
       }
       return true;
   }
   public boolean setDisplayWidth(int width) {
       if (width % 2 == 0 || width < MIN_DISPLAY_WIDTH || width > MAX_DISPLAY_WIDTH) {
           return false;
       }
       displayWidth = width;
       return false;
   }
   public String toStringCurrentGen() {
       StringBuffer sb = new StringBuffer();
       for (int i = 0; i < displayWidth; ++i) {
           int idx = i - (displayWidth - thisGen.length()) / 2;
           if (idx >= 0 && idx < thisGen.length()) {
               sb.append(thisGen.charAt(idx));
           } else {
               sb.append(extremeBit);
           }
       }
       return sb.toString();
   }
   public void propagateNewGeneration() {
       StringBuffer sb = new StringBuffer();
       String s = extremeBit + extremeBit + thisGen + extremeBit + extremeBit;
       for (int i = 0; i < s.length()-BITS_IN_RULE_SIZE + 1; ++i) {
           int idx = bitsToInt(s.substring(i, i+BITS_IN_RULE_SIZE));
           sb.append(rule[idx] ? ON_STR : OFF_STR);
       }
       thisGen = sb.toString();
       
       int idx = bitsToInt(new String(new char[BITS_IN_RULE_SIZE]).replace('\0', extremeBit.charAt(0)));
       extremeBit = rule[idx] ? ON_STR : OFF_STR;
   }
   private static int bitsToInt(String bits) {
       int ret = 0;
       for (int i = 0; i < bits.length(); ++i) {
           ret *= 2;
           if (bits.charAt(i) == ON_STR.charAt(0)) {
               ret += 1;
           }
       }
       return ret;
   }
}
