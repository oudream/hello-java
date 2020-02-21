/**
 * @version 1.20 07 Apr 1998 
 * @author Cay Horstmann
 */

import java.util.*;
import corejava.*;

public class EmployeeSortTest
{  public static void main(String[] args)
   {  Employee[] staff = new Employee[3];

      staff[0] = new Employee("Harry Hacker", 35000, 
         new Day(1989,10,1));
      staff[1] = new Employee("Carl Cracker", 75000, 
         new Day(1987,12,15));
      staff[2] = new Employee("Tony Tester", 38000, 
         new Day(1990,3,15));
        
      ArrayAlg.shellSort(staff);
        
      int i;
      for (i = 0; i < staff.length; i++) 
         System.out.println(staff[i]);
       
    }
}

abstract class Sortable 
{  public abstract int compareTo(Sortable b);
}

class ArrayAlg
{  public static void shellSort(Sortable[] a)
   {  int n = a.length;
      int incr = n / 2;
      while (incr >= 1)
      {  for (int i = incr; i < n; i++)
         {  Sortable temp = a[i];
            int j = i;
            while (j >= incr 
               && temp.compareTo(a[j - incr]) < 0)
            {  a[j] = a[j - incr];
               j -= incr;
            }
            a[j] = temp;
         }
         incr /= 2;
      }
   }
}

class Employee extends Sortable
{  public Employee(String n, double s, Day d)
   {  name = n;
      salary = s;
      hireDate = d;
   }
    
   public void raiseSalary(double byPercent)
   {  salary *= 1 + byPercent / 100;
   }
    
   public String getName() { return name; }
    
   public double getSalary() { return salary; }
    
   public String toString()
   {  return name + " " + salary + " " + hireYear();
   }
    
   public int hireYear()
   {  return hireDate.getYear();
   }
    
   public int compareTo(Sortable b)
   {  Employee eb = (Employee)b;
      if (salary < eb.salary) return -1;
      if (salary > eb.salary) return 1;
      return 0;      
   }

   private String name;
   private double salary;
   private Day hireDate;
}



