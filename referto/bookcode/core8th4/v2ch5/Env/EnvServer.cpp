/**
 * @version 1.00 1999-08-21
 * @author Cay Horstmann
 */

#include <iostream.h>
#include "omnithread.h"

#include "Env.hh"

class EnvImpl : public virtual _sk_Env
{
public:
  virtual ~EnvImpl() {}
  virtual char* getenv(const char *name);
};

char* EnvImpl::getenv(const char *name)
{  char* value = ::getenv(name);
   return CORBA::string_dup(value);
}

void bindObjectToName(CORBA::ORB_ptr orb,
   const char name[], CORBA::Object_ptr obj)
{  CosNaming::NamingContext_var rootContext;

   try
   {  // Obtain a reference to the root context of the Name service:
      CORBA::Object_var initServ
         = orb->resolve_initial_references("NameService");

      rootContext = CosNaming::NamingContext::_narrow(initServ);

      if (CORBA::is_nil(rootContext))
      {  cerr << "Failed to narrow naming context." << endl;
         return;
      }
   }
   catch(CORBA::ORB::InvalidName& ex)
   {  cerr << "Service does not exist." << endl;
      return;
   }

   try
   {  // Bind a context called "corejava" to the root context:

      CosNaming::Name contextName;
      contextName.length(1);
      contextName[0].id   = "corejava";
      contextName[0].kind = "Context";

      CosNaming::NamingContext_var corejavaContext;
      try
      {  // Bind the context to root, and assign testContext to it:
         corejavaContext = rootContext->bind_new_context(contextName);
      }
      catch(CosNaming::NamingContext::AlreadyBound&)
      {  // If the context already exists, this exception will be raised.
         // In this case, just resolve the name and assign testContext
         // to the object returned:
         CORBA::Object_var contextObj
            = rootContext->resolve(contextName);
         corejavaContext = CosNaming::NamingContext::_narrow(contextObj);
         if (CORBA::is_nil(corejavaContext))
         {  cerr << "Failed to narrow corejava context." << endl;
            return;
         }
      }

      // Bind obj to name in the corejava context
      CosNaming::Name objectName;
      objectName.length(1);
      objectName[0].id   = name;
      objectName[0].kind = "Object";

      corejavaContext->rebind(objectName, obj);
   }
   catch (CORBA::COMM_FAILURE&)
   {  cerr << "COMM_FAILURE" << endl;
   }
}

int main(int argc, char *argv[])
{  cout << "Creating and initializing the ORB..." << endl;

   CORBA::ORB_ptr orb = CORBA::ORB_init(argc, argv, "omniORB2");
   CORBA::BOA_ptr boa = orb->BOA_init(argc,argv,"omniORB2_BOA");

   cout << "Registering server implementation with the ORB..." << endl;

   EnvImpl* impl = new EnvImpl();
   impl->_obj_is_ready(boa);

   CORBA::String_var s = orb->object_to_string(impl);
   cout << s << endl;

   cout << "Binding server implementation to name service..." << endl;

   Env_var env = impl->_this();
   bindObjectToName(orb, "Env", env);

   cout << "Waiting for invocations from clients..." << endl;

   boa->impl_is_ready();

   return 0;
}
