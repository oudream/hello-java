/**
 * @version 1.00 1999-09-11
 * @author Cay Horstmann
 */

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.util.*;
import javax.swing.*;

public class PaintTest
{  public static void main(String[] args)
   {  JFrame frame = new PaintTestFrame();
      frame.show();
   }
}

class PaintTestFrame extends JFrame
   implements ActionListener
{  public PaintTestFrame()
   {  setTitle("PaintTest");
      setSize(400, 400);
      addWindowListener(new WindowAdapter()
         {  public void windowClosing(WindowEvent e)
            {  System.exit(0);
            }
         } );

      Container contentPane = getContentPane();
      canvas = new PaintPanel();
      contentPane.add(canvas, "Center");

      JPanel buttonPanel = new JPanel();
      ButtonGroup group = new ButtonGroup();

      colorButton = new JRadioButton("Color", true);
      buttonPanel.add(colorButton);
      group.add(colorButton);
      colorButton.addActionListener(this);

      gradientPaintButton
         = new JRadioButton("Gradient Paint", false);
      buttonPanel.add(gradientPaintButton);
      group.add(gradientPaintButton);
      gradientPaintButton.addActionListener(this);

      texturePaintButton
         = new JRadioButton("Texture Paint", false);
      buttonPanel.add(texturePaintButton);
      group.add(texturePaintButton);
      texturePaintButton.addActionListener(this);

      contentPane.add(buttonPanel, "North");
   }

   public void actionPerformed(ActionEvent event)
   {  Object source = event.getSource();
      if (source == colorButton)
         canvas.setColor();
      else if (source == gradientPaintButton)
         canvas.setGradientPaint();
      else if (source == texturePaintButton)
         canvas.setTexturePaint();
   }

   private PaintPanel canvas;
   private JRadioButton colorButton;
   private JRadioButton gradientPaintButton;
   private JRadioButton texturePaintButton;
}

class PaintPanel extends JPanel
{  public PaintPanel()
   {  Image image = Toolkit.getDefaultToolkit().getImage
         ("blue-ball.gif");
      MediaTracker tracker = new MediaTracker(this);
      tracker.addImage(image, 0);
      try { tracker.waitForID(0); }
      catch (InterruptedException e) {}
      bufferedImage = new BufferedImage(image.getWidth(null),
         image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
      Graphics2D g2 = bufferedImage.createGraphics();
      g2.drawImage(image, 0, 0, null);

      setColor();
   }

   public void paintComponent(Graphics g)
   {  super.paintComponent(g);
      Graphics2D g2 = (Graphics2D)g;
      g2.setPaint(paint);
      Ellipse2D circle
         = new Ellipse2D.Double(0, 0, getWidth(), getHeight());
      g2.fill(circle);
   }

   public void setColor()
   {  paint = Color.red; // Color implements Paint
      repaint();
   }

   public void setGradientPaint()
   {  paint = new GradientPaint(0, 0, Color.red,
         (float)getWidth(), (float)getHeight(), Color.blue);
      repaint();
   }

   public void setTexturePaint()
   {  Rectangle2D anchor = new Rectangle2D.Double(0, 0,
         2 * bufferedImage.getWidth(),
         2 * bufferedImage.getHeight());
      paint = new TexturePaint(bufferedImage, anchor);
      repaint();
   }

   private Paint paint;
   private BufferedImage bufferedImage;
}
