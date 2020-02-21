/**
 * @version 1.20 27 Jun 1997
 * @author Cay Horstmann
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
 
class BorderFrame extends JFrame 
   implements ActionListener
{  public BorderFrame()
   {  JPanel buttonPanel = new JPanel();
      group = new ButtonGroup();
      addRadioButton(buttonPanel, group, "Lowered bevel", 
         true);    
      addRadioButton(buttonPanel, group, "Raised bevel", 
         false);    
      addRadioButton(buttonPanel, group, "Etched", 
         false);
      addRadioButton(buttonPanel, group, "Line", 
         false);
      addRadioButton(buttonPanel, group, "Matte", 
         false);
      addRadioButton(buttonPanel, group, "Empty", 
         false);
         
      Border etched = BorderFactory.createEtchedBorder();
      Border titled = BorderFactory.createTitledBorder
         (etched, "Border types");
      buttonPanel.setBorder(titled);
         
      getContentPane().add(buttonPanel, "South");
      
      setDemoPanel();
      
      setTitle("BorderTest");
      setSize(600, 200);
      addWindowListener(new WindowAdapter()
      {  public void windowClosing(WindowEvent e)
         {  System.exit(0);
         }
      } );
   }

   public void addRadioButton(JPanel buttonPanel, 
      ButtonGroup g, String buttonName, boolean v)
   {  JRadioButton button = new JRadioButton(buttonName, v);
      button.addActionListener(this);
      g.add(button);
      buttonPanel.add(button);
      button.setActionCommand(buttonName);
   }
   
   public void actionPerformed(ActionEvent evt)
   {  setDemoPanel();
   }
   
   public void setDemoPanel()
   {  JPanel panel = new JPanel();
      Border border = null;
      String command = group.getSelection()
         .getActionCommand();
      if (command.equals("Lowered bevel"))
         border = BorderFactory.createLoweredBevelBorder();
      else if (command.equals("Raised bevel"))
         border = BorderFactory.createRaisedBevelBorder();
      else if (command.equals("Etched"))
         border = BorderFactory.createEtchedBorder();
      else if (command.equals("Line"))
         border 
            = BorderFactory.createLineBorder(Color.blue);
      else if (command.equals("Matte"))
         border = BorderFactory.createMatteBorder(10, 10,
            10, 10, Color.blue);
      else if (command.equals("Empty"))
         border = BorderFactory.createEmptyBorder();

      panel.setBorder(border);
      getContentPane().add(panel, "Center");
      validate();
   }
  
   private JPanel panel;
   private ButtonGroup group;
}

public class BorderTest
{  public static void main(String[] args)
   {  JFrame frame = new BorderFrame();
      frame.show();
   }
}
