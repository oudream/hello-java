/**
 * @version 1.00 1999-09-11
 * @author Cay Horstmann
 */

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;
import javax.swing.*;

public class ShapeTest
{  public static void main(String[] args)
   {  JFrame frame = new ShapeTestFrame();
      frame.show();
   }
}

class ShapeTestFrame extends JFrame
   implements ActionListener
{  public ShapeTestFrame()
   {  setTitle("ShapeTest");
      setSize(300, 300);
      addWindowListener(new WindowAdapter()
         {  public void windowClosing(WindowEvent e)
            {  System.exit(0);
            }
         } );

      Container contentPane = getContentPane();

      panel = new ShapePanel();
      contentPane.add(panel, "Center");
      comboBox = new JComboBox();
      comboBox.addItem(new LineMaker());
      comboBox.addItem(new RectangleMaker());
      comboBox.addItem(new RoundRectangleMaker());
      comboBox.addItem(new EllipseMaker());
      comboBox.addItem(new ArcMaker());
      comboBox.addItem(new PolygonMaker());
      comboBox.addItem(new QuadCurveMaker());
      comboBox.addItem(new CubicCurveMaker());
      comboBox.addActionListener(this);
      contentPane.add(comboBox, "North");
   }

   public void actionPerformed(ActionEvent event)
   {  ShapeMaker shapeMaker =
         (ShapeMaker)comboBox.getSelectedItem();
      panel.setShapeMaker(shapeMaker);
   }

   private JComboBox comboBox;
   private ShapePanel panel;
}

class ShapePanel extends JPanel
   implements MouseListener, MouseMotionListener
{  public ShapePanel()
   {  addMouseListener(this);
      addMouseMotionListener(this);
      current = -1;
   }

   public void setShapeMaker(ShapeMaker aShapeMaker)
   {  shapeMaker = aShapeMaker;
      int n = shapeMaker.getPointCount();
      points = new Point2D[n];
      for (int i = 0; i < n; i++)
      {  double x = generator.nextDouble() * getWidth();
         double y = generator.nextDouble() * getHeight();
         points[i] = new Point2D.Double(x, y);
      }
      repaint();
   }

   public void paintComponent(Graphics g)
   {  super.paintComponent(g);
      if (points == null) return;
      Graphics2D g2 = (Graphics2D)g;
      for (int i = 0; i < points.length; i++)
      {  double x = points[i].getX() - SIZE / 2;
         double y = points[i].getY() - SIZE / 2;
         g2.fill(new Rectangle2D.Double(x, y, SIZE, SIZE));
      }

      g2.draw(shapeMaker.makeShape(points));
   }

   public void mousePressed(MouseEvent event)
   {  Point p = event.getPoint();
      for (int i = 0; i < points.length; i++)
      {  double x = points[i].getX() - SIZE / 2;
         double y = points[i].getY() - SIZE / 2;
         Rectangle2D r = new Rectangle2D.Double(x, y, SIZE, SIZE);
         if (r.contains(p))
         {  current = i;
            return;
         }
      }
   }

   public void mouseReleased(MouseEvent event)
   {  current = -1;
   }

   public void mouseEntered(MouseEvent event)
   {
   }

   public void mouseExited(MouseEvent event)
   {
   }

   public void mouseClicked(MouseEvent event)
   {
   }

   public void mouseMoved(MouseEvent event)
   {
   }

   public void mouseDragged(MouseEvent event)
   {  if (current == -1) return;
      points[current] = event.getPoint();
      repaint();
   }


   private Point2D[] points;
   private static Random generator = new Random();
   private static int SIZE = 10;
   private int current;
   private ShapeMaker shapeMaker;
}

abstract class ShapeMaker
{  public ShapeMaker(int aPointCount)
   {  pointCount = aPointCount;
   }

   public int getPointCount()
   {  return pointCount;
   }

   public abstract Shape makeShape(Point2D[] p);

   public String toString()
   {  return getClass().getName();
   }

   private int pointCount;
}

class LineMaker extends ShapeMaker
{  public LineMaker() { super(2); }

   public Shape makeShape(Point2D[] p)
   {  return new Line2D.Double(p[0], p[1]);
   }
}

class RectangleMaker extends ShapeMaker
{  public RectangleMaker() { super(2); }

   public Shape makeShape(Point2D[] p)
   {  Rectangle2D s = new Rectangle2D.Double();
      s.setFrameFromDiagonal(p[0], p[1]);
      return s;
   }
}

class RoundRectangleMaker extends ShapeMaker
{  public RoundRectangleMaker() { super(2); }

   public Shape makeShape(Point2D[] p)
   {  RoundRectangle2D s
         = new RoundRectangle2D.Double(0, 0, 0, 0, 20, 20);
      s.setFrameFromDiagonal(p[0], p[1]);
      return s;
   }
}

class EllipseMaker extends ShapeMaker
{  public EllipseMaker() { super(2); }

   public Shape makeShape(Point2D[] p)
   {  Ellipse2D s = new Ellipse2D.Double();
      s.setFrameFromDiagonal(p[0], p[1]);
      return s;
   }
}

class ArcMaker extends ShapeMaker
{  public ArcMaker() { super(4); }

   public Shape makeShape(Point2D[] p)
   {  double centerX = (p[0].getX() + p[1].getX()) / 2;
      double centerY = (p[0].getY() + p[1].getY()) / 2;
      double width = Math.abs(p[1].getX() - p[0].getX());
      double height = Math.abs(p[1].getY() - p[0].getY());

      double distortedStartAngle
         = Math.toDegrees(Math.atan2(-(p[2].getY() - centerY)
            * width, (p[2].getX() - centerX) * height));
      double distortedEndAngle
         = Math.toDegrees(Math.atan2(-(p[3].getY() - centerY)
            * width, (p[3].getX() - centerX) * height));
      double distortedAngleDifference
         = distortedEndAngle - distortedStartAngle;
      if (distortedStartAngle < 0)
         distortedStartAngle += 360;
      if (distortedAngleDifference < 0)
         distortedAngleDifference += 360;

      Arc2D s = new Arc2D.Double(0, 0, 0, 0,
         distortedStartAngle, distortedAngleDifference,
         Arc2D.OPEN);
      s.setFrameFromDiagonal(p[0], p[1]);

      GeneralPath g = new GeneralPath();
      g.append(s, false);
      Rectangle2D r = new Rectangle2D.Double();
      r.setFrameFromDiagonal(p[0], p[1]);
      g.append(r, false);
      Point2D center = new Point2D.Double(centerX, centerY);
      g.append(new Line2D.Double(center, p[2]), false);
      g.append(new Line2D.Double(center, p[3]), false);
      return g;
   }
}

class PolygonMaker extends ShapeMaker
{  public PolygonMaker() { super(6); }

   public Shape makeShape(Point2D[] p)
   {  GeneralPath s = new GeneralPath();
      s.moveTo((float)p[0].getX(), (float)p[0].getY());
      for (int i = 1; i < p.length; i++)
         s.lineTo((float)p[i].getX(), (float)p[i].getY());
      s.closePath();
      return s;
   }
}

class QuadCurveMaker extends ShapeMaker
{  public QuadCurveMaker() { super(3); }

   public Shape makeShape(Point2D[] p)
   {  return new QuadCurve2D.Double(p[0].getX(), p[0].getY(),
         p[1].getX(), p[1].getY(), p[2].getX(), p[2].getY());
   }
}

class CubicCurveMaker extends ShapeMaker
{  public CubicCurveMaker() { super(4); }

   public Shape makeShape(Point2D[] p)
   {  return new CubicCurve2D.Double(p[0].getX(), p[0].getY(),
         p[1].getX(), p[1].getY(), p[2].getX(), p[2].getY(),
         p[3].getX(), p[3].getY());
   }
}
