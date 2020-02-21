/**
 * @version 1.00 1999-09-11
 * @author Cay Horstmann
 */

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;
import javax.swing.*;

public class RenderQualityTest
{  public static void main(String[] args)
   {  JFrame frame = new RenderQualityTestFrame();
      frame.show();
   }
}

class RenderQualityTestFrame extends JFrame
   implements ActionListener
{  public RenderQualityTestFrame()
   {  setTitle("RenderQualityTest");
      setSize(400, 400);
      addWindowListener(new WindowAdapter()
         {  public void windowClosing(WindowEvent e)
            {  System.exit(0);
            }
         } );

      checkBoxContainer = Box.createVerticalBox();

      antiAliasingBox
         = makeCheckBox("Antialiasing");
      qualityRenderingBox
         = makeCheckBox("Quality rendering");
      ditheringBox
         = makeCheckBox("Dithering");
      textAntiAliasingBox
         = makeCheckBox("Font antialiasing");
      fractionalMetricsBox
         = makeCheckBox("Fractional font metrics");
      qualityAlphaInterpolationBox
         = makeCheckBox("Quality alpha interpolation");
      qualityColorRenderingBox
         = makeCheckBox("Quality Color rendering");

      Container contentPane = getContentPane();
      canvas = new RenderQualityPanel();
      contentPane.add(canvas, "Center");
      contentPane.add(checkBoxContainer, "North");
   }

   JCheckBox makeCheckBox(String title)
   {  JCheckBox box = new JCheckBox(title);
      box.addActionListener(this);
      checkBoxContainer.add(box);
      return box;
   }

   public void actionPerformed(ActionEvent event)
   {  // get values from all check boxes
      RenderingHints hints = new RenderingHints(null);
      hints.put(RenderingHints.KEY_ANTIALIASING,
         antiAliasingBox.isSelected()
            ? RenderingHints.VALUE_ANTIALIAS_ON
            : RenderingHints.VALUE_ANTIALIAS_OFF);
      hints.put(RenderingHints.KEY_RENDERING,
         qualityRenderingBox.isSelected()
            ? RenderingHints.VALUE_RENDER_QUALITY
            : RenderingHints.VALUE_RENDER_SPEED);
      hints.put(RenderingHints.KEY_DITHERING,
         ditheringBox.isSelected()
            ? RenderingHints.VALUE_DITHER_ENABLE
            : RenderingHints.VALUE_DITHER_DISABLE);
      hints.put(RenderingHints.KEY_TEXT_ANTIALIASING,
         textAntiAliasingBox.isSelected()
            ? RenderingHints.VALUE_TEXT_ANTIALIAS_ON
            : RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
      hints.put(RenderingHints.KEY_FRACTIONALMETRICS,
         fractionalMetricsBox.isSelected()
            ? RenderingHints.VALUE_FRACTIONALMETRICS_ON
            : RenderingHints.VALUE_FRACTIONALMETRICS_OFF);
      hints.put(RenderingHints.KEY_ALPHA_INTERPOLATION,
         qualityAlphaInterpolationBox.isSelected()
            ? RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY
            : RenderingHints.VALUE_ALPHA_INTERPOLATION_SPEED);
      hints.put(RenderingHints.KEY_COLOR_RENDERING,
         qualityColorRenderingBox.isSelected()
            ? RenderingHints.VALUE_COLOR_RENDER_QUALITY
            : RenderingHints.VALUE_COLOR_RENDER_SPEED);
      canvas.setRenderingHints(hints);
   }

   private RenderQualityPanel canvas;
   private JCheckBox antiAliasingBox;
   private JCheckBox qualityRenderingBox;
   private JCheckBox ditheringBox;
   private JCheckBox textAntiAliasingBox;
   private JCheckBox fractionalMetricsBox;
   private JCheckBox qualityAlphaInterpolationBox;
   private JCheckBox qualityColorRenderingBox;
   private Box checkBoxContainer;
}

class RenderQualityPanel extends JPanel
{  public RenderQualityPanel()
   {  Random generator = new Random();
      color1 = new Color(0.7F, 0.7F, 0.0F, 0.5F);
      color2 = new Color(0.0F, 0.3F, 0.3F, 0.5F);
      image = Toolkit.getDefaultToolkit().getImage
         ("clouds.jpg");
      MediaTracker tracker = new MediaTracker(this);
      tracker.addImage(image, 0);
      try { tracker.waitForID(0); }
      catch (InterruptedException e) {}
   }

   public void paintComponent(Graphics g)
   {  super.paintComponent(g);
      Graphics2D g2 = (Graphics2D)g;
      g2.setRenderingHints(hints);

      g2.drawImage(image, 0, 0, null);
      g2.draw(new Ellipse2D.Double(0, 0,
         image.getWidth(null), image.getHeight(null)));
      g2.setFont(new Font("Serif", Font.ITALIC, 40));
      g2.drawString("Hello", 75, 75);
      g2.setPaint(color1);
      g2.translate(0,-80);
      g2.fill(new Rectangle2D.Double(100, 100, 200, 100));
      g2.setPaint(color2);
      g2.fill(new Rectangle2D.Double(120, 120, 200, 100));
   }

   public void setRenderingHints(RenderingHints h)
   {  hints = h;
      repaint();
   }

   private RenderingHints hints = new RenderingHints(null);
   private Color color1;
   private Color color2;
   private Image image;
}
