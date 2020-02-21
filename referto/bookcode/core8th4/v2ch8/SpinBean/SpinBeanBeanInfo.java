/**
 * @version 1.10 1997-10-27
 * @author Cay Horstmann
 */

import java.awt.*;
import java.beans.*;

public class SpinBeanBeanInfo extends SimpleBeanInfo
{  public BeanDescriptor getBeanDescriptor()
   {  return new BeanDescriptor(SpinBean.class,
         SpinBeanCustomizer.class);
   }
}
