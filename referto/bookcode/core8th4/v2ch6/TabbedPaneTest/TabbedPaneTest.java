/**
 * @version 1.00 1999-07-17
 * @author Cay Horstmann
 */

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;

public class TabbedPaneTest
{  public static void main(String[] args)
   {  JFrame frame = new TabbedPaneFrame();
      frame.show();
   }
}

class TabbedPaneFrame extends JFrame
   implements ChangeListener
{  public TabbedPaneFrame()
   {  setTitle("TabbedPaneTest");
      setSize(400, 300);
      addWindowListener(new WindowAdapter()
         {  public void windowClosing(WindowEvent e)
            {  System.exit(0);
            }
         } );

      tabbedPane = new JTabbedPane();
      tabbedPane.addChangeListener(this);

      /* we set the components to null and delay their
         loading until the tab is shown for the first time
      */

      ImageIcon icon = new ImageIcon("yellow-ball.gif");

      tabbedPane.addTab("Mercury", icon, null);
      tabbedPane.addTab("Venus", icon, null);
      tabbedPane.addTab("Earth", icon, null);
      tabbedPane.addTab("Mars", icon, null);
      tabbedPane.addTab("Jupiter", icon, null);
      tabbedPane.addTab("Saturn", icon, null);
      tabbedPane.addTab("Uranus", icon, null);
      tabbedPane.addTab("Neptune", icon, null);
      tabbedPane.addTab("Pluto", icon, null);

      getContentPane().add(tabbedPane, "Center");
   }

   public void stateChanged(ChangeEvent event)
   {  JTabbedPane pane = (JTabbedPane)event.getSource();

      // check if this tab still has a null component

      if (pane.getSelectedComponent() == null)
      {  // set the component to the image icon

         int n = pane.getSelectedIndex();
         String title = pane.getTitleAt(n);
         ImageIcon planetIcon = new ImageIcon(title + ".gif");
         pane.setComponentAt(n, new JLabel(planetIcon));

         // indicate that this tab has been visited--just for fun

         pane.setIconAt(n, new ImageIcon("red-ball.gif"));
      }
   }

   private JTabbedPane tabbedPane;
}

