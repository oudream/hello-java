/**
 * @version 1.00 11 Apr 1998
 * @author Cay Horstmann
 */

import java.lang.reflect.*;
import corejava.*;

public class ObjectAnalyzerTest
{  public static void main(String[] args)
   {  Employee harry  = new Employee("Harry Hacker", 35000,
         new Day(1996,12, 1));
      System.out.println(harry);
      Employee coder  = new Employee("Harry Hacker", 35000,
         new Day(1996,12, 1));
      System.out.println(harry.equals(coder));
      harry.raiseSalary(5);
      System.out.println(harry.equals(coder));

      System.out.println(ObjectAnalyzer.toString(System.out));
   }
}

class ObjectAnalyzer
{  public static String toString(Object obj)
   {  Class cl = obj.getClass();
      String r = cl.getName() + "[";
      Class sc = cl.getSuperclass();
      if (!sc.equals(Object.class)) r += sc + ",";
      Field[] fields = cl.getDeclaredFields();
      try
      {  AccessibleObject.setAccessible(fields, true);
      }
      catch(SecurityException e) {}
      for (int i = 0; i < fields.length; i++)
      {  Field f = fields[i];
         r += f.getName() + "=";
         try
         {  Object val = f.get(obj);
            r += val.toString;
         } catch (IllegalAccessException e)
         {  r += "???";
         }
         if (i < fields.length - 1) 
            r += ","; 
         else
            r += "]";
      }
      return r;
   }

   public static boolean equals(Object a, Object b)
   {  Class cl = a.getClass();
      if (!cl.equals(b.getClass())) return false;
      Field[] fields = cl.getDeclaredFields();
      AccessibleObject.setAccessible(fields, true);
      for (int i = 0; i < fields.length; i++)
      {  Field f = fields[i];
         try
         {  if (!f.get(a).equals(f.get(b)))
               return false;
         }
         catch(IllegalAccessException e)
         {  return false;
         }
      }
      return true;
   }
}

class Employee
{  public Employee(String n, double s, Day d)
   {  name = n;
      salary = s;
      hireDay = d;
   }

   public String toString()
   {  return ObjectAnalyzer.toString(this);
   }

   public boolean equals(Object b)
   {  return ObjectAnalyzer.equals(this, b);
   }

   public void print()
   {  System.out.println(name + " " + salary + " " 
         + hireYear());
   }

   public void raiseSalary(double byPercent)
   {  salary *= 1 + byPercent / 100;
   }

   public int hireYear()
   {  return hireDay.getYear();
   }

   private String name;
   private double salary;
   private Day hireDay;
}


