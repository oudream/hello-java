/**
 * @version 1.20 27 Jun 1998
 * @author Cay Horstmann
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class CheckBoxFrame extends JFrame 
   implements ActionListener
{  public CheckBoxFrame()
   {  setTitle("CheckBoxTest");
      setSize(300, 200);
      addWindowListener(new WindowAdapter()
         {  public void windowClosing(WindowEvent e)
            {  System.exit(0);
            }
         } );
         
      JPanel p = new JPanel();
      bold = addCheckBox(p, "Bold");    
      italic = addCheckBox(p, "Italic");    
      getContentPane().add(p, "South");
      panel = new CheckBoxTestPanel();
      getContentPane().add(panel, "Center");
   }
   
   public JCheckBox addCheckBox(JPanel p, String name)
   {  JCheckBox c = new JCheckBox(name);
      c.addActionListener(this);
      p.add(c);
      return c;
   }
   
   public void actionPerformed(ActionEvent evt)
   {  int m = (bold.isSelected() ? Font.BOLD : 0) 
         + (italic.isSelected() ? Font.ITALIC : 0);
      panel.setFont(m);
   }
 
   private CheckBoxTestPanel panel;
   private JCheckBox bold;
   private JCheckBox italic;        
}

class CheckBoxTestPanel extends JPanel
{  public CheckBoxTestPanel() 
   { setFont(Font.PLAIN); 
   }

   public void setFont(int m)
   {  setFont(new Font("SansSerif", m, 12));
      repaint();
   }

   public void paintComponent(Graphics g)
   {  super.paintComponent(g); 
      g.drawString
         ("The quick brown fox jumps over the lazy dog.", 
         0, 50);
   }
}

public class CheckBoxTest
{  public static void main(String[] args)
   {  JFrame frame = new CheckBoxFrame();
      frame.show();
   }
}

