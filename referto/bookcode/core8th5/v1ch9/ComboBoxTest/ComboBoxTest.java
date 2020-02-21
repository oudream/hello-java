/**
 * @version 1.20 27 Jun 1998
 * @author Cay Horstmann
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class ComboBoxFrame extends JFrame 
   implements ActionListener
{  public ComboBoxFrame()
   {  setTitle("ComboBoxTest");
      setSize(300,200);
      addWindowListener(new WindowAdapter()
         {  public void windowClosing(WindowEvent e)
            {  System.exit(0);
            }
         } );
   
      style = new JComboBox();
      style.setEditable(true);
      style.addItem("Serif");
      style.addItem("SansSerif");
      style.addItem("Monospaced");
      style.addItem("Dialog");
      style.addItem("DialogInput");
      style.addActionListener(this);

      JPanel p = new JPanel();
      p.add(style);
      getContentPane().add(p, "South");
      panel = new ComboBoxTestPanel();
      getContentPane().add(panel, "Center");
   }
   
   public void actionPerformed(ActionEvent evt)
   {  JComboBox source = (JComboBox)evt.getSource();
      String item = (String)source.getSelectedItem();
      panel.setStyle(item);
   }

   private ComboBoxTestPanel panel;
   private JComboBox style;
}

class ComboBoxTestPanel extends JPanel
{  public ComboBoxTestPanel() 
   {  setStyle("Serif"); 
   }
   
   public void setStyle(String s)
   {  setFont(new Font(s, Font.PLAIN, 12));
      repaint();
   }
   
   public void paintComponent(Graphics g)
   {  super.paintComponent(g); 
      g.drawString
         ("The quick brown fox jumps over the lazy dog.", 
         0, 50);
   }
}

public class ComboBoxTest
{  public static void main(String[] args)
   {  JFrame frame = new ComboBoxFrame();
      frame.show();
   }
}

