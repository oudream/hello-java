/**
 * @version 1.00 1999-07-07
 * @author Cay Horstmann
 */

import java.util.*;

public class MapTest
{  public static void main(String[] args)
   {  Map staff = new HashMap();
      staff.put("144-25-5464", new Employee("Angela Hung"));
      staff.put("567-24-2546", new Employee("Harry Hacker"));
      staff.put("157-62-7935", new Employee("Gary Cooper"));
      staff.put("456-62-5527", new Employee("Francesca Cruz"));

      // print all entries

      System.out.println(staff);

      // remove an entry

      staff.remove("567-24-2546");

      // replace an entry

      staff.put("456-62-5527", new Employee("Francesca Miller"));

      // look up a value

      System.out.println(staff.get("157-62-7935"));

      // iterate through all entries

      Set entries = staff.entrySet();
      Iterator iter = entries.iterator();
      while (iter.hasNext())
      {  Map.Entry entry = (Map.Entry)iter.next();
         Object key = entry.getKey();
         Object value = entry.getValue();
         System.out.println("key=" + key + ", value=" + value);
      }
   }
}

class Employee
{  public Employee(String n)
   {  name = n;
      salary = 0;
   }

   public String toString()
   {  return "[name=" + name + ", salary=" + salary + "]";
   }

   public void setSalary(double s)
   {  salary = s;
   }

   private String name;
   private double salary;
}