/**
 * @version 1.20 06 May 1997
 * @author Cay Horstmann
 */


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DebugWinTest extends JFrame 
   implements ActionListener
{  public DebugWinTest()
   {  setTitle("DebugWinTest");
      setSize(400, 400);
      addWindowListener(new WindowAdapter()
         {  public void windowClosing(WindowEvent e)
            {  System.exit(0);
            }
         } );

      pane = new JPanel();

      yellowButton = new JButton("Yellow");
      pane.add(yellowButton);
      yellowButton.addActionListener(this); 

      blueButton = new JButton("Blue");
      pane.add(blueButton);
      blueButton.addActionListener(this); 

      redButton = new JButton("Red");
      pane.add(redButton);
      redButton.addActionListener(this);

      getContentPane().add(pane);
   }
   
   public void actionPerformed(ActionEvent evt)
   {  dw.print("Event = " + evt);
      Object source = evt.getSource();
      Color color = getBackground();
      if (source == yellowButton) color = Color.yellow;
      else if (source == blueButton) color = Color.blue;
      else if (source == redButton ) color = Color.red;
      setBackground(color);
      repaint();
   }
   
   public static void main(String[] args)
   {  JFrame f = new DebugWinTest();
      f.show();
   }

   private JPanel pane;
   private JButton yellowButton;
   private JButton blueButton;
   private JButton redButton; 
   private DebugWin dw = new DebugWin();
}
