package corejava;

/**
   A class for assertion checking

   @version 1.00 10 Oct 1998 
   @author Cay Horstmann
*/

public class Assertion 
{  
   /**
      Check an assertion
      @param b the condition to check
      @param s a string describing the check
      @throws Assertion.Failure if condition not true
   */

   public static void check(boolean b, String s)
   {  if (doCheck && !b)
         throw new Failure(s);
   }

   /**
      Check an assertion
      @param b the condition to check
      @throws Assertion.Failure if condition not true
   */

   public static void check(boolean b)
   {  if (doCheck && !b)
         throw new Failure();
   }

   /**
      Check an assertion
      @param obj an object to check
      @param s a string describing the check
      @throws Assertion.Failure if object is null
   */

   public static void check(Object obj, String s)
   {  if (doCheck && obj == null)
         throw new Failure(s);
   }

   /**
      Check an assertion
      @param obj an object to check
      @throws Assertion.Failure if object is null
   */

   public static void check(Object obj)
   {  if (doCheck && obj == null)
         throw new Failure();
   }
  
   /**
      Check an assertion
      @param x a number
      @param s a string describing the check
      @throws Assertion.Failure if number is 0
   */

   public static void check(double x, String s)
   {  if (doCheck && x == 0)
         throw new Failure(s);
   }

   /**
      Check an assertion
      @param x a number
      @throws Assertion.Failure if number is 0
   */

   public static void check(double x)
   {  if (doCheck && x == 0)
         throw new Failure();
   }

   /**
      Check an assertion
      @param x a number
      @param s a string describing the check
      @throws Assertion.Failure if number is 0
   */

   public static void check(long x, String s)
   {  if (doCheck && x == 0)
         throw new Failure(s);
   }

   /**
      Check an assertion
      @param x a number
      @throws Assertion.Failure if number is 0
   */

   public static void check(long x)
   {  if (doCheck && x == 0)
         throw new Failure();
   }

   /**
      Turn checking on or off
      @param c true to turn checking on, false to turn checking off
   */

   public static void setCheck(boolean c)
   {  doCheck = c;
   }
   
   private static boolean doCheck = true;

   /**
      test stub
   */

   public static void main(String[] args)
   {  Assertion.check(args);
      Assertion.check(args.length, "No command line arguments");
   }
   
   /**
      A class for reporting assertion failures
   */

   public static class Failure extends RuntimeException
   {  public Failure() 
      {  super("Assertion failed"); 
      }

      /**
         @param gripe a description of the reason for the failure
      */
      public Failure(String gripe) 
      {  super(gripe); 
      }
   }
}

