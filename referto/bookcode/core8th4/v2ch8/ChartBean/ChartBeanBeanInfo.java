/**
 * @version 1.10 1997-10-27
 * @author Cay Horstmann
 */

import java.beans.*;

public class ChartBeanBeanInfo extends SimpleBeanInfo
{  public PropertyDescriptor[] getPropertyDescriptors()
   {  try
      {  PropertyDescriptor titlePositionDescriptor
            = new PropertyDescriptor("titlePosition",
               ChartBean.class);
         titlePositionDescriptor.setPropertyEditorClass
            (TitlePositionEditor.class);
         PropertyDescriptor inverseDescriptor
            = new PropertyDescriptor("inverse",
               ChartBean.class);
         inverseDescriptor.setPropertyEditorClass
            (InverseEditor.class);

         return new PropertyDescriptor[]
         {  new PropertyDescriptor("title",
               ChartBean.class),
            titlePositionDescriptor,
            new PropertyDescriptor("values",
               ChartBean.class),
            new PropertyDescriptor("graphColor",
               ChartBean.class),
            inverseDescriptor
         };
      }
      catch(IntrospectionException e)
      {  System.out.println("Error: " + e);
         return null;
      }
   }

   static
   {  PropertyEditorManager.registerEditor(double[].class,
         DoubleArrayEditor.class);
   }
}
