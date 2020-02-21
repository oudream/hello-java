/**
 * @version 1.20 1999-10-02
 * @author Cay Horstmann
 */

import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.beans.beancontext.*;
import java.lang.reflect.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class SpinBean extends JPanel
   implements Serializable, BeanContextProxy
{  public SpinBean()
   {  setLayout(new GridLayout(2, 1));
      JButton plusButton = new JButton("+");
      JButton minusButton = new JButton("-");
      add(plusButton);
      add(minusButton);
      plusButton.addActionListener(
         new ActionListener()
         {  public void actionPerformed(ActionEvent evt)
            {  spin(1);
            }
         });
      minusButton.addActionListener(
         new ActionListener()
         {  public void actionPerformed(ActionEvent evt)
            {  spin(-1);
            }
         });

      childSupport =
         new BeanContextChildSupport()
         {  public void setBeanContext(BeanContext context)
               throws PropertyVetoException
            {  super.setBeanContext(context);
               setTracer(context);
            }
         };
   }

   public BeanContextChild getBeanContextProxy()
   {  return childSupport;
   }

   public void setBuddy(Component b, PropertyDescriptor p)
   {  buddy = b;
      prop = p;
   }

   public void spin(int increment)
   {  if (buddy == null) return;
      if (prop == null) return;
      Method readMethod = prop.getReadMethod();
      Method writeMethod = prop.getWriteMethod();
      try
      {  int value = ((Integer)readMethod.invoke(buddy,
            null)).intValue();

         if (tracer != null)
         {  String text = "spin: value=" + value
               + " increment=" + increment;
            Method logText = tracerClass.getMethod("logText",
               new Class[] { String.class });
            logText.invoke(tracer, new Object[] { text });
         }

         value += increment;
         writeMethod.invoke(buddy,
            new Object[] { new Integer(value) });
      }
      catch(Exception e)
      {
      }
   }

   public void setTracer(BeanContext context)
   {  try
      {  BeanContextServices services
           = (BeanContextServices)context;
         tracerClass = Class.forName
            ("sunw.demo.methodtracer.MethodTracer");
         if (services.hasService(tracerClass))
         {  BeanContextServiceRevokedListener revokedListener =
               new BeanContextServiceRevokedListener()
               {  public void serviceRevoked
                     (BeanContextServiceRevokedEvent event)
                  {  tracer = null;
                  }
               };
            tracer = services.getService(childSupport, this,
               tracerClass, null, revokedListener);
         }
      }
      catch (Exception exception)
      {  tracer = null;
      }
   }

   public Dimension getPreferredSize()
   {  return new Dimension(MINSIZE, MINSIZE);
   }

   private static final int MINSIZE = 20;
   private Component buddy;
   private PropertyDescriptor prop;
   private Object tracer;
   private Class tracerClass;
   private BeanContextChildSupport childSupport;
}
