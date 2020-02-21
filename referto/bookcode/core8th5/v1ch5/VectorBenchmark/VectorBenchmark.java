/**
 * @version 1.10 27 Apr 1997
 * @author Cay Horstmann
 */

import java.util.*;

class VectorBenchmark
{  public static void main(String[] args)
   {  Vector v = new Vector();
      
      long start = new Date().getTime();
      for (int i = 0; i < MAXSIZE; i++)
         v.add(new Integer(i));
      long end = new Date().getTime();
      System.out.println("Allocating vector elements: " 
         + (end - start) + " milliseconds");

      Integer[] a = new Integer[1];
      start = new Date().getTime();
      for (int i = 0; i < MAXSIZE; i++)
      {  if (i >= a.length)
         {  Integer[] b = new Integer[i * 2];
            System.arraycopy(a, 0, b, 0, a.length);
            a = b;
         }
         a[i] = new Integer(i);
      }
      end = new Date().getTime();
      System.out.println("Allocating array elements:  " 
         + (end - start) + " milliseconds");

      start = new Date().getTime();
      for (int j = 0; j < NTRIES; j++)
         for (int i = 0; i < MAXSIZE; i++)
         {  Integer r = (Integer)v.get(i);
            v.set(i, new Integer(r.intValue() + 1));
         }
      end = new Date().getTime();
      System.out.println("Accessing vector elements:  " 
         + (end - start) + " milliseconds");

      start = new Date().getTime();
      for (int j = 0; j < NTRIES; j++)
         for (int i = 0; i < MAXSIZE; i++)
         {  Integer r = a[i];
            a[i] = new Integer(r.intValue() + 1);
         }
      end = new Date().getTime();
      System.out.println("Accessing array elements:   " 
         + (end - start) + " milliseconds");
   }

   public static final int MAXSIZE = 100000;
   public static final int NTRIES = 10;
}