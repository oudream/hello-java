/**
 * @version 1.00 1996-09-07
 * @author Cay Horstmann
 */

import java.rmi.*;
import java.util.*;
import java.rmi.server.*;


public class WarehouseImpl
   extends UnicastRemoteObject
   implements Warehouse
{  public WarehouseImpl()
      throws RemoteException
   {  products = new Vector();
   }

   public synchronized void add(ProductImpl p) // local method
   {  products.add(p);
   }

   public synchronized Vector find(Customer c)
      throws RemoteException
   {  Vector result = new Vector();
      for (int i = 0; i < products.size(); i++)
      {  ProductImpl p = (ProductImpl)products.get(i);
         if (p.match(c)) result.add(p);
      }
      result.add(new ProductImpl("Core Java Book",
         0, 200, Product.BOTH, ""));
      c.reset();
      return result;
   }

   private Vector products;
}
