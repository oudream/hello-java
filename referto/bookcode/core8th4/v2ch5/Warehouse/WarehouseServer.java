/**
 * @version 1.10 1999-08-23
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
         Product.BOTH, 18, 200, "Household"));
      w.add(new ProductImpl("ZapXpress Microwave Oven",
         Product.BOTH, 18, 200, "Household"));
      w.add(new ProductImpl("Jimbo After Shave",
         Product.MALE, 18, 200, "Beauty"));
      w.add(new ProductImpl("Handy Hand Grenade",
         Product.MALE, 20, 60, "Gardening"));
      w.add(new ProductImpl("DirtDigger Steam Shovel",
         Product.MALE, 20, 60, "Gardening"));
      w.add(new ProductImpl("U238 Weed Killer",
         Product.BOTH, 20, 200, "Gardening"));
      w.add(new ProductImpl("Van Hope Cosmetic Set",
         Product.FEMALE, 15, 45, "Beauty"));
      w.add(new ProductImpl("Persistent Java Fragrance",
         Product.FEMALE, 15, 45, "Beauty"));
      w.add(new ProductImpl("Rabid Rodent Computer Mouse",
         Product.BOTH, 6, 40, "Computers"));
      w.add(new ProductImpl
         ("Learn Bad Java Habits in 21 Days Book",
         Product.BOTH, 20, 200, "Computers"));
      w.add(new ProductImpl("My first Espresso Maker",
         Product.FEMALE, 6, 10, "Household"));
      w.add(new ProductImpl("JavaJungle Eau de Cologne",
         Product.FEMALE, 20, 200, "Beauty"));
      w.add(new ProductImpl("Fast/Wide SCSI Coffee Maker",
         Product.MALE, 20, 50, "Computers"));
      w.add(new ProductImpl("ClueLess Network Computer",
         Product.BOTH,6, 200, "Computers"));
   }
}

