/**
 * @version 1.10 1997-10-27
 * @author Cay Horstmann
 */

import java.awt.*;
import java.beans.*;

public class ChartBeanBeanInfo extends SimpleBeanInfo
{  public BeanDescriptor getBeanDescriptor()
   {  return new BeanDescriptor(ChartBean.class,
         ChartBeanCustomizer.class);
   }

   public Image getIcon(int iconType)
   {  String name = "";
      if (iconType == BeanInfo.ICON_COLOR_16x16)
         name = "COLOR_16x16";
      else if (iconType == BeanInfo.ICON_COLOR_32x32)
         name = "COLOR_32x32";
      else if (iconType == BeanInfo.ICON_MONO_16x16)
         name = "MONO_16x16";
      else if (iconType == BeanInfo.ICON_MONO_32x32)
         name = "MONO_32x32";
      else return null;
      return loadImage("ChartBean_" + name + ".gif");
   }
}
