/**
 * @version 1.10 1997-10-27
 * @author Cay Horstmann
 */

import java.beans.*;

public class TitlePositionEditor
   extends PropertyEditorSupport
{  public String getAsText()
   {  int value = ((Integer)getValue()).intValue();
      return options[value];
   }

   public void setAsText(String s)
   {  for (int i = 0; i < options.length; i++)
      {  if (options[i].equals(s))
         {  setValue(new Integer(i));
            return;
         }
      }
   }

   public String[] getTags() { return options; }

   private String[] options = { "Left", "Center", "Right" };
}

