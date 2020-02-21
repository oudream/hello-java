/**
 * @version 1.20 27 Mar 1998 
 * @author Cay Horstmann
 */

public class ShellSort
{  public static void sort(int[] a)
   {  int n = a.length;
      int incr = n / 2;
      while (incr >= 1)
      {  for (int i = incr; i < n; i++)
         {  int temp = a[i];
            int j = i;
            while (j >= incr && temp < a[j - incr])
            {  a[j] = a[j - incr];
               j -= incr;
            }
            a[j] = temp;
         }
         incr /= 2;
      }
   }

   public static void print(int[] a)
   {  for (int i = 0; i < a.length; i++)
         System.out.print(a[i] + " ");
      System.out.println();
   }

   public static void main(String[] args)
   {  // make an array of ten integers
      int[] a = new int[10];
      int i;
      // fill the array with random values
      for (i = 0; i < a.length; i++)
         a[i] = (int)(Math.random() * 100);
      print(a);
      sort(a);
      print(a);
   }
}

