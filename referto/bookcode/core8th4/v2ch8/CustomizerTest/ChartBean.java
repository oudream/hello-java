/**
 * @version 1.20 1999-09-28
 * @author Cay Horstmann
 */

import java.awt.*;
import java.util.*;
import java.beans.*;
import java.io.*;
import javax.swing.*;

public class ChartBean extends JPanel
   implements Serializable
{  public void paint(Graphics g)
   {  if (values == null || values.length == 0) return;
      int i;
      double minValue = 0;
      double maxValue = 0;
      for (i = 0; i < values.length; i++)
      {  if (minValue > getValues(i)) minValue = getValues(i);
         if (maxValue < getValues(i)) maxValue = getValues(i);
      }
      if (maxValue == minValue) return;

      Dimension d = getSize();
      int clientWidth = d.width;
      int clientHeight = d.height;
      int barWidth = clientWidth / values.length;

      g.setColor(inverse ? color : Color.white);
      g.fillRect(0, 0, clientWidth, clientHeight);
      g.setColor(Color.black);

      Font titleFont = new Font("SansSerif", Font.BOLD, 20);
      FontMetrics titleFontMetrics
         = g.getFontMetrics(titleFont);

      int titleWidth = titleFontMetrics.stringWidth(title);
      int y = titleFontMetrics.getAscent();
      int x;
      if (titlePosition == LEFT)
         x = 0;
      else if (titlePosition == CENTER)
         x = (clientWidth - titleWidth) / 2;
      else
         x = clientWidth - titleWidth;

      g.setFont(titleFont);
      g.drawString(title, x, y);

      int top = titleFontMetrics.getHeight();
      double scale = (clientHeight - top)
         / (maxValue - minValue);
      y = clientHeight;

      for (i = 0; i < values.length; i++)
      {  int x1 = i * barWidth + 1;
         int y1 = top;
         int height = (int)(getValues(i) * scale);
         if (getValues(i) >= 0)
            y1 += (int)((maxValue - getValues(i)) * scale);
         else
         {  y1 += (int)(maxValue * scale);
            height = -height;
         }

         g.setColor(inverse ? Color.white : color);
         g.fillRect(x1, y1, barWidth - 2, height);
         g.setColor(Color.black);
         g.drawRect(x1, y1, barWidth - 2, height);
      }
   }
   public void setTitle(String t) { title = t; }
   public String getTitle() { return title; }


   public double[] getValues() { return values; }

   public void setValues(double[] v) { values = v; }

   public double getValues(int i)
   {  if (0 <= i && i < values.length) return values[i];
      return 0;
   }

   public void setValues(int i, double value)
   {  if (0 <= i && i < values.length) values[i] = value;
   }

   public boolean isInverse()
   {  return inverse;
   }

   public void setTitlePosition(int p) { titlePosition = p; }

   public int getTitlePosition()
   {  return titlePosition;
   }

   public void setInverse(boolean b) { inverse = b; }

   public Dimension getMinimumSize()
   {  return new Dimension(MINSIZE, MINSIZE);
   }

   public void setGraphColor(Color c) { color = c; }
   public Color getGraphColor() { return color; }

   private static final int LEFT = 0;
   private static final int CENTER = 1;
   private static final int RIGHT = 2;

   private static final int MINSIZE = 50;
   private double[] values = { 1, 2, 3 };
   private String title = "Title";
   private int titlePosition = CENTER;
   private boolean inverse;
   private Color color = Color.red;
}
