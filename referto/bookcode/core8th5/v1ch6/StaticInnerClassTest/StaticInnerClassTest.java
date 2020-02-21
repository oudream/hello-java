/**
 * @version 1.00 07 Apr 1998
 * @author Cay Horstmann
 */

public class StaticInnerClassTest
{  public static void main(String[] args)
   {  double[] d = new double[20];
      for (int i = 0; i < d.length; i++)
         d[i] = 100 * Math.random();
      ArrayAlg.Pair p = ArrayAlg.minmax(d);
      System.out.println("min = " + p.getFirst());
      System.out.println("max = " + p.getSecond());
   }
}

class ArrayAlg
{  public static class Pair
   {  public Pair(double f, double s)
      {  first = f;
         second = s;
      }
      public double getFirst()
      {  return first;
      }
      public double getSecond()
      {  return second;
      }

      private double first;
      private double second;
   }

   public static Pair minmax(double[] d)
   {  if (d.length == 0) return new Pair(0, 0);
      double min = d[0];
      double max = d[0];
      for (int i = 1; i < d.length; i++)
      {  if (min > d[i]) min = d[i];
         if (max < d[i]) max = d[i];
      }
      return new Pair(min, max);
   }
}
