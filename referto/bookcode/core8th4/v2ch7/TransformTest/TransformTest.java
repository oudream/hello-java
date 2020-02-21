/**
 * @version 1.00 1999-09-11
 * @author Cay Horstmann
 */

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;
import javax.swing.*;

public class TransformTest
{  public static void main(String[] args)
   {  JFrame frame = new TransformTestFrame();
      frame.show();
   }
}

class TransformTestFrame extends JFrame
   implements ActionListener
{  public TransformTestFrame()
   {  setTitle("TransformTest");
      setSize(300, 300);
      addWindowListener(new WindowAdapter()
         {  public void windowClosing(WindowEvent e)
            {  System.exit(0);
            }
         } );

      Container contentPane = getContentPane();
      canvas = new TransformPanel();
      contentPane.add(canvas, "Center");

      JPanel buttonPanel = new JPanel();
      ButtonGroup group = new ButtonGroup();

      rotateButton = new JRadioButton("Rotate", true);
      buttonPanel.add(rotateButton);
      group.add(rotateButton);
      rotateButton.addActionListener(this);

      translateButton = new JRadioButton("Translate", false);
      buttonPanel.add(translateButton);
      group.add(translateButton);
      translateButton.addActionListener(this);

      scaleButton = new JRadioButton("Scale", false);
      buttonPanel.add(scaleButton);
      group.add(scaleButton);
      scaleButton.addActionListener(this);

      shearButton = new JRadioButton("Shear", false);
      buttonPanel.add(shearButton);
      group.add(shearButton);
      shearButton.addActionListener(this);

      contentPane.add(buttonPanel, "North");
   }

   public void actionPerformed(ActionEvent event)
   {  Object source = event.getSource();
      if (source == rotateButton) canvas.setRotate();
      else if (source == translateButton) canvas.setTranslate();
      else if (source == scaleButton) canvas.setScale();
      else if (source == shearButton) canvas.setShear();
   }

   private TransformPanel canvas;
   private JRadioButton rotateButton;
   private JRadioButton translateButton;
   private JRadioButton scaleButton;
   private JRadioButton shearButton;
}

class TransformPanel extends JPanel
{  public TransformPanel()
   {  square = new Rectangle2D.Double(-50, -50, 100, 100);
      t = new AffineTransform();
      setRotate();
   }

   public void paintComponent(Graphics g)
   {  super.paintComponent(g);
      Graphics2D g2 = (Graphics2D)g;
      g2.translate(getWidth() / 2, getHeight() / 2);
      g2.setPaint(Color.gray);
      g2.draw(square);
      g2.transform(t);
         /* we don't use setTransform because we want
            to compose with the current translation
         */
      g2.setPaint(Color.black);
      g2.draw(square);
   }

   public void setRotate()
   {  t.setToRotation(Math.toRadians(30));
      repaint();
   }

   public void setTranslate()
   {  t.setToTranslation(20, 15);
      repaint();
   }

   public void setScale()
   {  t.setToScale(2.0, 1.5);
      repaint();
   }

   public void setShear()
   {  t.setToShear(-0.2, 0);
      repaint();
   }

   private Rectangle2D square;
   private AffineTransform t;
}
