/**
 * @version 1.00 1999-08-21
 * @author Cay Horstmann
 */

#include <iostream.h>
#include "SysProp.hh"

CORBA::Object_ptr getObjectReference(CORBA::ORB_ptr orb,
   const char serviceName[])
{  CosNaming::NamingContext_var rootContext;

   try
   {  // Obtain a reference to the root context of the Name service:
      CORBA::Object_var initServ;
      initServ = orb->resolve_initial_references("NameService");

      // Narrow the object returned by resolve_initial_references()
      // to a CosNaming::NamingContext object:
      rootContext = CosNaming::NamingContext::_narrow(initServ);
      if (CORBA::is_nil(rootContext))
      {  cerr << "Failed to narrow naming context." << endl;
         return CORBA::Object::_nil();
      }
   }
   catch(CORBA::ORB::InvalidName&)
   {  cerr << "Name service does not exist." << endl;
      return CORBA::Object::_nil();
   }

   // Create a name object, containing the name corejava/SysProp:
   CosNaming::Name name;
   name.length(1);

   name[0].id   = serviceName;
   name[0].kind = "Object";

   CORBA::Object_ptr obj;
   try
   {  // Resolve the name to an object reference, and assign the reference
      // returned to a CORBA::Object:
      obj = rootContext->resolve(name);
   }
   catch(CosNaming::NamingContext::NotFound&)
   {  // This exception is thrown if any of the components of the
      // path [contexts or the object] aren't found:
      cerr << "Context not found." << endl;
      return CORBA::Object::_nil();
   }
   return obj;
}

void callServer(CORBA::Object_ptr obj)
{  SysProp_var sysProp = SysProp::_narrow(obj);

  if (CORBA::is_nil(sysProp))
  {  cerr << "hello: cannot invoke on a nil object reference.\n" << endl;
     return;
  }

  CORBA::String_var key = "java.vendor";
  CORBA::String_var value = sysProp->getProperty(key);

  cerr << key << "=" << value << endl;
}

int main (int argc, char *argv[])
{  CORBA::ORB_ptr orb = CORBA::ORB_init(argc, argv, "omniORB2");
   CORBA::BOA_ptr boa = orb->BOA_init(argc, argv, "omniORB2_BOA");

   try
   {  CORBA::Object_var obj = getObjectReference(orb, "SysProp");
      callServer(obj);
   }
   catch (CORBA::COMM_FAILURE&)
   {  cerr << "COMM_FAILURE" << endl;
   }

   return 0;
}


