/**
 * @version 1.00 11 Mar 1997
 * @author Cay Horstmann
 */

import corejava.*;

public class PropertyTest
{  public static void main(String[] args)
   {  Employee harry = new Employee("Harry Hacker", 
         35000, new Day(1989, 10, 1));
      Employee carl = new Employee("Carl Cracker", 
         75000, new Day(1987, 12, 15));

      PropertyEditor editor = new PropertyEditor(
            new Property[]
            {  harry.getSalaryProperty(), 
               harry.getSalaryProperty(), 
               carl.getSalaryProperty(),
               carl.getSeniorityProperty()
            });

      System.out.println("Before:");
      harry.print();
      carl.print();
      System.out.println("Edit properties:");
      editor.editProperties();
      System.out.println("After:");
      harry.print();
      carl.print();
   }
}

interface Property
{  public String get();
   public void set(String s);
   public String name();
}

class PropertyEditor
{  public PropertyEditor(Property[] p)
   {  properties = p;
   }
   
   public void editProperties()
   {  while (true)
      {  for (int i = 0; i < properties.length; i++)
            System.out.println((i + 1) + ":" 
               + properties[i].name() 
               + "=" + properties[i].get());
         int n = Console.readInt
            ("Change which property? (0 to quit)");
         if (n == 0) return;
         if (0 < n && n <= properties.length)
         {  String value 
               = Console.readLine("New value:");
            properties[n - 1].set(value);
         }
      }
   }

   private Property[] properties;
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

   private class SalaryProperty implements Property
   {  public String name() 
      {  return name + "'s salary"; 
      }
      public String get() 
      {  return "" + salary; 
      }

      public void set(String s) 
      {  if (isSet) return; // can set once
         double sal = Double.parseDouble(s);
         if (sal > 0) 
         {  salary = sal;
            isSet = true;
         }
      }
      private boolean isSet = false;
   }
   
   public Property getSalaryProperty()
   {  return new SalaryProperty();
   }

   private class SeniorityProperty implements Property
   {  public String name() 
      {  return name + "'s years on the job"; 
      }
      public String get() 
      {  Day today = new Day();
         int years = today.daysBetween(hireDay) / 365;
         return "" + years; 
      }
      public void set(String s) 
      {} // can't set seniority
   }

   public Property getSeniorityProperty()
   {  return new SeniorityProperty();
   }

   private String name;
   private double salary;
   private Day hireDay;
}
