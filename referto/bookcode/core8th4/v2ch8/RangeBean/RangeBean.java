/**
 * @version 1.20 1999-09-26
 * @author Cay Horstmann
 */

import java.awt.*;
import java.beans.*;
import java.io.*;
import javax.swing.*;

public class RangeBean extends JPanel
   implements VetoableChangeListener, Serializable
{  public RangeBean()
   {  add(new JLabel("From"));
      add(from);
      add(new JLabel("To"));
      add(to);

      from.addVetoableChangeListener(this);
      to.addVetoableChangeListener(this);
   }

   public void vetoableChange(PropertyChangeEvent event)
      throws PropertyVetoException
   {  int v = ((Integer)event.getNewValue()).intValue();
      if (event.getSource() == from && v > to.getValue())
         throw new PropertyVetoException("from > to", event);
      if (event.getSource() == to && v < from.getValue())
         throw new PropertyVetoException("to < from", event);
   }

   public int getFrom() { return from.getValue(); }
   public int getTo() { return to.getValue(); }

   public void setFrom(int v) throws PropertyVetoException
   {  from.setValue(v);
   }

   public void setTo(int v) throws PropertyVetoException
   {  to.setValue(v);
   }

   private IntTextBean from = new IntTextBean();
   private IntTextBean to = new IntTextBean();
}
