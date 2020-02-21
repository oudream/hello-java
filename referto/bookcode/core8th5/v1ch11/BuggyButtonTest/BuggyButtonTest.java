/**
 * @version 1.10 25 Mar 1997
 * @author Cay Horstmann
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class BuggyButtonTest extends JFrame 
   implements ActionListener
{  public BuggyButtonTest()
   {  pane = new JPanel();

      JButton yellowButton = new JButton("Yellow");
      pane.add(yellowButton);
      yellowButton.addActionListener(this); 

      JButton blueButton = new JButton("Blue");
      pane.add(blueButton);
      blueButton.addActionListener(this); 

      JButton redButton = new JButton("Red");
      pane.add(redButton);
      redButton.addActionListener(this);

      Container contentPane = getContentPane();
      contentPane.add(pane);
   }
   
   public void actionPerformed(ActionEvent evt)
   {  String arg = evt.getActionCommand();
      Color color = Color.black;
      if (arg.equals("yellow")) color = Color.yellow;
      else if (arg.equals("blue")) color = Color.blue;
      else if (arg.equals("red")) color = Color.red;
      pane.setBackground(color);
      repaint();
   }
   
   public static void main(String[] args)
   {  JFrame f = new BuggyButtonTest();
      f.addWindowListener(new WindowAdapter() 
         {  public void windowClosing(WindowEvent e) 
            { System.exit(0); } 
         } );
      f.setSize(400, 400);
      f.show();
   }

   private JPanel pane;
}
