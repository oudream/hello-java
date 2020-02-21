/**
 * @version 1.10 1997-10-27
 * @author Cay Horstmann
 */

import java.awt.*;
import java.beans.*;

public class DoubleArrayEditor extends PropertyEditorSupport
{  public Component getCustomEditor()
   {  return new DoubleArrayEditorPanel(this);
   }

   public boolean supportsCustomEditor()
   {  return true;
   }

   public boolean isPaintable()
   {  return true;
   }

   public void paintValue(Graphics g, Rectangle box)
   {  double[] values = (double[]) getValue();
      String s = "";
      for (int i = 0; i < 3; i++)
      {  if (values.length > i) s = s + values[i];
         if (values.length > i + 1) s = s + ", ";
      }
      if (values.length > 3) s += "...";

      g.setColor(Color.white);
      g.fillRect(box.x, box.y, box.width, box.height);
      g.setColor(Color.black);
      FontMetrics fm = g.getFontMetrics();
      int w = fm.stringWidth(s);
      int x = box.x;
      if (w < box.width) x += (box.width - w) / 2;
      int y = box.y + (box.height - fm.getHeight()) / 2
         + fm.getAscent();
      g.drawString(s, x, y);
   }

   public String getAsText()
   {  return null;
   }
}


