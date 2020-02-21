/**
 * @version 1.00 1996-09-07
 * @author Cay Horstmann
 */

import java.rmi.*;
import java.util.*;

public interface Warehouse
   extends Remote
{  public Vector find(Customer c)
      throws RemoteException;
}
