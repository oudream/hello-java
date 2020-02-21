/**
 * @version 1.00 1999-09-11
 * @author Cay Horstmann
 */

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;
import javax.swing.*;

public class StrokeTest
{  public static void main(String[] args)
   {  JFrame frame = new StrokeTestFrame();
      frame.show();
   }
}

class StrokeTestFrame extends JFrame
   implements ActionListener
{  public StrokeTestFrame()
   {  setTitle("StrokeTest");
      setSize(400, 400);
      addWindowListener(new WindowAdapter()
         {  public void windowClosing(WindowEvent e)
            {  System.exit(0);
            }
         } );

      Container contentPane = getContentPane();
      canvas = new StrokePanel();
      contentPane.add(canvas, "Center");

      JPanel buttonPanel = new JPanel();
      buttonPanel.setLayout(new GridLayout(3, 3));
      ButtonGroup group1 = new ButtonGroup();

      buttCapButton = new JRadioButton("Butt Cap", true);
      buttonPanel.add(buttCapButton);
      group1.add(buttCapButton);
      buttCapButton.addActionListener(this);

      roundCapButton = new JRadioButton("Round Cap", false);
      buttonPanel.add(roundCapButton);
      group1.add(roundCapButton);
      roundCapButton.addActionListener(this);

      squareCapButton = new JRadioButton("Square Cap", false);
      buttonPanel.add(squareCapButton);
      group1.add(squareCapButton);
      squareCapButton.addActionListener(this);

      ButtonGroup group2 = new ButtonGroup();

      bevelJoinButton = new JRadioButton("Bevel Join", true);
      buttonPanel.add(bevelJoinButton);
      group2.add(bevelJoinButton);
      bevelJoinButton.addActionListener(this);

      miterJoinButton = new JRadioButton("Miter Join", false);
      buttonPanel.add(miterJoinButton);
      group2.add(miterJoinButton);
      miterJoinButton.addActionListener(this);

      roundJoinButton = new JRadioButton("Round Join", false);
      buttonPanel.add(roundJoinButton);
      group2.add(roundJoinButton);
      roundJoinButton.addActionListener(this);

      ButtonGroup group3 = new ButtonGroup();

      solidLineButton = new JRadioButton("Solid Line", true);
      buttonPanel.add(solidLineButton);
      group3.add(solidLineButton);
      solidLineButton.addActionListener(this);

      dashedLineButton = new JRadioButton("Dashed Line", false);
      buttonPanel.add(dashedLineButton);
      group3.add(dashedLineButton);
      dashedLineButton.addActionListener(this);

      contentPane.add(buttonPanel, "North");
   }

   public void actionPerformed(ActionEvent event)
   {  Object source = event.getSource();
      if (source == buttCapButton)
         canvas.setCap(BasicStroke.CAP_BUTT);
      else if (source == roundCapButton)
         canvas.setCap(BasicStroke.CAP_ROUND);
      else if (source == squareCapButton)
         canvas.setCap(BasicStroke.CAP_SQUARE);
      else if (source == bevelJoinButton)
         canvas.setJoin(BasicStroke.JOIN_BEVEL);
      else if (source == miterJoinButton)
         canvas.setJoin(BasicStroke.JOIN_MITER);
      else if (source == roundJoinButton)
         canvas.setJoin(BasicStroke.JOIN_ROUND);
      else if (source == solidLineButton)
         canvas.setDash(false);
      else if (source == dashedLineButton)
         canvas.setDash(true);
   }

   private StrokePanel canvas;
   private JRadioButton buttCapButton;
   private JRadioButton roundCapButton;
   private JRadioButton squareCapButton;
   private JRadioButton bevelJoinButton;
   private JRadioButton miterJoinButton;
   private JRadioButton roundJoinButton;
   private JRadioButton solidLineButton;
   private JRadioButton dashedLineButton;
}

class StrokePanel extends JPanel
   implements MouseListener, MouseMotionListener
{  public StrokePanel()
   {  addMouseListener(this);
      addMouseMotionListener(this);
      points = new Point2D[3];
      points[0] = new Point2D.Double(200, 100);
      points[1] = new Point2D.Double(100, 200);
      points[2] = new Point2D.Double(200, 200);
      current = -1;
      width = 10.0F;
   }

   public void paintComponent(Graphics g)
   {  super.paintComponent(g);
      Graphics2D g2 = (Graphics2D)g;
      GeneralPath path = new GeneralPath();
      path.moveTo((float)points[0].getX(),
         (float)points[0].getY());
      for (int i = 1; i < points.length; i++)
         path.lineTo((float)points[i].getX(),
            (float)points[i].getY());
      BasicStroke stroke;
      if (dash)
      {  float miterLimit = 10.0F;
         float[] dashPattern = { 10F, 10F, 10F, 10F, 10F, 10F,
            30F, 10F, 30F, 10F, 30F, 10F,
            10F, 10F, 10F, 10F, 10F, 30F };
         float dashPhase = 0;
         stroke = new BasicStroke(width, cap, join,
            miterLimit, dashPattern, dashPhase);
      }
      else
         stroke = new BasicStroke(width, cap, join);
      g2.setStroke(stroke);
      g2.draw(path);
   }

   public void setJoin(int j) { join = j; repaint(); }

   public void setCap(int c) { cap = c; repaint(); }

   public void setDash(boolean d) { dash = d; repaint(); }

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
   private static int SIZE = 10;
   private int current;
   private float width;
   private int cap;
   private int join;
   private boolean dash;
}
