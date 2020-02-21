/**
 * @version 1.20 21 Jun 1998
 * @author Cay Horstmann
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class CenteredFrame extends JFrame
{  public CenteredFrame()
   {  setTitle("CenteredFrame");
      addWindowListener(new WindowAdapter()
         {  public void windowClosing(WindowEvent e)
            {  System.exit(0);
            }
         } );
      Toolkit tk = Toolkit.getDefaultToolkit();
      Dimension d = tk.getScreenSize();
      int screenHeight = d.height;
      int screenWidth = d.width;
      setSize(screenWidth / 2, screenHeight / 2);
      setLocation(screenWidth / 4, screenHeight / 4);
      Image img = tk.getImage("icon.gif");
      setIconImage(img);
   }
}

public class CenteredTest
{  public static void main(String[] args)
   {  JFrame frame = new CenteredFrame();
      frame.show();  
   }
}

