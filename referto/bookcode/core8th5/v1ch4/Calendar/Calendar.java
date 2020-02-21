/**
 * @version 1.20 27 Mar 1998 
 * @author Cay Horstmann
 */
 
import corejava.*;

public class Calendar
{  public static void main(String[] args)
   {  int m;
      int y;  
      if (args.length == 2)
      {  m = Integer.parseInt(args[0]);
         y = Integer.parseInt(args[1]);
      }
      else
      {  Day today = new Day(); // today's date
         m = today.getMonth();
         y = today.getYear();
      }

      Day d = new Day(y, m, 1); // start date of the month

      System.out.println(m + " " + y);
      System.out.println("Sun Mon Tue Wed Thu Fri Sat");
      for (int i = Day.SUNDAY; i < d.weekday(); i++ ) 
         System.out.print("    ");
      while (d.getMonth() == m)
      {  if (d.getDay() < 10) System.out.print(" ");
         System.out.print(d.getDay());
         if (d.weekday() == Day.SATURDAY) 
            System.out.println();
         else 
            System.out.print("  ");
         d.advance(1);
      }
      if (d.weekday() != Day.SUNDAY) System.out.println();
   }
}
