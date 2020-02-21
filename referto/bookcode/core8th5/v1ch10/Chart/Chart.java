/**
 * @version 1.20 27 Jun 1998
 * @author Cay Horstmann
 */

import java.awt.*;
import javax.swing.*;

class ChartPanel extends JPanel
{  public ChartPanel(double[] v, String[] n, String t)
   {  names = n;
      values = v;
      title = t;
   }
   
   public void paintComponent(Graphics g)
   {  super.paintComponent(g);
      if (values == null || values.length == 0) return;
      int i;
      double minValue = 0;
      double maxValue = 0;
      for (i = 0; i < values.length; i++)
      {  if (minValue > values[i]) minValue = values[i];
         if (maxValue < values[i]) maxValue = values[i];
      }
            
      Dimension d = getSize();
      int clientWidth = d.width;
      int clientHeight = d.height;
      int barWidth = clientWidth / values.length;
      
      Font titleFont 
         = new Font("SansSerif", Font.BOLD, 20);
      FontMetrics titleFontMetrics 
         = g.getFontMetrics(titleFont);
      Font labelFont 
         = new Font("SansSerif", Font.PLAIN, 10);
      FontMetrics labelFontMetrics 
         = g.getFontMetrics(labelFont);
         
      int titleWidth 
         = titleFontMetrics.stringWidth(title);
      int y = titleFontMetrics.getAscent();
      int x = (clientWidth - titleWidth) / 2;
      g.setFont(titleFont);
      g.drawString(title, x, y);
      
      int top = titleFontMetrics.getHeight();
      int bottom = labelFontMetrics.getHeight();
      if (maxValue == minValue) return;
      double scale = (clientHeight - top - bottom) 
         / (maxValue - minValue);
      y = clientHeight - labelFontMetrics.getDescent();
      g.setFont(labelFont);
      
      for (i = 0; i < values.length; i++)
      {  int x1 = i * barWidth + 1;
         int y1 = top;
         int height = (int)(values[i] * scale);
         if (values[i] >= 0)
            y1 += (int)((maxValue - values[i]) * scale);
         else
         {  y1 += (int)(maxValue * scale);
            height = -height;
         }
         
         g.setColor(Color.red);
         g.fillRect(x1, y1, barWidth - 2, height);         
         g.setColor(Color.black);
         g.drawRect(x1, y1, barWidth - 2, height);
         int labelWidth 
            = labelFontMetrics.stringWidth(names[i]);
         x = i * barWidth + (barWidth - labelWidth) / 2;
         g.drawString(names[i], x, y);
      }
   }

   private double[] values;
   private String[] names;
   private String title;
}

public class Chart extends JApplet 
{  public void init() 
   {  String v = getParameter("values");
      if (v == null) return;
      int n = Integer.parseInt(v);
      double[] values = new double[n];
      String[] names = new String[n];
      int i;
      for (i = 0; i < n; i++)
      {  values[i] = Double.parseDouble
            (getParameter("value_" + (i + 1)));
         names[i] = getParameter("name_" + (i + 1));
      }
      
      Container contentPane = getContentPane();
      contentPane.add(new ChartPanel(values, names, 
         getParameter("title")));
   }      
}   


