/**
 * @version 1.00 07 Feb 1996 
 * @author Cay Horstmann
 */

import corejava.*;

public class LotteryOdds
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
   {  int numbers = Console.readInt
         ("How many numbers do you need to draw?");
      int topNumber = Console.readInt
         ("What is the highest number you can draw?");
      long oddsAre = lotteryOdds(topNumber, numbers);
      
      System.out.println
         ("Your odds are 1 in " + oddsAre + ". Good luck!");
   }
}    

