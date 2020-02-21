/**
 * @version 1.00 07 May 1997
 * @author Cay Horstmann
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MessageCrackerTest extends JFrame
{  public MessageCrackerTest()
   {  setTitle("MessageCrackerTest");
      setSize(400, 400);
      addWindowListener(new WindowAdapter()
         {  public void windowClosing(WindowEvent e)
            {  System.exit(0);
            }
         } );

      JPanel p = new JPanel();
      p.setLayout(new BorderLayout());
      p.add(new JButton("Test"), "South");
      p.add(new JScrollBar(), "East");
      getContentPane().add(p);
      
      new MessageCracker().add(this);
   }

   public static void main(String[] args)
   {  JFrame f = new MessageCrackerTest();
      f.show();
   }
}
