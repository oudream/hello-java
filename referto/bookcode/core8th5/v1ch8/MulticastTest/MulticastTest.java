/**
 * @version 1.20 25 Jun 1998
 * @author Cay Horstmann
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class MulticastPanel extends JPanel 
   implements ActionListener
{  public MulticastPanel()
   {  JButton newButton = new JButton("New");
      add(newButton);
      newButton.addActionListener(this); 

      closeAllButton = new JButton("Close all");
      add(closeAllButton);
   }

   public void actionPerformed(ActionEvent evt)
   {  // handles New button
      SimpleFrame f = new SimpleFrame();
      counter++;
      f.setTitle("Window " + counter);
      f.setSize(200, 150);
      f.setLocation(30 * counter, 30 * counter);
      f.show();
      closeAllButton.addActionListener(f);
   }

   private int counter = 0;   
   private JButton closeAllButton;
}   

class MulticastFrame extends JFrame
{  public MulticastFrame()
   {  setTitle("MulticastTest");
      setSize(300, 200);
      addWindowListener(new WindowAdapter()
         {  public void windowClosing(WindowEvent e)
            {  System.exit(0);
            }
         } );

      Container contentPane = getContentPane();
      contentPane.add(new MulticastPanel());   
   }
}

public class MulticastTest
{  public static void main(String[] args)
   {  JFrame frame = new MulticastFrame();
      frame.show();  
   }
}

class SimpleFrame extends JFrame 
   implements ActionListener
{  public void actionPerformed(ActionEvent evt)
   {  // handles Close all button
      dispose();
   }
}