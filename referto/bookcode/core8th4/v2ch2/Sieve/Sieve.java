/**
 * @version 1.20 1999-07-03
 * @author Cay Horstmann
 */

import java.util.*;

public class Sieve
{  public static final boolean PRINT = false;

   public static void main(String[] s)
   {  int n = 1000000;
      long start = System.currentTimeMillis();
      BitSet b = new BitSet(n);
      int count = 0;
      int i;
      for (i = 2; i <= n; i++)
         b.set(i);
      i = 2;
      while (i * i <= n)
      {  if (b.get(i))
         {  if (PRINT) System.out.println(i);
            count++;
            int k = 2 * i;
            while (k <= n)
            {  b.clear(k);
               k += i;
            }
         }
         i++;
      }
      while (i <= n)
      {  if (b.get(i))
         {  if (PRINT) System.out.println(i);
            count++;
         }
         i++;
      }
      long end =  System.currentTimeMillis();
      System.out.println(count + " primes");
      System.out.println((end - start) + " milliseconds");
   }
}

