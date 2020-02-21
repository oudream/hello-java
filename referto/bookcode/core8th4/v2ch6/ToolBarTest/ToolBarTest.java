/**
 * @version 1.00 1999-07-17
 * @author Cay Horstmann
 */

import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import javax.swing.*;

public class ToolBarTest
{  public static void main(String[] args)
   {  JFrame frame = new ToolBarFrame();
      frame.show();
   }
}

/* the color action sets the background of its target to a
   given color
*/

class ColorAction extends AbstractAction
{  public ColorAction(String name, Icon icon,
      Color c, Component t)
   {  putValue(Action.NAME, name);
      putValue(Action.SMALL_ICON, icon);
      putValue(Action.SHORT_DESCRIPTION, name + " background");
      putValue("Color", c);
      target = t;
   }

   public void actionPerformed(ActionEvent evt)
   {  Color c = (Color)getValue("Color");
      target.setBackground(c);
      target.repaint();
   }

   private Component target;
}

/* the tool bar button is a button with an icon and no text
   suitable for addition into a tool bar. The tool tip is set
   to the short description of the action, or to the name
   if the short description is not available
*/

class ToolBarButton extends JButton
{  public ToolBarButton(Action a)
   {  super((Icon)a.getValue(Action.SMALL_ICON));

      String toolTip
         = (String)a.getValue(Action.SHORT_DESCRIPTION);
      if (toolTip == null)
         toolTip = (String)a.getValue(Action.NAME);
      if (toolTip != null)
         setToolTipText(toolTip);
      addActionListener(a);
   }
}

class ToolBarFrame extends JFrame
{  public ToolBarFrame()
   {  setTitle("ToolBarTest");
      setSize(300, 200);
      addWindowListener(new WindowAdapter()
         {  public void windowClosing(WindowEvent e)
            {  System.exit(0);
            }
         } );

      // add a panel for color change

      Container contentPane = getContentPane();
      JPanel panel = new JPanel();
      contentPane.add(panel, "Center");

      // set up actions

      Action blueAction = new ColorAction("Blue",
         new ImageIcon("blue-ball.gif"),
         Color.blue, panel);
      Action yellowAction = new ColorAction("Yellow",
         new ImageIcon("yellow-ball.gif"),
         Color.yellow, panel);
      Action redAction = new ColorAction("Red",
         new ImageIcon("red-ball.gif"),
         Color.red, panel);

      Action exitAction
         = new AbstractAction("Exit", new ImageIcon("exit.gif"))
            {  public void actionPerformed(ActionEvent event)
               {  System.exit(0);
               }
            };

      // populate tool bar

      JToolBar bar = new JToolBar();
      bar.add(new ToolBarButton(blueAction));
      bar.add(new ToolBarButton(yellowAction));
      bar.add(new ToolBarButton(redAction));
      bar.addSeparator();
      bar.add(new ToolBarButton(exitAction));
      contentPane.add(bar, "North");

      // populate menu

      JMenu menu = new JMenu("Color");
      menu.add(yellowAction);
      menu.add(blueAction);
      menu.add(redAction);
      menu.add(exitAction);
      JMenuBar menuBar = new JMenuBar();
      menuBar.add(menu);
      setJMenuBar(menuBar);
   }
}
