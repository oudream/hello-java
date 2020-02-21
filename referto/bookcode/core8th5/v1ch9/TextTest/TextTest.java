/**
 * @version 1.20 2 Jul 1998
 * @author Cay Horstmann
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

class TextTestFrame extends JFrame
   implements DocumentListener
{  public TextTestFrame()
   {  setTitle("TextTest");
      setSize(300, 200);
      addWindowListener(new WindowAdapter()
      {  public void windowClosing(WindowEvent e)
         {  System.exit(0);
         }
      } );

      Container contentPane = getContentPane();

      JPanel p = new JPanel();
      hourField = new JTextField("12", 3);
      p.add(hourField);
      hourField.getDocument().addDocumentListener(this);
      
      minuteField = new JTextField("00", 3);
      p.add(minuteField);
      minuteField.getDocument().addDocumentListener(this);
  
      contentPane.add(p, "South");
      clock = new ClockPanel();
      contentPane.add(clock, "Center");
   }
   
   public void insertUpdate(DocumentEvent e)
   {  setClock();
   }
   public void removeUpdate(DocumentEvent e)
   {  setClock();
   }
   public void changedUpdate(DocumentEvent e)
   {  
   }
   
   public void setClock()
   {  int hours 
         = Integer.parseInt(hourField.getText().trim());
      int minutes 
         = Integer.parseInt(minuteField.getText().trim());
      clock.setTime(hours, minutes);
   }
      
   private JTextField hourField;
   private JTextField minuteField;
   private ClockPanel clock;
}

class ClockPanel extends JPanel
{  public void paintComponent(Graphics g)
   {  super.paintComponent(g); 
      g.drawOval(0, 0, 100, 100);
      double hourAngle 
         = 2 * Math.PI * (minutes - 3 * 60) / (12 * 60);
      double minuteAngle 
         = 2 * Math.PI * (minutes - 15) / 60;
      g.drawLine(50, 50, 
         50 + (int)(30 * Math.cos(hourAngle)), 
         50 + (int)(30 * Math.sin(hourAngle)));
      g.drawLine(50, 50, 
         50 + (int)(45 * Math.cos(minuteAngle)), 
         50 + (int)(45 * Math.sin(minuteAngle)));
   }
   
   public void setTime(int h, int m)
   {  minutes = h * 60 + m;
      repaint();
   }
   
   private int minutes = 0;
}


public class TextTest
{  public static void main(String[] args)
   {  JFrame frame = new TextTestFrame();
      frame.show();
   }
}


