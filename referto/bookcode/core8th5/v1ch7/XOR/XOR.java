/**
 * @version 1.20 23 Jun 1998
 * @author Cay Horstmann
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class XORPanel extends JPanel
{  XORPanel()
   {  setBackground(Color.black);
   }

   public void paintComponent(Graphics g)
   {  super.paintComponent(g);
      g.setColor(Color.red);
      g.fillRect(10, 10, 80, 30);
      g.setColor(Color.green);
      g.fillRect(50, 20, 80, 30);
      g.setColor(Color.blue);
      g.fillRect(130, 40, 80, 30);
      g.setXORMode(Color.green);
      g.fillRect(90, 30, 80, 30);
   }
}

class XORFrame extends JFrame
{  public XORFrame()
   {  setTitle("XOR");
      setSize(300, 200);
      addWindowListener(new WindowAdapter()
         {  public void windowClosing(WindowEvent e)
            {  System.exit(0);
            }
         } );
      Container contentPane = getContentPane();
      contentPane.add(new XORPanel());
   }
}

public class XOR
{  public static void main(String[] args)
   {  JFrame frame = new XORFrame();
      frame.show();  
   }
}

                        
