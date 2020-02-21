/**
 * @version 1.20 25 Jun 1998
 * @author Cay Horstmann
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class ColorAction extends AbstractAction
{  public ColorAction(String name, Icon icon, 
      Color c, Component comp)
   {  putValue(Action.NAME, name);
      putValue(Action.SMALL_ICON, icon);
      putValue("Color", c);
      target = comp;
   }
   
   public void actionPerformed(ActionEvent evt)
   {  Color c = (Color)getValue("Color");
      target.setBackground(c);
      target.repaint();
   }
   
   private Component target;
}

class ActionButton extends JButton
{  public ActionButton(Action a)
   {  setText((String)a.getValue(Action.NAME));
      Icon icon = (Icon)a.getValue(Action.SMALL_ICON);
      if (icon != null)
         setIcon(icon);
      addActionListener(a);
   }
}

class SeparateGUIFrame extends JFrame
{  public SeparateGUIFrame()
   {  setTitle("SeparateGUITest");
      setSize(300, 200);
      addWindowListener(new WindowAdapter()
         {  public void windowClosing(WindowEvent e)
            {  System.exit(0);
            }
         } );


      JPanel panel = new JPanel();

      Action blueAction = new ColorAction("Blue", 
         new ImageIcon("blue-ball.gif"),
         Color.blue, panel);
      Action yellowAction = new ColorAction("Yellow", 
         new ImageIcon("yellow-ball.gif"),
         Color.yellow, panel);
      Action redAction = new ColorAction("Red", 
         new ImageIcon("red-ball.gif"),
         Color.red, panel);
      
      panel.add(new ActionButton(yellowAction));
      panel.add(new ActionButton(blueAction));
      panel.add(new ActionButton(redAction));
      
      panel.registerKeyboardAction(yellowAction, 
         KeyStroke.getKeyStroke(KeyEvent.VK_Y, 0),
         JComponent.WHEN_IN_FOCUSED_WINDOW);
      panel.registerKeyboardAction(blueAction, 
         KeyStroke.getKeyStroke(KeyEvent.VK_B, 0),
         JComponent.WHEN_IN_FOCUSED_WINDOW);
      panel.registerKeyboardAction(redAction, 
         KeyStroke.getKeyStroke(KeyEvent.VK_R, 0),
         JComponent.WHEN_IN_FOCUSED_WINDOW);      
      
      Container contentPane = getContentPane();
      contentPane.add(panel);

      JMenu m = new JMenu("Color");
      m.add(yellowAction);
      m.add(blueAction);
      m.add(redAction);
      JMenuBar mbar = new JMenuBar();
      mbar.add(m);
      setJMenuBar(mbar);
   }
}

public class SeparateGUITest
{  public static void main(String[] args)
   {  JFrame frame = new SeparateGUIFrame();
      frame.show();  
   }
}
