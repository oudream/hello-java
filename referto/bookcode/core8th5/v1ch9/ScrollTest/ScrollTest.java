/**
 * @version 1.20 17 Aug 1998
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
            {  int x = evt.getX() + dx;
               int y = evt.getY() + dy;
               current = find(x, y);
               if (current < 0) // not inside a square
               {  if (x < MAX_XWIDTH && y < MAX_YHEIGHT)
                     add(x, y);
               }
            }

            public void mouseClicked(MouseEvent evt)
            {  int x = evt.getX() + dx;
               int y = evt.getY() + dy;

               if (evt.getClickCount() >= 2)
               {  remove(current);
               }
            }
         });
      addMouseMotionListener(this);
   }

   public void translate(int x, int y)
   {  dx = x;
      dy = y;
      repaint();
   }

   public void paintComponent(Graphics g)
   {  super.paintComponent(g);
      g.translate(-dx, -dy);
      g.setColor(Color.red);
      g.drawRect(0, 0, MAX_XWIDTH - 1, MAX_YHEIGHT - 1);
      g.setColor(Color.black);
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
         SQUARELENGTH, SQUARELENGTH);
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

   private int dx = 0;
   private int dy = 0;

   public static final int MAX_XWIDTH = 600;
   public static final int MAX_YHEIGHT = 400;
}

class ScrollFrame extends JFrame
   implements AdjustmentListener
{  public ScrollFrame()
   {  setTitle("ScrollTest");
      setSize(300, 200);
      addWindowListener(new WindowAdapter()
         {  public void windowClosing(WindowEvent e)
            {  System.exit(0);
            }
         } );

      Container contentPane = getContentPane();
      contentPane.add(panel = new MousePanel(), "Center");
      contentPane.add(vert = new JScrollBar
         (Adjustable.VERTICAL), "East");
      contentPane.add(horiz = new JScrollBar
         (Adjustable.HORIZONTAL), "South");
      vert.addAdjustmentListener(this);
      horiz.addAdjustmentListener(this);
      horiz.setValues(horiz.getValue(), 0, 0,
         MousePanel.MAX_XWIDTH);
      vert.setValues(vert.getValue(), 0, 0,
         MousePanel.MAX_YHEIGHT);

      addComponentListener(new ComponentAdapter()
      {  public void componentShown(ComponentEvent evt)
         {  setVisibleAmounts();
         }
         public void componentResized(ComponentEvent evt)
         {  setVisibleAmounts();
         }
      });
   }

   public void setVisibleAmounts()
   {  Dimension d = panel.getSize();
      horiz.setVisibleAmount(d.width);
      vert.setVisibleAmount(d.height);
   }

   public void adjustmentValueChanged(AdjustmentEvent evt)
   {  panel.translate(horiz.getValue(), vert.getValue());
   }

   private JScrollBar horiz;
   private JScrollBar vert;
   private MousePanel panel;
}

public class ScrollTest
{  public static void main(String[] args)
   {  JFrame frame = new ScrollFrame();
      frame.show();
   }
}

