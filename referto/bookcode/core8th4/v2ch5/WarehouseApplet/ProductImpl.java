/**
 * @version 1.00 1996-09-07
 * @author Cay Horstmann
 */

import java.rmi.*;
import java.rmi.server.*;
import java.awt.*;

public class ProductImpl
   extends UnicastRemoteObject
   implements Product
{  public ProductImpl(String n, int s, int age1, int age2,
      String h, String i)
      throws RemoteException
   {  name = n;
      ageLow = age1;
      ageHigh = age2;
      sex = s;
      hobby = h;
      imageFile = i;
   }

   public boolean match(Customer c) // local method
   {  if (c.getAge() < ageLow || c.getAge() > ageHigh)
      return false;
      if (!c.hasHobby(hobby)) return false;
      if ((sex & c.getSex()) == 0) return false;
      return true;
   }

   public String getDescription() throws RemoteException
   {  return "I am a " + name + ". Buy me!";
   }

   public String getImageFile() throws RemoteException
   {  return imageFile;
   }

   private String name;
   private int ageLow;
   private int ageHigh;
   private int sex;
   private String hobby;
   private String imageFile;
}
