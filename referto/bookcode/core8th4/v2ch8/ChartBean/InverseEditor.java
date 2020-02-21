/**
 * @version 1.10 1997-10-27
 * @author Cay Horstmann
 */

import java.awt.*;
import java.beans.*;

public class InverseEditor extends PropertyEditorSupport
{  public Component getCustomEditor()
   {  return new InverseEditorPanel(this);
   }

   public boolean supportsCustomEditor()
   {  return true;
   }

   public boolean isPaintable()
   {  return true;
   }

   public void paintValue(Graphics g, Rectangle box)
   {  boolean isInverse = ((Boolean)getValue()).booleanValue();
      String s = isInverse ? "Inverse" : "Normal";
      g.setColor(isInverse ? Color.black : Color.white);
      g.fillRect(box.x, box.y, box.width, box.height);
      g.setColor(isInverse ? Color.white : Color.black);
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


