/**
 * @version 1.20 23 Jun 1998
 * @author Cay Horstmann
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class DrawPolyPanel extends JPanel
{  public void paintComponent(Graphics g)
   {  super.paintComponent(g);
   
      int r = 40; // radius of circle bounding PacMan(R)
      int cx = 50; // center of that circle
      int cy = 100;
      int angle = 30; // opening angle of mouth
      
      int dx = (int)(r * Math.cos(angle * Math.PI / 180));
      int dy = (int)(r * Math.sin(angle * Math.PI / 180));
      
      g.drawLine(cx, cy, cx + dx, cy + dy); // lower jaw
      g.drawLine(cx, cy, cx + dx, cy - dy); // upper jaw
      g.drawArc(cx - r, cy - r, 2 * r, 2 * r, angle, 
         360 - 2 * angle); 
 
      Polygon p = new Polygon();
      cx = 150;
      int i;
      for (i = 0; i < 5; i++)
         p.addPoint(
            (int)(cx + r * Math.cos(i * 2 * Math.PI / 5)),
            (int)(cy + r * Math.sin(i * 2 * Math.PI / 5)));
            
      g.drawPolygon(p);
      
      Polygon s = new Polygon(); 
      cx = 250;
      for (i = 0; i < 360; i++)
      {  double t = i / 360.0;
         s.addPoint(
            (int)(cx + r * t * Math.cos(8 * t * Math.PI)), 
            (int)(cy + r * t * Math.sin(8 * t * Math.PI)));
      }
      g.drawPolygon(s);       
   }
}

class DrawPolyFrame extends JFrame
{  public DrawPolyFrame()
   {  setTitle("DrawPoly");
      setSize(300, 200);
      addWindowListener(new WindowAdapter()
         {  public void windowClosing(WindowEvent e)
            {  System.exit(0);
            }
         } );
      Container contentPane = getContentPane();
      contentPane.add(new DrawPolyPanel());
   }
}

public class DrawPoly
{  public static void main(String[] args)
   {  JFrame frame = new DrawPolyFrame();
      frame.show();  
   }
}
