/**
 * @version 1.00 07 Feb 1996 
 * @author Cay Horstmann
 */

import java.util.*;
import corejava.*;

public class EmployeeTest
{  public static void main(String[] args)
   {  Employee[] staff = new Employee[3];

      staff[0] = new Employee("Harry Hacker", 35000, 
         new Day(1989,10,1));
      staff[1] = new Employee("Carl Cracker", 75000, 
         new Day(1987,12,15));
      staff[2] = new Employee("Tony Tester", 38000, 
         new Day(1990,3,15));
      int i;
      for (i = 0; i < 3; i++) staff[i].raiseSalary(5);
      for (i = 0; i < 3; i++) staff[i].print();
   }
}

class Employee
{  public Employee(String n, double s, Day d)
   {  name = n;
      salary = s;
      hireDay = d;
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