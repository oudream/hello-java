/**
 * @version 1.20 7 Oct 1998
 * @author Cay Horstmann
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class CircleLayoutFrame extends JFrame
{  public CircleLayoutFrame()
   {  setTitle("CircleLayoutTest");
      setSize(300, 300);
      addWindowListener(new WindowAdapter()
      {  public void windowClosing(WindowEvent e)
         {  System.exit(0);
         }
      } );

      getContentPane().setLayout(new CircleLayout());
      getContentPane().add(new Button("Yes"));
      getContentPane().add(new Button("No"));
      getContentPane().add(new Button("Ok"));
      getContentPane().add(new Button("Cancel"));
      getContentPane().add(new Button("Abort"));
      getContentPane().add(new Button("Retry"));
      getContentPane().add(new Button("Ignore"));
   }
}

class CircleLayout implements LayoutManager
{  public void addLayoutComponent(String name,
      Component comp)
   {}

   public void removeLayoutComponent(Component comp)
   {}

   public void setSizes(Container parent)
   {  if (sizesSet) return;
      int n = parent.getComponentCount();

      preferredWidth = 0;
      preferredHeight = 0;
      minWidth = 0;
      minHeight = 0;
      maxComponentWidth = 0;
      maxComponentHeight = 0;

      for (int i = 0; i < n; i++)
      {  Component c = parent.getComponent(i);
         if (c.isVisible()) {
         Dimension d = c.getPreferredSize();
         maxComponentWidth = Math.max(maxComponentWidth,
            d.width);
         maxComponentHeight = Math.max(maxComponentWidth,
            d.height);
         preferredHeight += d.height;
         }
      }
      preferredHeight += maxComponentHeight;
      preferredWidth = 2 * maxComponentWidth;
      minHeight = preferredHeight;
      minWidth = preferredWidth;
      sizesSet = true;
   }

   public Dimension preferredLayoutSize(Container parent)
   {  Dimension dim = new Dimension(0, 0);
      setSizes(parent);
      Insets insets = parent.getInsets();
      dim.width = preferredWidth + insets.left
         + insets.right;
      dim.height = preferredHeight + insets.top
         + insets.bottom;
      return dim;
   }

   public Dimension minimumLayoutSize(Container parent)
   {  Dimension dim = new Dimension(0, 0);
      setSizes(parent);
      Insets insets = parent.getInsets();
      dim.width = minWidth + insets.left + insets.right;
      dim.height = minHeight + insets.top + insets.bottom;
      return dim;
   }

   public void layoutContainer(Container parent)
   {  Insets insets = parent.getInsets();
      int containerWidth = parent.getSize().width
         - insets.left - insets.right;
      int containerHeight = parent.getSize().height
         - insets.top - insets.bottom;
      int xradius = (containerWidth - maxComponentWidth)
         / 2;
      int yradius = (containerHeight - maxComponentHeight)
         / 2;

      setSizes(parent);
      int xcenter = insets.left + containerWidth / 2;
      int ycenter = insets.top + containerHeight / 2;

      int n = parent.getComponentCount();
      for (int i = 0; i < n; i++)
      {  Component c = parent.getComponent(i);
         if (c.isVisible())
         {  Dimension d = c.getPreferredSize();
            double angle = 2 * Math.PI * i / n;
            int x = xcenter
               + (int)(Math.cos(angle) * xradius);
            int y = ycenter
               + (int)(Math.sin(angle) * yradius);

            c.setBounds(x - d.width / 2, y - d.width / 2,
               d.width, d.height);
         }
      }

   }

   private int minWidth = 0;
   private int minHeight = 0;
   private int preferredWidth = 0, preferredHeight = 0;
   private boolean sizesSet = false;
   private int maxComponentWidth = 0;
   private int maxComponentHeight = 0;
}

public class CircleLayoutTest {
   public static void main(String[] args)
   {  JFrame f = new CircleLayoutFrame();
      f.show();
   }
}
