/**
 * @version 1.20 23 Jun 1998
 * @author Cay Horstmann
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class DrawRectPanel extends JPanel
{  public void paintComponent(Graphics g)
   {  super.paintComponent(g);
      g.setColor(Color.blue);
      g.drawRect(10, 10, 80, 30);
      g.drawRoundRect(100, 10, 80, 30, 15, 15);

      int thickness = 4;

      for (int i = 0; i <= thickness; i++)
         g.draw3DRect(200 - i, 10 - i, 
            80 + 2 * i, 30 + 2 * i, true);
      for (int i = 0; i < thickness; i++)
         g.draw3DRect(200 - i, 50 - i, 
            80 + 2 * i, 30 + 2 * i, false);
 
      g.drawOval(10, 100, 80, 30);
   }
}

class DrawRectFrame extends JFrame
{  public DrawRectFrame()
   {  setTitle("DrawRect");
      setSize(300, 200);
      addWindowListener(new WindowAdapter()
         {  public void windowClosing(WindowEvent e)
            {  System.exit(0);
            }
         } );
      Container contentPane = getContentPane();
      contentPane.add(new DrawRectPanel());
   }
}

public class DrawRect
{  public static void main(String[] args)
   {  JFrame frame = new DrawRectFrame();
      frame.show();  
   }
}



                        
