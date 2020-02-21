/**
 * @version 1.20 23 Jun 1998
 * @author Cay Horstmann
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class FillRectPanel extends JPanel
{  public void paintComponent(Graphics g)
   {  super.paintComponent(g);
      g.drawRect(10, 10, 80, 30);
      g.drawRoundRect(100, 10, 80, 30, 15, 15);
      g.drawOval(10, 100, 80, 30);
      g.setColor(Color.red);
      g.fillRect(10, 10, 80, 30);
      g.fillRoundRect(100, 10, 80, 30, 15, 15);

      int thickness = 4;

      g.fill3DRect(200, 10, 80, 30, true);
      for (int i = 1; i <= thickness; i++)
         g.draw3DRect(200 - i, 10 - i, 
            80 + 2 * i - 1, 30 + 2 * i - 1, true);
      
      g.fill3DRect(200, 50, 80, 30, false);
      for (int i = 1; i <= thickness; i++)
         g.draw3DRect(200 - i, 50 - i, 
            80 + 2 * i - 1, 30 + 2 * i - 1, true);

      g.fillOval(10, 100, 80, 30);
   }
}

class FillRectFrame extends JFrame
{  public FillRectFrame()
   {  setTitle("FillRect");
      setSize(300, 200);
      addWindowListener(new WindowAdapter()
         {  public void windowClosing(WindowEvent e)
            {  System.exit(0);
            }
         } );
      Container contentPane = getContentPane();
      contentPane.add(new FillRectPanel());
   }
}

public class FillRect
{  public static void main(String[] args)
   {  JFrame frame = new FillRectFrame();
      frame.show();  
   }
}