/**
 * @version 1.01 25 Oct 1997
 * @author Cay Horstmann
 */

import java.io.*;
import java.util.*;
import corejava.*;

class ObjectRefTest
{  public static void main(String[] args)
   {  try
      {
         Employee[] staff = new Employee[3];

         Employee harry = new Employee("Harry Hacker", 35000, 
            new Day(1989,10,1));
         staff[0] = harry;
         staff[1] = new Manager("Carl Cracker", 75000, 
            new Day(1987,12,15), harry);
         staff[2] = new Manager("Tony Tester", 38000, 
            new Day(1990,3,15), harry);

         ObjectOutputStream out = new ObjectOutputStream(new 
            FileOutputStream("employee.dat"));
         out.writeObject(staff);
         out.close();

         ObjectInputStream in =  new
            ObjectInputStream(new 
            FileInputStream("employee.dat"));
         Employee[] newStaff = (Employee[])in.readObject();

         for (int i = 0; i < newStaff.length; i++)
            newStaff[i].raiseSalary(100);
         for (int i = 0; i < newStaff.length; i++)
            newStaff[i].print();
      }
      catch(Exception e)
      {  e.printStackTrace();
         System.exit(1);
      }
   }
}

class Employee implements Serializable
{  public Employee(String n, double s, Day d)
   {  name = n;
      salary = s;
      hireDay = d;
   }

   public Employee() {}

   public void raiseSalary(double byPercent)
   {  salary *= 1 + byPercent / 100;
   }

   public int hireYear()
   {  return hireDay.getYear();
   }

   public void print()
   {  System.out.println(name + " " + salary 
         + " " + hireYear());
   }
   
   private String name;
   private double salary;
   private Day hireDay;
}

class Manager extends Employee
{  public Manager(String n, double s, Day d, Employee e)
   {  super(n, s, d);
      secretary = e;
   }
    
   public Manager() {}

   public void raiseSalary(double byPercent)
   {  // add 1/2% bonus for every year of service
      Day today = new Day();
      double bonus = 0.5 * (today.getYear() - hireYear());
      super.raiseSalary(byPercent + bonus);
   }

   public void print()
   {  super.print();
      System.out.print("Secretary: ");
      if (secretary != null) secretary.print();
   }
   
   private Employee secretary;    
}


