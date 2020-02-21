/**
 * @version 1.00 07 Feb 1996 
 * @author Gary Cornell
 */

import corejava.*;

public class Retirement
{  public static void main(String[] args)
   {  double goal;
      double interest;
      double payment;
      int years = 0;
      double balance = 0;
 
      goal = Console.readDouble
         ("How much money do you need to retire?");
      payment = Console.readDouble
         ("How much money will you contribute every year?");
      interest = Console.readDouble
         ("Interest rate in % (ex: use 7.5 for 7.5%):") / 100; 
         
      while (balance < goal)
      {  balance = (balance + payment) * (1 + interest);
         years++;
      }   

      System.out.println
         ("Your can retire in " + years + " years.");
   }
}    

