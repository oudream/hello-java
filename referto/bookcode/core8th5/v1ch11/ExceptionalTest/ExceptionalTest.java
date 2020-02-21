/**
 * @version 1.00 07 Feb 1996 
 * @author Cay Horstmann
 */

import java.util.*;

class ExceptionalTest
{  public static void main(String[] args)
   {  int i = 0;
      int ntry = 1000000;
      Stack s = new Stack();
      long s1;
      long s2; 
      System.out.println("Testing for empty stack");
      s1 = new Date().getTime();
      for (i = 0; i <= ntry; i++)
         if (!s.empty()) s.pop();
      s2 = new Date().getTime();
      System.out.println((s2 - s1) + " milliseconds");
         
      System.out.println("Catching EmptyStackException");
      s1 = new Date().getTime();
      for (i = 0; i <= ntry; i++)
      {  try
         {  s.pop();
         }
         catch(EmptyStackException e)
         {
         }
      }         
      s2 = new Date().getTime();
      System.out.println((s2 - s1) + " milliseconds");
   }
}
