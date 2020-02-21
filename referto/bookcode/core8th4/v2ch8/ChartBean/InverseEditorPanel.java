/**
 * @version 1.20 1999-09-28
 * @author Cay Horstmann
 */

import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.lang.reflect.*;
import java.beans.*;
import javax.swing.*;

public class InverseEditorPanel extends JPanel
{  public InverseEditorPanel(PropertyEditorSupport ed)
   {  editor = ed;
      ButtonGroup g = new ButtonGroup();
      boolean isInverse
         = ((Boolean)editor.getValue()).booleanValue();
      normal = new JCheckBox("Normal", !isInverse);
      inverse = new JCheckBox("Inverse", isInverse);

      g.add(normal);
      g.add(inverse);
      add(normal);
      add(inverse);

      ActionListener buttonListener =
         new ActionListener()
         {  public void actionPerformed(ActionEvent event)
            {  editor.setValue(new Boolean(inverse.isSelected()));
               editor.firePropertyChange();
            }
         };

      normal.addActionListener(buttonListener);
      inverse.addActionListener(buttonListener);
   }

   private JCheckBox normal;
   private JCheckBox inverse;
   PropertyEditorSupport editor;
}
