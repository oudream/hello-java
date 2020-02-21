/**
 * @version 1.20 25 Jun 1998
 * @author Cay Horstmann
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class SketchPanel extends JPanel
   implements KeyListener
{  public SketchPanel()
   {  addKeyListener(this);
   }

   public void keyPressed(KeyEvent evt)
   {  int keyCode = evt.getKeyCode();
      int modifiers = evt.getModifiers();
      int d;
      if ((modifiers & InputEvent.SHIFT_MASK) != 0) 
         d = 5; 
      else 
         d = 1;
      if (keyCode == KeyEvent.VK_LEFT) add(-d, 0);
      else if (keyCode == KeyEvent.VK_RIGHT) add(d, 0);
      else if (keyCode == KeyEvent.VK_UP) add(0, -d);
      else if (keyCode == KeyEvent.VK_DOWN) add(0, d);
   }
   
   public void keyReleased(KeyEvent evt)
   {}

   public void keyTyped(KeyEvent evt)
   {  char keyChar = evt.getKeyChar();
      int d;
      if (Character.isUpperCase(keyChar))
      {  d = 5; 
         keyChar = Character.toLowerCase(keyChar);
      }
      else 
         d = 1;
      if (keyChar == 'h') add(-d, 0);
      else if (keyChar == 'l') add(d, 0);
      else if (keyChar == 'k') add(0, -d);
      else if (keyChar == 'j') add(0, d);
   }

   public boolean isFocusTraversable() { return true; }

   public void add(int dx, int dy)
   {  end.x += dx;
      end.y += dy;
      Graphics g = getGraphics();
      g.drawLine(start.x, start.y, end.x, end.y);
      g.dispose();
      start.x = end.x;
      start.y = end.y;
   }  
           
   private Point start = new Point(0, 0);
   private Point end = new Point(0, 0); 
}

class SketchFrame extends JFrame
{  public SketchFrame()
   {  setTitle("Sketch");
      setSize(300, 200);
      addWindowListener(new WindowAdapter()
         {  public void windowClosing(WindowEvent e)
            {  System.exit(0);
            }
         } );

      Container contentPane = getContentPane();
      contentPane.add(new SketchPanel());
   }
}

public class Sketch
{  public static void main(String[] args)
   {  JFrame frame = new SketchFrame();
      frame.show();  
   }
}




