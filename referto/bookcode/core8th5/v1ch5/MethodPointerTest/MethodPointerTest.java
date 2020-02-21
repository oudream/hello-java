/**
 * @version 1.00 11 Mar 1997
 * @author Cay Horstmann
 */

import java.lang.reflect.*;
import corejava.*;

public class MethodPointerTest
{  public static void main(String[] args) throws Exception
   {  printTable(1, 10, 10, 
         MethodPointerTest.class.getMethod("square", 
            new Class[] { double.class }));
      printTable(1, 10, 10, java.lang.Math.class.getMethod("sqrt",
         new Class[] { double.class }));
   }  

   public static double square(double x) { return x * x; }

   public static void printTable(double from, double to, 
      int n, Method f)
   {  System.out.println(f);
      double dx = (to - from) / (n - 1);
      for (double x = from; x <= to; x += dx)
      {  Format.printf("%12.4f |", x);
         try
         {  Object[] args = { new Double(x) };
            Double d = (Double)f.invoke(null, args);
            double y = d.doubleValue();
            Format.printf("%12.4f\n", y);
         } catch (Exception e)
         {  System.out.println("???");
         }
      }
   }
}