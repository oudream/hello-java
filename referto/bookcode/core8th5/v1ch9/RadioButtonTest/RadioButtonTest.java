/**
 * @version 1.20 27 Jun 1997
 * @author Cay Horstmann
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
 
class RadioButtonFrame extends JFrame 
   implements ActionListener
{  public RadioButtonFrame()
   {  setTitle("RadioButtonTest");
      setSize(400, 200);
      addWindowListener(new WindowAdapter()
      {  public void windowClosing(WindowEvent e)
         {  System.exit(0);
         }
      } );
      
      JPanel buttonPanel = new JPanel();
      ButtonGroup group = new ButtonGroup();
      smallButton = 
         addRadioButton(buttonPanel, group, "Small", false);
      mediumButton = 
         addRadioButton(buttonPanel, group, "Medium", true);    
      largeButton = 
         addRadioButton(buttonPanel, group, "Large", false);    
      xlargeButton = 
         addRadioButton(buttonPanel, group, "Extra large", 
         false);
      getContentPane().add(buttonPanel, "South");
      panel = new RadioButtonTestPanel();
      getContentPane().add(panel, "Center");
   }

   public JRadioButton addRadioButton(JPanel buttonPanel, 
      ButtonGroup g, String buttonName, boolean v)
   {  JRadioButton button = new JRadioButton(buttonName, v);
      button.addActionListener(this);
      g.add(button);
      buttonPanel.add(button);
      return button;
   }
   
   public void actionPerformed(ActionEvent evt)
   {  Object source = evt.getSource();
      if(source == smallButton)
         panel.setSize(8);
      else if (source == mediumButton) 
         panel.setSize(12);
      else if (source == largeButton) 
         panel.setSize(14);
      else if (source == xlargeButton) 
         panel.setSize(18);
   }
   
   private RadioButtonTestPanel panel;
   private JRadioButton smallButton;
   private JRadioButton mediumButton;
   private JRadioButton largeButton;
   private JRadioButton xlargeButton;
}

class RadioButtonTestPanel extends JPanel
{  public RadioButtonTestPanel() 
   {  setSize(12); 
   }
   
   public void setSize(int p)
   {  setFont(new Font("SansSerif", Font.PLAIN, p));
      repaint();
   }
   
   public void paintComponent(Graphics g)
   {  super.paintComponent(g);
      g.drawString
         ("The quick brown fox jumps over the lazy dog.", 
         0, 50);
   }
}

public class RadioButtonTest
{  public static void main(String[] args)
   {  JFrame frame = new RadioButtonFrame();
      frame.show();
   }
}
