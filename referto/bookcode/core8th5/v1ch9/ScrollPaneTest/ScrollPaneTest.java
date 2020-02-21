/**
 * @version 1.20 17 Aug 1998
 * @author Cay Horstmann
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class MousePanel extends JPanel
   // unchanged except for setPreferredSize
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

      setPreferredSize(new Dimension(MAX_XWIDTH,
         MAX_YHEIGHT));
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

   private static final int MAX_XWIDTH = 600;
   private static final int MAX_YHEIGHT = 400;
   private static final int SQUARELENGTH = 10;
   private static final int MAXNSQUARES = 100;
   private Point[] squares = new Point[MAXNSQUARES];
   private int nsquares = 0;
   private int current = -1;
}

class ScrollPaneFrame extends JFrame
{  public ScrollPaneFrame()
   {  setTitle("ScrollPaneTest");
      setSize(300, 200);
      addWindowListener(new WindowAdapter()
         {  public void windowClosing(WindowEvent e)
            {  System.exit(0);
            }
         } );

      Container contentPane = getContentPane();
      Component viewedComponent = new MousePanel();
      JScrollPane sp = new JScrollPane(viewedComponent);

      RulerPanel horizRulerPanel = new RulerPanel
         (SwingConstants.HORIZONTAL,
         viewedComponent.getPreferredSize().width,
         25, 100, 100, 10);
      sp.setColumnHeaderView(horizRulerPanel);

      RulerPanel vertRulerPanel = new RulerPanel
         (SwingConstants.VERTICAL,
         25, viewedComponent.getPreferredSize().height,
         100, 100, 10);
      sp.setRowHeaderView(vertRulerPanel);
      contentPane.add(sp, "Center");

      ScrollAction.register(sp, JScrollBar.HORIZONTAL,
         ScrollAction.UNIT, -1, KeyEvent.VK_LEFT, 0);
      ScrollAction.register(sp, JScrollBar.HORIZONTAL,
         ScrollAction.UNIT, 1, KeyEvent.VK_RIGHT, 0);
      ScrollAction.register(sp, JScrollBar.VERTICAL,
         ScrollAction.UNIT, -1, KeyEvent.VK_UP, 0);
      ScrollAction.register(sp, JScrollBar.VERTICAL,
         ScrollAction.UNIT, 1, KeyEvent.VK_DOWN, 0);
      ScrollAction.register(sp, JScrollBar.HORIZONTAL,
         ScrollAction.BLOCK, -1, KeyEvent.VK_PAGE_UP,
         InputEvent.CTRL_MASK);
      ScrollAction.register(sp, JScrollBar.HORIZONTAL,
         ScrollAction.BLOCK, 1, KeyEvent.VK_PAGE_DOWN,
         InputEvent.CTRL_MASK);
      ScrollAction.register(sp, JScrollBar.VERTICAL,
         ScrollAction.BLOCK, -1, KeyEvent.VK_PAGE_UP, 0);
      ScrollAction.register(sp, JScrollBar.VERTICAL,
         ScrollAction.BLOCK, 1, KeyEvent.VK_PAGE_DOWN, 0);
   }
}

public class ScrollPaneTest
{  public static void main(String[] args)
   {  JFrame frame = new ScrollPaneFrame();
      frame.show();
   }
}

class ScrollAction extends AbstractAction
{  public ScrollAction(JScrollPane p, int orient,
      int t, int dir)
   {  scrollPane = p;
      orientation = orient;
      type = t;
      direction = dir;
   }

   public static void register(JScrollPane p, int orient,
      int t, int dir, int key, int modifier)
   {  p.registerKeyboardAction(
         new ScrollAction(p, orient, t, dir),
         KeyStroke.getKeyStroke(key, modifier, false),
         JComponent.WHEN_IN_FOCUSED_WINDOW);
   }

   public void actionPerformed(ActionEvent evt)
   {  JScrollBar scrollBar;
      if (orientation == JScrollBar.HORIZONTAL)
         scrollBar = scrollPane.getHorizontalScrollBar();
      else
         scrollBar = scrollPane.getVerticalScrollBar();
      if (scrollBar == null || !scrollBar.isVisible())
         return;
      int increment;
      if (type == UNIT)
         increment = scrollBar.getUnitIncrement();
      else
         increment = scrollBar.getBlockIncrement();
      scrollBar.setValue(scrollBar.getValue() +
         increment * direction);
   }

   private JScrollPane scrollPane;
   private int orientation; // HORIZONTAL or VERTICAL
   private int type; // UNIT or BLOCK
   private int direction; // +1 or -1

   public static final int UNIT = 1;
   public static final int BLOCK = 2;
}

class RulerPanel extends JPanel implements SwingConstants
{  public RulerPanel(int dir, int w, int h,
      int lbldist, int lbl, int subs)
   {  direction = dir;
      labelDistance = lbldist;
      label = lbl;
      subdivisions = subs;
      setPreferredSize(new Dimension(w, h));
   }

   public void paintComponent(Graphics g)
   {  super.paintComponent(g);
      Dimension d = getPreferredSize();
      if (direction == HORIZONTAL)
      {  int i = 0;
         int x = 0;
         if (subdivisions > 0)
         {  while (x < d.width)
            {  g.drawLine(x, 0, x, (d.height * 4) / 10);
               i++;
               x = (i * labelDistance) / subdivisions;
            }
         }
         i = 0;
         x = 0;
         while (x <= d.width)
         {  g.drawLine(x, 0, x, (d.height * 8) / 10);
            g.drawString("" + i * label, x + 2,
               (d.height * 8) / 10);
            i++;
            x = i * labelDistance;
         }
      }
      else
      {  int i = 0;
         int y = 0;
         if (subdivisions > 0)
         {  while (y <= d.height)
            {  g.drawLine(0, y, (d.width * 4) / 10, y);
               i++;
               y = (i * labelDistance) / subdivisions;
            }
         }
         i = 0;
         y = 0;
         while (y <= d.height)
         {  g.drawLine(0, y, (d.width * 8) / 10, y);
            g.drawString("" + i * label, 2, y);
            i++;
            y = i * labelDistance;
         }
      }
   }

   private int direction;
   private int labelDistance;
   private int subdivisions;
   private int label;
}