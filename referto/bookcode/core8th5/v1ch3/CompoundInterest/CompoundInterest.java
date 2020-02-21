/**
 * @version 1.2 27 Mar 1998 
 * @author Cay Horstmann
 */

import corejava.*;

public class CompoundInterest
{  public static double futureValue(double initialBalance, 
      double nyear, double p)
   {  return initialBalance * Math.pow(1 + p / 12 / 100,
         12 * nyear);
   }

   public static void main(String[] args)
   {  double[][] balance;
      balance = new double[5][6];
      int i;
      int j;
      for (i = 0; i < 5; i++)
         for (j = 0; j < 6; j++)
            balance[i][j] = futureValue(10000, 10 + 10 * i,
               5 + 0.5 * j);
      System.out.print("   ");
      for (j = 0; j < 6; j++)
         Format.printf("%9.2f%", 5 + 0.5 * j);

      System.out.println("");
      for (i = 0; i < 5; i++)
      {  Format.printf("%3d", 10 + 10 * i);
         for (j = 0; j < 6; j++)
            Format.printf("%10.2f", balance[i][j]);
         System.out.println("");
      }         
   }
}
