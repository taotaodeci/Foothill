public class Foothill {

     public static void main(String []args) {
        SevenSegmentImage ssi = new SevenSegmentImage();

      System.out.println(
         "Testing SevenSegmentImage ===================================");

      ssi.setSize( 7, 9 );
      ssi.turnOnCellsForSegment( 'a' );
      ssi.display();
      ssi.turnOnCellsForSegment( 'b' );
      ssi.display();
      ssi.turnOnCellsForSegment( 'c' );
      ssi.display();
      ssi.turnOnCellsForSegment( 'd' );
      ssi.display();

      ssi.clearImage();
      ssi.turnOnCellsForSegment( 'e' );
      ssi.display();
      ssi.turnOnCellsForSegment( 'f' );
      ssi.display();
      ssi.turnOnCellsForSegment( 'g' );
      ssi.display();

      ssi.clearImage();
      ssi.turnOnCellsForSegment( 'x' );
      ssi.display();
      ssi.turnOnCellsForSegment( '3' );
      ssi.display();
      
      
      SevenSegmentDisplay  my7SegForCon = new SevenSegmentDisplay( 15, 13 );
      int j;
      
      System.out.println(
         "Testing SevenSegmentDisplay ===================================");

      my7SegForCon.setSize( 7, 9 );
      for ( j = 0; j < 16; j++ )
      {
         my7SegForCon.eval( j );
         my7SegForCon.loadConsoleImage();
         my7SegForCon.consoleDisplay();
      }

      for ( j = 5; j < 21; j += 4)
      {
         my7SegForCon.setSize( j, 2*j + 1 );
         my7SegForCon.eval( 5 );
         my7SegForCon.loadConsoleImage();
         my7SegForCon.consoleDisplay();
      }
   }
     }
}

class SevenSegmentImage implements Cloneable
{

   public static final int MIN_HEIGHT = 5;
   public static final int MIN_WIDTH = 5;
   public static final int MAX_HEIGHT = 65;
   public static final int MAX_WIDTH = 41;
   public static final String DRAW_CHAR = "*";
   public static final String BLANK_CHAR = " ";

   private boolean[][] data;
   private int topRow, midRow, bottomRow, leftCol, rightCol;

   public SevenSegmentImage()
   {
       setSize(MIN_WIDTH, MIN_HEIGHT);
   }

   public SevenSegmentImage(int width, int height)
   {
       if (!validateSize(width, height)) {
           setSize(MIN_WIDTH, MIN_HEIGHT);
       } else {
           setSize(width, height);
       }
   }

   public void clearImage()
   {
      for (int i = 0; i < data.length; ++i) {
          for (int j = 0; j < data[i].length; ++j) {
              data[i][j] = false;
          }
      }
   }

   public boolean turnOnCellsForSegment(char segment)
   {
      switch (Character.toUpperCase(segment)) {
         case 'A':
            drawHorizontal(topRow);
            break;
         case 'B':
            drawVertical(rightCol, topRow, midRow);
            break;
         case 'C':
            drawVertical(rightCol, midRow, bottomRow);
            break;
         case 'D':
            drawHorizontal(bottomRow);
            break;
         case 'E':
            drawVertical(leftCol, midRow, bottomRow);
            break;
         case 'F':
            drawVertical(leftCol, topRow, midRow);
            break;
         case 'G':
            drawHorizontal(midRow);
            break;
         default:
            return false;
      }
      return true;
   }

   public boolean setSize(int width, int height)
   {
      if (validateSize(width, height)) {
          data = new boolean[height][width];
          topRow = 0;
          midRow = (height-1) / 2;
          bottomRow = height - 1;
          leftCol = 0;
          rightCol = width - 1;
          return true;
      }
      return false;
   }

   public void display()
   {
      StringBuffer sb = new StringBuffer();
      for (int i = 0; i < data.length; ++i) {
          for (int j = 0; j < data[i].length; ++j) {
              sb.append(data[i][j] ? DRAW_CHAR : BLANK_CHAR);
          }
          sb.append("\n");
      }
      System.out.println(sb.toString());
   }

   // deep copy required
   public Object clone() throws CloneNotSupportedException
   {
      SevenSegmentImage ret = (SevenSegmentImage)super.clone();
      ret.data = data.clone();
      return ret;
   }

   private boolean validateSize(int width, int height)
   {
      return width >= MIN_WIDTH && width <= MAX_WIDTH && height >= MIN_HEIGHT && height <= MAX_HEIGHT;
   }

   // helpers - not required, but used by instructor
   void drawHorizontal(int row)
   {
      for (int j = 0; j < data[row].length; ++j) {
         data[row][j] = true;
      }
   }

   void drawVertical(int col, int startRow, int stopRow)
   {
      for (int i = startRow; i <= stopRow; ++i) {
         data[i][col] = true;
      }
   }
}

class SevenSegmentDisplay implements Cloneable
{
   private SevenSegmentImage theImage;
   private SevenSegmentLogic theDisplay;

   public SevenSegmentDisplay()
   {
      theImage = new SevenSegmentImage();
      theDisplay = new SevenSegmentLogic();
   }
   
   public SevenSegmentDisplay( int width, int height )
   {
      theImage = new SevenSegmentImage(width, height);
      theDisplay = new SevenSegmentLogic();
   }
   
   public boolean setSize( int width, int height )
   {
      theImage.setSize(width, height);
   }
   
   public void loadConsoleImage()
   {
      theImage.clearImage();
      for (int seg = 0; seg < 7; ++seg) {
         if (theDisplay.getValOfSeg(seg)) {
            theImage.turnOnCellsForSegment('A' + seg);
         }
      }
   }
   
   public void consoleDisplay()
   {
      theImage.display();
   }
   
   public void eval(int input)
   {
      theDisplay.eval(input);
   }

   public Object clone() throws CloneNotSupportedException
   {
      SevenSegmentDisplay ret = (SevenSegmentDisplay)super.clone();
      ret.theImage = theImage.clone();
      ret.theDisplay = theDisplay.clone();
      return ret;
   }
}


//  ----------------- From previous assignment ---------------------
class MultiSegmentLogic implements Cloneable
{
   protected int numSegs;
   protected BooleanFunc[] segs;

   // constructor
   public MultiSegmentLogic()
   {
      this(0);
   }

   // mutators
   public MultiSegmentLogic(int numSegs)
   {
      setNumSegs(numSegs);
   }

   public boolean setNumSegs(int numSegs)
   {
      if (numSegs >= 0)
      {
         this.numSegs = numSegs;
         this.segs = new BooleanFunc[numSegs];
         return true;
      } else
      {
         return false;
      }
   }

   public boolean setSegment(int segNum, BooleanFunc funcForThisSeg)
   {
      if (segNum < numSegs && segNum >= 0)
      {
         try
         {
            this.segs[segNum] = (BooleanFunc) funcForThisSeg.clone();
         } catch (CloneNotSupportedException e)
         {

         }
         return true;
      } else
      {
         return false;
      }
   }

   public void eval(int input)
   {
      for (int i = 0; i < numSegs; i++)
      {
         segs[i].eval(input);
      }
   }

   // deep memory method
   protected Object clone() throws CloneNotSupportedException
   {
      MultiSegmentLogic ret = (MultiSegmentLogic) super.clone();
      ret.segs = new BooleanFunc[numSegs];
      for (int i = 0; i < numSegs; ++i)
      {
         ret.segs[i] = (BooleanFunc) segs[i].clone();
      }
      return ret;
   }

}

// Phase 3
class SevenSegmentLogic extends MultiSegmentLogic
{
   public SevenSegmentLogic()
   {
      super(7);
      setSevenSegments();
   }

   boolean getValOfSeg(int seg)
   {
      if (seg >= numSegs || seg < 0)
      {
         return false;
      } else
      {
         return segs[seg].getState();
      }
   }

   private void setSevenSegments()
   {
      BooleanFunc sega = new BooleanFunc();
      BooleanFunc segb = new BooleanFunc();
      BooleanFunc segc = new BooleanFunc();
      BooleanFunc segd = new BooleanFunc();
      BooleanFunc sege = new BooleanFunc();
      BooleanFunc segf = new BooleanFunc();
      BooleanFunc segg = new BooleanFunc();

      int rulea[] =
      { 0, 2, 3, 5, 6, 7, 8, 9, 10, 12, 14, 15 };
      sega.setTruthTableUsingTrue(rulea);
      segs[0] = sega;

      int ruleb[] =
      { 0, 1, 4, 7, 9, 10, 13, 14 };
      segb.setTruthTableUsingTrue(ruleb);
      segs[1] = segb;

      int rulec[] =
      { 0, 2, 4, 8, 10, 12 };
      segc.setTruthTableUsingFalse(rulec);
      segs[2] = segc;

      int ruled[] =
      { 0, 1, 2, 5, 6, 7, 10, 11, 12, 15 };
      segd.setTruthTableUsingTrue(ruled);
      segs[3] = segd;

      int rulee[] =
      { 1, 2, 5, 7, 11, 13 };
      sege.setTruthTableUsingFalse(rulee);
      segs[4] = sege;

      int rulef[] =
      { 0, 5, 10, 15 };
      segf.setTruthTableUsingTrue(rulef);
      segs[5] = segf;

      int ruleg[] =
      { 1, 2, 3, 4, 5, 10, 11, 13, 14, 15 };
      segg.setTruthTableUsingTrue(ruleg);
      segs[6] = segg;
   }
}
