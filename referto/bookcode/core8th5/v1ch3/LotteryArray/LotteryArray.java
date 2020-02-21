/**
 * @version 1.0 27 Mar 1998
 * @author Cay Horstmann
 */

import corejava.*;

public class LotteryArray
{  public static long lotteryOdds(int high, int number)
   {  long r = 1;
      int i;
      for (i = 1; i <= number; i++)
      {  r = r * high / i;
         high--;
      }
      return r;
   }

   public static void main(String[] args)
   {  int i;
      int j;

      final int MAX_HIGH = 10;

      // allocate triangular array
      long[][] odds = new long[MAX_HIGH + 1][];
      for (i = 0; i <= MAX_HIGH; i++)
         odds[i] = new long[i + 1];

      // fill triangular array
      for (i = 0; i < odds.length; i++)
         for (j = 0; j < odds[i].length; j++)
            odds[i][j] = lotteryOdds(i, j);

      // print triangular array
      for (i = 0; i < odds.length; i++)
      {  for (j = 0; j < odds[i].length; j++)
            Format.printf("%4d", odds[i][j]);
         System.out.println();
      }
   }
}    


