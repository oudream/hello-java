/**
 * @version 1.20 27 Jun 1998
 * @author Cay Horstmann
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class CalculatorPanel extends JPanel
   implements ActionListener
{  public CalculatorPanel()
   {  setLayout(new BorderLayout());
      
      display = new JTextField("0");
      display.setEditable(false);
      add(display, "North");
      
      JPanel p = new JPanel();
      p.setLayout(new GridLayout(4, 4));
      String buttons = "789/456*123-0.=+";
      for (int i = 0; i < buttons.length(); i++) 
         addButton(p, buttons.substring(i, i + 1));      
      add(p, "Center");
   }

   private void addButton(Container c, String s)
   {  JButton b = new JButton(s);
      c.add(b);
      b.addActionListener(this);
   }
   
   public void actionPerformed(ActionEvent evt)
   {  String s = evt.getActionCommand();
      if ('0' <= s.charAt(0) && s.charAt(0) <= '9' 
         || s.equals("."))
      {  if (start) display.setText(s);
         else display.setText(display.getText() + s);
         start = false;
      }
      else
      {  if (start)
         {  if (s.equals("-")) 
            { display.setText(s); start = false; }
            else op = s;
         }
         else
         {  calculate(Double.parseDouble(display.getText()));
            op = s;
            start = true;
         }
      }
   }
   
   public void calculate(double n)
   {  if (op.equals("+")) arg += n;
      else if (op.equals("-")) arg -= n;
      else if (op.equals("*")) arg *= n;
      else if (op.equals("/")) arg /= n;
      else if (op.equals("=")) arg = n;
      display.setText("" + arg);
   }
   
   private JTextField display;
   private double arg = 0;
   private String op = "=";
   private boolean start = true;
}

public class CalculatorApplet extends JApplet
{  public void init()
   {  Container contentPane = getContentPane();
      contentPane.add(new CalculatorPanel());
   }
}