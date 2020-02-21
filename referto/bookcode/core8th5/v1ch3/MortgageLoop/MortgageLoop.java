/**
 * @version 1.20 27 Mar 1998
 * @author Gary Cornell
 */

import corejava.*;

public class MortgageLoop
{  public static void main(String[] args)
   {  double principal;
      double yearlyInterest;
      int years;
 
      principal = Console.readDouble
         ("Loan amount (no commas):");
      yearlyInterest = Console.readDouble
         ("Interest rate in % (ex: use 7.5 for 7.5%):") / 100; 
      years = Console.readInt
         ("The number of years:");

      double y;
      for (y = yearlyInterest - 0.01; 
         y <= yearlyInterest + 0.01; y += 0.00125)         
      {  double monthlyInterest = y / 12;
         double payment = principal * monthlyInterest 
            / (1 - (Math.pow(1/(1 + monthlyInterest), 
            years * 12)));
         Format.printf("With rate %6.3f", 100 * y);
         Format.printf
            ("%%, your monthly payment is $%10.2f\n", 
            payment);
      }
   }
}    

