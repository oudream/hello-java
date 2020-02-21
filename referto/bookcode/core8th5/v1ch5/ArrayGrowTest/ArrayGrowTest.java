/**
 * @version 1.00 11 Mar 1997
 * @author Cay Horstmann
 */

import java.lang.reflect.*;
import corejava.*;

public class ArrayGrowTest
{  public static void main(String[] args)
   {  int[] a = { 1, 2, 3 };
      Day[] b = { new Day(1996, 1, 1), new Day(1997, 3, 26) };
      a = (int[])goodArrayGrow(a);
      arrayPrint(a);
      b = (Day[])goodArrayGrow(b);
      arrayPrint(b);
      System.out.println
         ("The following call will generate an exception.");
      b = (Day[])badArrayGrow(b);
   }

   static Object[] badArrayGrow(Object[] a)
   {  int newLength = a.length * 11 / 10 + 10;
      Object[] newArray = new Object[newLength];
      System.arraycopy(a, 0, newArray, 0, a.length);
      return newArray;
   }

   static Object goodArrayGrow(Object a)
   {  Class cl = a.getClass();
      if (!cl.isArray()) return null;
      Class componentType = a.getClass().getComponentType();
      int length = Array.getLength(a);
      int newLength = length * 11 / 10 + 10;
      
      Object newArray = Array.newInstance(componentType, 
         newLength);
      System.arraycopy(a, 0, newArray, 0, length);
      return newArray;
   }

   static void arrayPrint(Object a)
   {  Class cl = a.getClass();
      if (!cl.isArray()) return;
      Class componentType = a.getClass().getComponentType();
      int length = Array.getLength(a);
      System.out.println(componentType.getName() 
         + "[" + length + "]");
      for (int i = 0; i < Array.getLength(a); i++)
         System.out.println(Array.get(a, i));
   }
}