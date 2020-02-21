/**
 * @version 1.20 25 Mar 1998
 * @author Cay Horstmann
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class MousePanel extends JPanel
   implements MouseMotionListener
{  public MousePanel()
   {  addMouseListener(new MouseAdapter() 
         {  public void mousePressed(MouseEvent evt) 
            {  int x = evt.getX();
               int y = evt.getY();
               current = find(x, y);
               if (current < 0) // not inside a square
                  add(x, y);
            }

            public void mouseClicked(MouseEvent evt) 
            {  int x = evt.getX();
               int y = evt.getY();

               if (evt.getClickCount() >= 2)
               {  remove(current);
               }
            }
         });
      addMouseMotionListener(this);
   }

   public void paintComponent(Graphics g)
   {  super.paintComponent(g);
      for (int i = 0; i < nsquares; i++)
         draw(g, i);
   }
   
   public int find(int x, int y)
   {  for (int i = 0; i < nsquares; i++)
         if (squares[i].x - SQUARELENGTH / 2 <= x && 
               x <= squares[i].x + SQUARELENGTH / 2 
               && squares[i].y - SQUARELENGTH / 2 <= y 
               && y <= squares[i].y + SQUARELENGTH / 2)
            return i;
      return -1;
   }

   public void draw(Graphics g, int i)
   {  g.drawRect(squares[i].x - SQUARELENGTH / 2, 
         squares[i].y - SQUARELENGTH / 2, 
         SQUARELENGTH, 
         SQUARELENGTH);
   }

   public void add(int x, int y)
   {  if (nsquares < MAXNSQUARES)
      {  squares[nsquares] = new Point(x, y);
         current = nsquares;
         nsquares++;
         repaint();
      }
   }

   public void remove(int n)
   {  if (n < 0 || n >= nsquares) return;
      nsquares--;
      squares[n] = squares[nsquares];
      if (current == n) current = -1;
      repaint();
   }

   public void mouseMoved(MouseEvent evt) 
   {  int x = evt.getX();
      int y = evt.getY();

      if (find(x, y) >= 0) 
         setCursor(Cursor.getPredefinedCursor
            (Cursor.CROSSHAIR_CURSOR)); 
      else 
         setCursor(Cursor.getDefaultCursor());
   }

   public void mouseDragged(MouseEvent evt) 
   {  int x = evt.getX();
      int y = evt.getY();

      if (current >= 0)
      {  Graphics g = getGraphics();
         g.setXORMode(getBackground());
         draw(g, current);      
         squares[current].x = x;
         squares[current].y = y;
         draw(g, current);
         g.dispose();
      }
   }

   private static final int SQUARELENGTH = 10;
   private static final int MAXNSQUARES = 100;
   private Point[] squares = new Point[MAXNSQUARES];
   private int nsquares = 0;
   private int current = -1;
}

class MouseFrame extends JFrame
{  public MouseFrame()
   {  setTitle("MouseTest");
      setSize(300, 200);
      addWindowListener(new WindowAdapter()
         {  public void windowClosing(WindowEvent e)
            {  System.exit(0);
            }
         } );

      Container contentPane = getContentPane();
      contentPane.add(new MousePanel());
   }
}

public class MouseTest
{  public static void main(String[] args)
   {  JFrame frame = new MouseFrame();
      frame.show();  
   }
}
