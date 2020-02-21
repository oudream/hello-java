/**
 * @version 1.00 1999-09-11
 * @author Cay Horstmann
 */

import java.awt.*;
import java.awt.event.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.util.*;
import javax.swing.*;

public class ClipTest
{  public static void main(String[] args)
   {  JFrame frame = new ClipTestFrame();
      frame.show();
   }
}

class ClipTestFrame extends JFrame
   implements ActionListener
{  public ClipTestFrame()
   {  setTitle("ClipTest");
      setSize(300, 300);
      addWindowListener(new WindowAdapter()
         {  public void windowClosing(WindowEvent e)
            {  System.exit(0);
            }
         } );

      Container contentPane = getContentPane();
      canvas = new ClipPanel();
      contentPane.add(canvas, "Center");

      JPanel buttonPanel = new JPanel();
      ButtonGroup group = new ButtonGroup();

      noClipButton = new JRadioButton("No Clip", true);
      buttonPanel.add(noClipButton);
      group.add(noClipButton);
      noClipButton.addActionListener(this);

      clipButton = new JRadioButton("Clip", false);
      buttonPanel.add(clipButton);
      group.add(clipButton);
      clipButton.addActionListener(this);

      contentPane.add(buttonPanel, "North");
   }

   public void actionPerformed(ActionEvent event)
   {  Object source = event.getSource();
      if (source == clipButton) canvas.setClip(true);
      else if (source == noClipButton) canvas.setClip(false);
   }

   private JRadioButton clipButton;
   private JRadioButton noClipButton;

   private ClipPanel canvas;
}

class ClipPanel extends JPanel
{  public void paintComponent(Graphics g)
   {  super.paintComponent(g);
      Graphics2D g2 = (Graphics2D)g;

      if (clip)
      {  FontRenderContext context = g2.getFontRenderContext();
         Font f = new Font("Serif", Font.PLAIN, 100);
         GeneralPath clipShape = new GeneralPath();

         TextLayout layout = new TextLayout("Hello", f, context);
         AffineTransform transform
            = AffineTransform.getTranslateInstance(0, 100);
         Shape outline = layout.getOutline(transform);
         clipShape.append(outline, false);

         layout = new TextLayout("World", f, context);
         transform
            = AffineTransform.getTranslateInstance(0, 200);
         outline = layout.getOutline(transform);
         clipShape.append(outline, false);

         g2.draw(clipShape);
         g2.clip(clipShape);
      }

      final int NLINES =50;
      Point2D p = new Point2D.Double(0, 0);
      for (int i = 0; i < NLINES; i++)
      {  double x = (2 * getWidth() * i) / NLINES;
         double y = (2 * getHeight() * (NLINES - 1 - i))
            / NLINES;
         Point2D q = new Point2D.Double(x, y);
         g2.draw(new Line2D.Double(p, q));
      }
   }

   public void setClip(boolean c)
   {  clip = c;
      repaint();
   }

   private boolean clip;
}
