/**
 * @version 1.00 11 Mar 1997
 * @author Cay Horstmann
 */

import corejava.*;
import java.math.*;

public class BigIntegerTest
{  public static BigInteger lotteryOdds(int high, int number)
   {  BigInteger r = new BigInteger("1");
      int i;
      for (i = 1; i <= number; i++)
      {  r = r.multiply(BigInteger.valueOf(high))
            .divide(BigInteger.valueOf(i));
         high--;
      }
      return r;
   }

   public static void main(String[] args)
   {  int numbers = Console.readInt
      ("How many numbers do you need to draw?");
      int topNumber = Console.readInt
         ("What is the highest number you can draw?");
      BigInteger oddsAre = lotteryOdds(topNumber, numbers);

      System.out.println("Your odds are 1 in " + oddsAre + 
        ". Good luck!");
   }
}
