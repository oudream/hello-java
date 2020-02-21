/**
 * @version 1.00 1996-09-07
 * @author Cay Horstmann
 */

import java.rmi.*;
import java.awt.*;

public interface Product
   extends Remote
{  String getDescription()
      throws RemoteException;
   String getImageFile()
      throws RemoteException;

   static final int MALE = 1;
   static final int FEMALE = 2;
   static final int BOTH = MALE + FEMALE;
}

