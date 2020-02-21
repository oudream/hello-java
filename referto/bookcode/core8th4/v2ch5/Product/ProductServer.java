/**
 * @version 1.10 1999-08-21
 * @author Cay Horstmann
 */

import java.rmi.*;
import java.rmi.server.*;
import sun.applet.*;

public class ProductServer
{  public static void main(String args[])
   {  try
      {  System.out.println
            ("Constructing server implementations...");

         ProductImpl p1
            = new ProductImpl("Blackwell Toaster");
         ProductImpl p2
            = new ProductImpl("ZapXpress Microwave Oven");

         System.out.println
            ("Binding server implementations to registry...");

         Naming.rebind("toaster", p1);
         Naming.rebind("microwave", p2);

         System.out.println
            ("Waiting for invocations from clients...");
      }
      catch(Exception e)
      {  System.out.println("Error: " + e);
      }
   }
}

