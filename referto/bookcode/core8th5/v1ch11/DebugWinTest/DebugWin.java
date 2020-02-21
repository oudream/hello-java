/**
 * @version 1.20 06 May 1998
 * @author Cay Horstmann
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class DebugWin extends JFrame
{  public void print(Object ob)
   {  output.append("\n" + ob);
   }

   public DebugWin()
   {  setTitle("DebugWin");
      output.setEditable(false);
      output.setText("[DebugWin]");
      getContentPane().add(new JScrollPane(output), 
         "Center");
      setSize(300, 200);
      setLocation(200, 200);
      addWindowListener(new WindowAdapter() 
      {  public void windowClosing(WindowEvent e) 
         { setVisible(false); 
         } 
      } );
      show();
   }

   private JTextArea output = new JTextArea();
}



