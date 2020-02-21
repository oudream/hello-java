/**
 * @version 1.20 1999-09-26
 * @author Cay Horstmann
 */

import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.io.*;
import javax.swing.*;
import javax.swing.text.*;

public class IntTextBean extends JTextField
   implements Serializable
{  public IntTextBean()
   {  this(0, 10);
   }

   public IntTextBean(int defval, int size)
   {  super("" + defval, size);
      addFocusListener(new FocusListener()
         {  public void focusGained(FocusEvent event)
            {  if (!event.isTemporary())
               {  lastValue = getValue();
               }
            }
            public void focusLost(FocusEvent event)
            {  if (!event.isTemporary())
               {  editComplete();
               }
            }
         });
   }

   public void editComplete()
   {  Integer oldValue = new Integer(lastValue);
      Integer newValue = new Integer(getValue());
      try
      {  fireVetoableChange("value", oldValue, newValue);
         // survived, therefore no veto
         firePropertyChange("value", oldValue, newValue);
      }
      catch(PropertyVetoException e)
      {  // someone didn't like it
         JOptionPane.showMessageDialog(this, "" + e,
            "Input Error", JOptionPane.WARNING_MESSAGE);
         setText("" + lastValue);
         requestFocus();
            // doesn't work in all JDK versions--see bug #4128659
      }
   }

   public int getValue()
   {  try
      {  return Integer.parseInt(getText());
      }
      catch (NumberFormatException exception)
      {  return 0;
      }
   }

   public void setValue(int v) throws PropertyVetoException
   {  Integer oldValue = new Integer(getValue());
      Integer newValue = new Integer(v);
      fireVetoableChange("value", oldValue, newValue);
      // survived, therefore no veto
      setText("" + v);
      firePropertyChange("value", oldValue, newValue);
   }

   protected Document createDefaultModel()
   {  return new IntTextDocument();
   }

   public Dimension getMinimumSize()
   {  return new Dimension(XMINSIZE, YMINSIZE);
   }

   private int lastValue;
   private static final int XMINSIZE = 50;
   private static final int YMINSIZE = 20;
}

class IntTextDocument extends PlainDocument
{  public void insertString(int offs, String str,
      AttributeSet a)
      throws BadLocationException
   {  if (str == null) return;
      String oldString = getText(0, getLength());
      String newString = oldString.substring(0, offs)
         + str + oldString.substring(offs);
      try
      {  Integer.parseInt(newString + "0");
         super.insertString(offs, str, a);
      }
      catch(NumberFormatException e)
      {
      }
   }
}

