/**
 * @version 1.00 1997-07-01
 * @author Cay Horstmann
 */

import java.util.*;

public class Win32RegKeyTest
{  public static void main(String[] args)
   {  Win32RegKey key = new Win32RegKey(
         Win32RegKey.HKEY_CURRENT_USER,
         "Software\\Microsoft\\MS Setup (ACME)\\User Info");

      key.setValue("Default user", "Bozo the clown");
      key.setValue("Lucky number", new Integer(13));
      key.setValue("Small primes", new byte[]
         { 2, 3, 5, 7, 11 });

      Enumeration enum = key.names();

      while (enum.hasMoreElements())
      {  String name = (String)enum.nextElement();
         System.out.print(name + " = ");

         Object value = key.getValue(name);

         if (value instanceof byte[])
         {  byte[] bvalue = (byte[])value;
            for (int i = 0; i < bvalue.length; i++)
               System.out.print((bvalue[i] & 0xFF) + " ");
         }
         else System.out.print(value);

         System.out.println();
      }
   }
}
