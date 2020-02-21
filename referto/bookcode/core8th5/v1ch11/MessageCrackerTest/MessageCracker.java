/**
 * @version 1.20 07 May 1997
 * @author Cay Horstmann
 */

import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.*;

public class MessageCracker
   implements MouseListener, ComponentListener, 
   FocusListener, KeyListener, ContainerListener,
   WindowListener, TextListener, AdjustmentListener,
   ActionListener, ItemListener
{  public void add(Component c)
   {  Class[] interfaces = getClass().getInterfaces();
      
      for (int i = 0; i < interfaces.length; i++)
      {  addListener(c, interfaces[i]);
      }
      
      if (c instanceof Container)
      {  Component[] a = ((Container)c).getComponents();
         for (int i = 0; i < a.length; i++)
         add(a[i]);
      }
   }
   
   public void addListener(Component c, Class iface)
   {  /* strip off package name from interface */
      String name = iface.getName();
      name = name.substring(name.lastIndexOf('.') + 1);
      /* name is an XXXListener
         find out whether c supports a method addXXXListener(XXXListener)
      */
      try
      {  Method listenerAddMethod = c.getClass().getMethod("add" + name,
            new Class[] { iface });
         listenerAddMethod.invoke(c, new Object[] { this });
      }
      catch(Exception e) {}
      /* lots of things can go wrong in the getMethod and invoke calls
         in that case, we simply don't add the listener
      */
   }

   public void mouseClicked(MouseEvent e) 
   {  System.out.println(e);
   }
   public void mouseEntered(MouseEvent e) 
   {  System.out.println(e);
   }
   public void mouseExited(MouseEvent e) 
   {  System.out.println(e);
   }
   public void mousePressed(MouseEvent e) 
   {  System.out.println(e);
   }
   public void mouseReleased(MouseEvent e) 
   {  System.out.println(e);
   }

   public void componentHidden(ComponentEvent e) 
   {  System.out.println(e);
   }
   public void componentMoved(ComponentEvent e) 
   {  System.out.println(e);
   }
   public void componentResized(ComponentEvent e) 
   {  System.out.println(e);
   }
   public void componentShown(ComponentEvent e)
   {  System.out.println(e);
   }

   public void focusGained(FocusEvent e) 
   {  System.out.println(e);
   }
   public void focusLost(FocusEvent e) 
   {  System.out.println(e);
   }

   public void keyPressed(KeyEvent e) 
   {  System.out.println(e);
   }
   public void keyReleased(KeyEvent e) 
   {  System.out.println(e);
   }
   public void keyTyped(KeyEvent e) 
   {  System.out.println(e);
   }

   public void windowActivated(WindowEvent e) 
   {  System.out.println(e);
   }
   public void windowClosed(WindowEvent e) 
   {  System.out.println(e);
   }
   public void windowClosing(WindowEvent e) 
   {  System.out.println(e);
   }
   public void windowDeactivated(WindowEvent e) 
   {  System.out.println(e);
   }
   public void windowDeiconified(WindowEvent e) 
   {  System.out.println(e);
   }
   public void windowIconified(WindowEvent e) 
   {  System.out.println(e);
   }
   public void windowOpened(WindowEvent e)
   {  System.out.println(e);
   }

   public void componentAdded(ContainerEvent e) 
   {  System.out.println(e);
   }
   public void componentRemoved(ContainerEvent e) 
   {  System.out.println(e);
   }

   public void textValueChanged(TextEvent e) 
   {  System.out.println(e);
   }

   public void adjustmentValueChanged(AdjustmentEvent e) 
   {  System.out.println(e);
   }

   public void actionPerformed(ActionEvent e) 
   {  System.out.println(e);
   }

   public void itemStateChanged(ItemEvent e) 
   {  System.out.println(e);
   }
}

