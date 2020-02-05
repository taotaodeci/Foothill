/*------------------------------------------------------------------------
Two classes used by assignment #4.  Do not modify these defintions.

Place the definitions into your final submission,  with each element 
appearing above the main Foothill class, and your own classes below
Foothill. 

The imports are needed by my solution and will probably be needed
by yours, so you should place them in your project at the top of the
file(s).
----------------------------------------------------------------------- */
import java.lang.*;
import java.util.*;

// IntPair allows public, no filtering; classes that use it will protect it
class IntPair
{
   public long firstInt;
   public long secondInt;

   // constructors
   IntPair() { firstInt = secondInt = 0; }
   IntPair(long frst, long scnd) { firstInt = frst;  secondInt = scnd; }
   
   public String toString()
   {  
      return "(" + firstInt + ", " + secondInt + ")";
   }
};

// EncryptionSupport contains only static methods for client use 
// method names should be fairly descriptive except inverseMonN(), which
// you can take as a black-box (see description of assignment)
class EncryptionSupport
{
   static private Random randObject = new Random(System.currentTimeMillis());
   
   public static boolean isPrime(long x)
   {
      long k, loopLim;

      if (x < 2)
         return false;
      if (x < 4)
         return true;
      if (x % 2 == 0 || x % 3 == 0)
         return false;

      // now use the fact the all primes of form 6k +/- 1
      loopLim = (long)Math.sqrt(x);
      for (k = 5; k <= loopLim; k += 6)
      {
         if (x % k == 0 || x % (k + 2) == 0)
            return false;
      }
      return true;
   }
   
   public static long inverseModN(long a, long n)
   {
      // uses extended euclidean algorithm giving as + nt = gcd(n, a), 
      // with gcd(n, a) = 1,  and s, t discovered.  s = 1/a, and t ignored

      long s, t, r, sPrev, tPrev, rPrev, temp, q, inverse;

      // special key encryption conditions;  we will pick some prime e >= 3 for a
      if (a < 3 || a >= n || !isPrime(a))
         return 0;  // error
      // we are now guaranteed 3 <= a < n and gcd(a, n) = 1;
      // initialize working variables
      s = 0;         t = 1;         r = n;
      sPrev = 1;    tPrev = 0;    rPrev = a;

      while (r != 0)
      {
         q = rPrev / r;

         temp = r;
         r = rPrev - q * r;
         rPrev = temp;

         temp = s;
         s = sPrev - q * s;
         sPrev = temp;

         temp = t;
         t = tPrev - q * t;
         tPrev = temp;
      }

      inverse = sPrev % n;
      if (inverse < 0)
         inverse += n;
      return inverse;
   }
   
   public static long getSmallRandomPrime()
   {
      int index;
      long lowPrimes[] =
      {
               19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67,
               71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113,
               127, 131, 137, 139, 149, 151, 157, 163, 167, 173,
               179, 181, 191, 193, 197, 199, 211, 223, 227, 229,
               233, 239, 241, 251, 257, 263, 269, 271, 277, 281,
               283, 293, 307, 311, 313, 317, 331, 337, 347, 349,
               353, 359, 367, 373, 379, 383, 389, 397, 401, 409,
               419, 421, 431, 433, 439, 443, 449, 457, 461, 463,
               467, 479, 487, 491, 499, 503, 509, 521, 523, 541
      };
      long arraySize = lowPrimes.length;

      // pick prime in the above array bet 0 and arraySize - 1
      index = (int)( randObject.nextDouble() * arraySize );

      return lowPrimes[index]; 
   }
   
   public static long getMedSizedRandomPrime()
   {
      int index;
      long medPrimes[] =
      {
            541, 547, 557, 563, 569, 571, 577, 587, 593, 599, 601, 607,
            613, 617, 619, 631, 641, 643, 647, 653, 659, 661, 673, 677,
            683, 691, 701, 709, 719, 727, 733, 739, 743, 751, 757, 761,
            769, 773, 787, 797, 809, 811, 821, 823, 827, 829, 839, 853,
            857, 859, 863, 877, 881, 883, 887, 907, 911, 919, 929, 937,
            941, 947, 953, 967, 971, 977, 983, 991, 997, 1009, 1013, 1019,
            1021, 1031, 1033, 1039, 1049, 1051, 1061, 1063, 1069, 1087,
            1091, 1093, 1097, 1103, 1109, 1117, 1123, 1129, 1151, 1153,
            1163, 1171, 1181, 1187, 1193, 1201, 1213, 1217, 1223,
      };
      long arraySize = medPrimes.length;

      // pick prime in the above array bet 0 and arraySize - 1
      index = (int)(randObject.nextDouble() * arraySize );

      return medPrimes[index]; 
   }
}

public class Foothill
{
   public static void main (String[] args)
   {
      Communicator c;
      
      System.out.println("Derived Class Constructor Testing ***************");
      
      c = new Communicator();
      System.out.println("----------------\n" + c.toString() + "\n\n");
      
      c = new Communicator("salim lakhani", "10.1.10.1", 18, 19);
      System.out.println("----------------\n" + c.toString() + "\n\n");
      
      c = new Communicator("yan kam", "127.90.32.14", 19, 19);
      System.out.println("----------------\n" + c.toString() + "\n\n");
      
      c = new Communicator("sally", "1", 19, 37);
      System.out.println("----------------\n" + c.toString() + "\n\n");
      
      c = new Communicator("betty", "1.1.1.1", 18, 19);
      System.out.println("----------------\n" + c.toString() + "\n\n");
      
      System.out.println("Derived and Base Class Mutator Testing **********");
      
      c = new Communicator("giral", "", 809, 821);
      System.out.println("----------------\n" + c.toString() + "\n\n");
      
      c.setName("NewName");
      System.out.println("----------------\n" + c.toString() + "\n\n");
      
      c.setFirstAndSecondPrime(137, 139);
      System.out.println("----------------\n" + c.toString() + "\n\n");
      
      System.out.println(c.getPublicKey().toString());
      System.out.println(c.getPrivateKey().toString());
   }
}

class InternetUser
{
    public static int MIN_NAME_LENGTH = 2;
    public static int MAX_NAME_LENGTH = 50;
    public static int MIN_IP_LENGTH = 7;
    public static int MAX_IP_LENGTH = 15;
    public static String DEFAULT_NAME = "(undefined)";
    public static String DEFAULT_IP ="0.0.0.0";
    
    public static int ERROR_FLAG_NUM = 0;
    private static int MAX_PQ = (int)Math.sqrt(Long.MAX_VALUE);
    
    private String name = DEFAULT_NAME; 
    private String ip = DEFAULT_IP;
    
    InternetUser() {
    }
    
    InternetUser(String name, String ip) {
        setName(name);
        setIp(ip);
    }
    
    public String getName() {
        return name;
    }
    
    public String getIp() {
        return ip;
    }
    
    public boolean setName(String name) {
        if (isValidName(name)) {
            this.name = name;
            return true;
        } else {
            return false;
        }
    }
    
    public boolean setIp(String ip) {
        if (isValidIp(ip)) {
            this.ip = ip;
            return true;
        } else {
            return false;
        }
    }
    
    public String toString() {
        return "Name: " + name + "\nIP Aaddr: " + ip;
    }
    
    private boolean isValidName(String name) {
        return name != null && MIN_NAME_LENGTH <= name.length() && name.length() <= MAX_NAME_LENGTH;
    }
    
    private boolean isValidIp(String ip) {
        return ip != null && MIN_IP_LENGTH <= ip.length() && ip.length() <= MAX_IP_LENGTH;
    }
}

class Communicator extends InternetUser
{
    private IntPair publicKey;
    private IntPair privateKey;
    
    private long firstPrime, secondPrime;
    private long n, phi, e, d;
    
    private static int MAX_TRY_E = 100;
    
    Communicator() {
        this(DEFAULT_NAME, DEFAULT_IP, 0, 0);
    }
    Communicator( long firstPrime, long secondPrime ) {
        this(DEFAULT_NAME, DEFAULT_IP, firstPrime, secondPrime);
    }
    Communicator( String name, String ip ) {
        this(name, ip, 0, 0);
    }
    Communicator( String name, String ip, long firstPrime, long secondPrime ) {
        super(name, ip);
        publicKey = new IntPair();
        privateKey = new IntPair();
        if (!setFirstAndSecondPrime(firstPrime, secondPrime)) {
            firstPrime = secondPrime = n = phi = e = d = 0;
        }
    }
    
    public IntPair getPublicKey() {
        return publicKey;
    }
    
    public IntPair getPrivateKey() {
        return privateKey;
    }
    
    public String toString() {
        return super.toString() + "\n\n" + 
            String.format("(p, q)  n, phi, e, d: (%d, %d)   %d, %d, %d, %d \npublic key%s \nprivate key%s", firstPrime, secondPrime, n, phi, e, d, publicKey, privateKey);
    }
    
    public boolean setFirstAndSecondPrime(long firstPrime, long secondPrime) {
        if (firstPrime != secondPrime && EncryptionSupport.isPrime(firstPrime) && EncryptionSupport.isPrime(secondPrime)) {
            this.firstPrime = firstPrime;
            this.secondPrime = secondPrime;
            computeBothEncrKeys();
            return true;
        } else {
            return false;
        }
    }
    
    private boolean computeBothEncrKeys() {
        long p = firstPrime;
        long q = secondPrime;
        n = p * q;
        phi = (p-1) * (q-1);
        
        int i = 0;
        for (i = 0; i < MAX_TRY_E; ++i) {
            e = EncryptionSupport.getSmallRandomPrime();
            if (e < phi && phi % e != 0) {
                break;
            }
        }
        if (i >= MAX_TRY_E) {
            return false;
        }
        
        d = EncryptionSupport.inverseModN(e, n);
        publicKey = new IntPair(e, n);
        privateKey = new IntPair(d, n);
        return true;
    }
    
}
