/**
 * @version 1.00 1999-09-11
 * @author Cay Horstmann
 */

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;
import javax.swing.*;

public class AreaTest
{  public static void main(String[] args)
   {  JFrame frame = new AreaTestFrame();
      frame.show();
   }
}

class AreaTestFrame extends JFrame
   implements ActionListener
{  public AreaTestFrame()
   {  setTitle("AreaTest");
      setSize(400, 400);
      addWindowListener(new WindowAdapter()
         {  public void windowClosing(WindowEvent e)
            {  System.exit(0);
            }
         } );

      Container contentPane = getContentPane();
      canvas = new AreaPanel();
      contentPane.add(canvas, "Center");

      JPanel buttonPanel = new JPanel();
      ButtonGroup group = new ButtonGroup();

      addButton = new JRadioButton("Add", true);
      buttonPanel.add(addButton);
      group.add(addButton);
      addButton.addActionListener(this);

      subtractButton = new JRadioButton("Subtract", false);
      buttonPanel.add(subtractButton);
      group.add(subtractButton);
      subtractButton.addActionListener(this);

      intersectButton = new JRadioButton("Intersect", false);
      buttonPanel.add(intersectButton);
      group.add(intersectButton);
      intersectButton.addActionListener(this);

      exclusiveOrButton = new JRadioButton("Exclusive Or", false);
      buttonPanel.add(exclusiveOrButton);
      group.add(exclusiveOrButton);
      exclusiveOrButton.addActionListener(this);

      contentPane.add(buttonPanel, "North");
   }

   public void actionPerformed(ActionEvent event)
   {  Object source = event.getSource();
      if (source == addButton)
         canvas.addAreas();
      else if (source == subtractButton)
         canvas.subtractAreas();
      else if (source == intersectButton)
         canvas.intersectAreas();
      else if (source == exclusiveOrButton)
         canvas.exclusiveOrAreas();
   }


   private AreaPanel canvas;
   private JRadioButton addButton;
   private JRadioButton subtractButton;
   private JRadioButton intersectButton;
   private JRadioButton exclusiveOrButton;
}

class AreaPanel extends JPanel
{  public AreaPanel()
   {  area1
         = new Area(new Ellipse2D.Double(100, 100, 150, 100));
      area2
         = new Area(new Rectangle2D.Double(150, 150, 150, 100));
      addAreas();
   }

   public void paintComponent(Graphics g)
   {  super.paintComponent(g);
      Graphics2D g2 = (Graphics2D)g;
      g2.draw(area1);
      g2.draw(area2);
      g2.fill(area);
   }

   public void addAreas()
   {  area = new Area();
      area.add(area1);
      area.add(area2);
      repaint();
   }

   public void subtractAreas()
   {  area = new Area();
      area.add(area1);
      area.subtract(area2);
      repaint();
   }

   public void intersectAreas()
   {  area = new Area();
      area.add(area1);
      area.intersect(area2);
      repaint();
   }

   public void exclusiveOrAreas()
   {  area = new Area();
      area.add(area1);
      area.exclusiveOr(area2);
      repaint();
   }

   private Area area;
   private Area area1;
   private Area area2;
}