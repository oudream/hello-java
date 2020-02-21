/**
 * @version 1.00 1996-09-07
 * @author Cay Horstmann
 */

import java.rmi.*;
import java.rmi.server.*;

public class ProductImpl
   extends UnicastRemoteObject
   implements Product
{  public ProductImpl(String n)
      throws RemoteException
   {  name = n;
   }

   public String getDescription()
      throws RemoteException
   {  return "I am a " + name + ". Buy me!";
   }

   private String name;
}
