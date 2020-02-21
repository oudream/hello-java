/**
 * @version 1.00 1996-09-07
 * @author Cay Horstmann
 */

import java.rmi.*;
import java.rmi.server.*;

public class WarehouseServer
{  public static void main(String[] args)
   {  try
      {  System.out.println
            ("Constructing server implementations...");

         WarehouseImpl w = new WarehouseImpl();
         fillWarehouse(w);

         System.out.println
            ("Binding server implementations to registry...");

         Naming.rebind("central_warehouse", w);

         System.out.println
            ("Waiting for invocations from clients...");
      }
      catch(Exception e)
      {  System.out.println("Error: " + e);
      }
   }

   public static void fillWarehouse(WarehouseImpl w)
      throws RemoteException
   {  w.add(new ProductImpl("Blackwell Toaster",
         Product.BOTH, 18, 200, "Household", "toaster.jpg"));
      w.add(new ProductImpl("Jimbo After Shave",
         Product.MALE, 18, 200, "Beauty", "shave.jpg"));
      w.add(new ProductImpl("U238 Weed Killer",
         Product.BOTH, 20, 200, "Gardening", "weed.jpg"));
      w.add(new ProductImpl("Rabid Rodent Computer Mouse",
         Product.BOTH, 6, 40, "Computers", "rodent.jpg"));
      w.add(new ProductImpl
         ("Learn Bad Java Habits in 21 Days Book",
         Product.BOTH, 20, 200, "Computers", "book.jpg"));
      w.add(new ProductImpl("JavaJungle Eau de Cologne",
         Product.FEMALE, 20, 200, "Beauty", "cologne.jpg"));
      w.add(new ProductImpl("Fast/Wide SCSI Coffee Maker",
         Product.MALE, 20, 50, "Computers", "coffee.jpg"));
      w.add(new ProductImpl("ClueLess Network Computer",
         Product.BOTH, 6, 200, "Computers", "computer.jpg"));
      w.add(new ProductImpl("Digging Dinosaur",
         Product.BOTH, 6, 200, "Gardening", "dino.jpg"));
      w.add(new ProductImpl("Fantastic Fan",
         Product.BOTH, 6, 200, "Household", "fan.jpg"));
      w.add(new ProductImpl("Japanese Cat",
         Product.BOTH, 6, 200, "Gardening", "cat.jpg"));
      w.add(new ProductImpl("Ms. Frizzle Curling Iron",
         Product.FEMALE, 6, 200, "Beauty", "curl.jpg"));
   }
}

