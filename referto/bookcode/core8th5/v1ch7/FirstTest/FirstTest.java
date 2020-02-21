/**
 * @version 1.20 21 Jun 1998
 * @author Cay Horstmann
 */

import javax.swing.*;

class FirstFrame extends JFrame
{  public FirstFrame()
   {  setTitle("FirstFrame");
      setSize(300, 200);
   }
}

public class FirstTest
{  public static void main(String[] args)
   {  JFrame frame = new FirstFrame();
      frame.show();  
   }
}

