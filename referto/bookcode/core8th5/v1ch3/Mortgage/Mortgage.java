/**
 * @version 1.10 10 Mar 1997 
 * @author Gary Cornell
 */

import corejava.*;
import java.text.*;

public class Mortgage
{  public static void main(String[] args)
   {  double principal;
      double yearlyInterest;
      int years;
 
      principal = Console.readDouble
         ("Loan amount (no commas):");
      yearlyInterest = Console.readDouble
         ("Interest rate in % (ex: use 7.5 for 7.5%):")/100; 
      years = Console.readInt("The number of years:");
         
      double monthlyInterest = yearlyInterest / 12;
      double payment = principal * monthlyInterest 
         / (1 - (Math.pow(1/(1 + monthlyInterest), 
            years * 12)));
      System.out.println("Your payment is ");
      NumberFormat nf = NumberFormat.getCurrencyInstance();
      System.out.println(nf.format(payment));
   }
}    

