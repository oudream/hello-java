/**
 * @version 1.00 1996-09-07
 * @author Cay Horstmann
 */

import java.rmi.*;
import java.rmi.server.*;

public class ProductClient
{  public static void main(String[] args)
   {  System.setSecurityManager(new RMISecurityManager());
      String url = "rmi://localhost/";
         // change to "rmi://yourserver.com/"
         // when server runs on remote machine
         // yourserver.com
      try
      {  Product c1 = (Product)Naming.lookup(url + "toaster");
         Product c2 = (Product)Naming.lookup(url + "microwave");
         System.out.println(c1.getDescription());
         System.out.println(c2.getDescription());
      }
      catch(Exception e)
      {  System.out.println("Error " + e);
      }
      System.exit(0);
   }
}
