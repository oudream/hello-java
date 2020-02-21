/**
 * @version 1.20 25 Mar 1998
 * @author Cay Horstmann
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class NotHelloWorld2Panel extends JPanel
{  public void setFonts(Graphics g)
   {  if (f != null) return;
      f = new Font("SansSerif", Font.BOLD, 14);
      fi = new Font("SansSerif", 
         Font.BOLD + Font.ITALIC, 14);
      fm = g.getFontMetrics(f);
      fim = g.getFontMetrics(fi);
   }
   
   public void paintComponent(Graphics g)
   {  super.paintComponent(g);

      setFonts(g);
      String s1 = "Not a ";
      String s2 = "Hello, World";
      String s3 = " Program";
      int w1 = fm.stringWidth(s1);
      int w2 = fim.stringWidth(s2);
      int w3 = fm.stringWidth(s3);

      Dimension d = getSize();
      int cx = (d.width - w1 - w2 - w3) / 2;
      int cy = (d.height - fm.getHeight()) / 2 
         + fm.getAscent();
      
      g.setFont(f);
      g.drawString(s1, cx, cy);
      cx += w1;
      g.setFont(fi);
      g.drawString(s2, cx, cy);
      cx += w2;
      g.setFont(f);
      g.drawString(s3, cx, cy);
   }

   private Font f;
   private Font fi;
   private FontMetrics fm;
   private FontMetrics fim;
}

class NotHelloWorld2Frame extends JFrame
{  public NotHelloWorld2Frame()
   {  setTitle("NotHelloWorld2");
      setSize(300, 200);
      addWindowListener(new WindowAdapter()
         {  public void windowClosing(WindowEvent e)
            {  System.exit(0);
            }
         } );
      Container contentPane = getContentPane();
      contentPane.add(new NotHelloWorld2Panel());
   }
}

public class NotHelloWorld2
{  public static void main(String[] args)
   {  JFrame frame = new NotHelloWorld2Frame();
      frame.show();  
   }
}

