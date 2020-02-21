/**
 * @version 1.00 07 Feb 1996 
 * @author Cay Horstmann
 */

import java.util.*;
import corejava.*;

public class LotteryDrawing
{  public static int[] drawing(int high, int number)
   {  int i;
      int numbers[] = new int[high];
      int result[] = new int[number];
      // fill an array with numbers 1 2 3 . . . high
      for (i = 0; i < high; i++) numbers[i] = i + 1;
      for (i = 0; i < number; i++)
      {  int j = (int)(Math.random() * (high - i));
         result[i] = numbers[j];
         numbers[j] = numbers[high - 1 - i];
      }
      return result;
   }
   
   public static void main(String[] args)
   {  int numbers = Console.readInt
         ("How many numbers do you need to draw?");
      int topNumber = Console.readInt
         ("What is the highest number you can draw?");
   
      int[] a = drawing(topNumber, numbers);
      Arrays.sort(a);
      System.out.println("Bet the following combination. It'll make you rich!");
      int i;
      for (i = 0; i < a.length; i++)
         System.out.println(a[i]);
   }
}
