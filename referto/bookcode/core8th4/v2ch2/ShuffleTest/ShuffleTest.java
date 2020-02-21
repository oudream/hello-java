/**
 * @version 1.00 1999-07-07
 * @author Cay Horstmann
 */

import java.util.*;

public class ShuffleTest
{  public static void main(String[] args)
   {  List numbers = new ArrayList(49);
      for (int i = 1; i <= 49; i++)
         numbers.add(new Integer(i));
      Collections.shuffle(numbers);
      List winningCombination = numbers.subList(0, 6);
      Collections.sort(winningCombination);
      System.out.println(winningCombination);
   }
}