/**
 * @version 1.00 1996-09-07
 * @author Cay Horstmann
 */

import java.rmi.*;

public interface Product extends Remote
{  String getDescription()
      throws RemoteException;
}
