/**
 * @version 1.20 21 Jun 1998
 * @author Cay Horstmann
 */

import java.awt.event.*;
import javax.swing.*;

class CloseableFrame extends JFrame
{  public CloseableFrame()
   {  setTitle("CloseableFrame");
      setSize(300, 200);
      addWindowListener(new WindowAdapter()
         {  public void windowClosing(WindowEvent e)
            {  System.exit(0);
            }
         } );
   }
}

public class CloseableTest
{  public static void main(String[] args)
   {  JFrame frame = new CloseableFrame();
      frame.show();  
   }
}
