/**
 * @version 1.01 07 Sep 1996 
 * @author Cay Horstmann
 */

import java.io.*;
import corejava.*;

public class RandomFileTest
{  public static void main(String[] args)
   {  Employee[] staff = new Employee[3];

      staff[0] = new Employee("Harry Hacker", 35000, 
         new Day(1989,10,1));
      staff[1] = new Employee("Carl Cracker", 75000, 
         new Day(1987,12,15));
      staff[2] = new Employee("Tony Tester", 38000, 
         new Day(1990,3,15));
      int i;      
      try
      {  DataOutputStream out = new DataOutputStream(new 
            FileOutputStream("employee.dat"));
         for (i = 0; i < staff.length; i++)
            staff[i].writeData(out);
         out.close();
      }
      catch(IOException e)
      {  System.out.print("Error: " + e);
         System.exit(1);
      }
      
      try
      {  RandomAccessFile in 
            = new RandomAccessFile("employee.dat", "r");   
         int n = (int)(in.length() / Employee.RECORD_SIZE);
         Employee[] newStaff = new Employee[n];

         for (i = n - 1; i >= 0; i--)
         {  newStaff[i] = new Employee();
            in.seek(i * Employee.RECORD_SIZE);
            newStaff[i].readData(in);
         }
         for (i = 0; i < newStaff.length; i++) 
            newStaff[i].print();
      }
      catch(IOException e)
      {  System.out.print("Error: " + e);
         System.exit(1);
      }
       
   }
}

class Employee
{  public Employee(String n, double s, Day d)
   {  name = n;
      salary = s;
      hireDay = d;
   }
   public Employee() {}
   public void print()
   {  System.out.println(name + " " + salary 
         + " " + hireYear());
   }
   public void raiseSalary(double byPercent)
   {  salary *= 1 + byPercent / 100;
   }
   public int hireYear()
   {  return hireDay.getYear();
   }
   public void writeData(DataOutput out) throws IOException
   {  DataIO.writeFixedString(name, NAME_SIZE, out);  
      out.writeDouble(salary);
      out.writeInt(hireDay.getYear());
      out.writeInt(hireDay.getMonth());
      out.writeInt(hireDay.getDay());
   }

   public void readData(DataInput in) throws IOException
   {  name = DataIO.readFixedString(NAME_SIZE, in);
      salary = in.readDouble();
      int y = in.readInt();
      int m = in.readInt();
      int d = in.readInt();
      hireDay = new Day(y, m, d);
   }

   public static final int NAME_SIZE = 40;
   public static final int RECORD_SIZE 
      = 2 * NAME_SIZE + 8 + 4 + 4 + 4;

   private String name;
   private double salary;
   private Day hireDay;
}

class DataIO
{  public static String readFixedString(int size, 
      DataInput in) throws IOException
   {  StringBuffer b = new StringBuffer(size);
      int i = 0;
      boolean more = true;
      while (more && i < size)
      {  char ch = in.readChar();
         i++;
         if (ch == 0) more = false;
         else b.append(ch);
      }
      in.skipBytes(2 * (size - i));
      return b.toString();
   }
   
   public static void writeFixedString(String s, int size, 
      DataOutput out) throws IOException
   {  int i;
      for (i = 0; i < size; i++)
      {  char ch = 0;
         if (i < s.length()) ch = s.charAt(i);
         out.writeChar(ch);
      }
   }
}

