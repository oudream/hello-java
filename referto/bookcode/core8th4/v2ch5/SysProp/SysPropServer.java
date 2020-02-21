/**
 * @version 1.00 1999-08-21
 * @author Cay Horstmann
 */

import org.omg.CosNaming.*;
import org.omg.CORBA.*;

class SysPropImpl extends _SysPropImplBase
{  public String getProperty(String key)
   {  return System.getProperty(key);
   }
}

public class SysPropServer
{  public static void main(String args[])
   {  try
      {  System.out.println("Creating and initializing the ORB...");

         ORB orb = ORB.init(args, null);

         System.out.println
            ("Registering server implementation with the ORB...");

         SysPropImpl impl = new SysPropImpl();
         orb.connect(impl);
         System.out.println(orb.object_to_string(impl));

         org.omg.CORBA.Object namingContextObj =
             orb.resolve_initial_references("NameService");
         NamingContext namingContext
            = NamingContextHelper.narrow(namingContextObj);

         NameComponent[] path =
            {  new NameComponent("SysProp", "Object")
            };

         System.out.println
            ("Binding server implementation to name service...");

         namingContext.rebind(path, impl);

         System.out.println
            ("Waiting for invocations from clients...");

         java.lang.Object sync = new java.lang.Object();
         synchronized (sync)
         {  sync.wait();
         }
      }
      catch (Exception e)
      {   System.err.println("Error: " + e);
          e.printStackTrace(System.out);
      }
   }
}
