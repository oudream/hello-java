/**
 * @version 1.00 07 Feb 1996 
 * @author Cay Horstmann
 */

import corejava.*;

public class SquareRoot
{  public static void main(String[] args)
   {  double a = Console.readDouble("Please enter a number:");
      
      double xnew = a / 2;
      double xold;

      do
      {  xold = xnew;
         xnew = (xold + a / xold) / 2;
         System.out.println(xnew);
      }
      while (Math.abs(xnew - xold) > 1E-4);
   }
}
