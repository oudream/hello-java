/**
 * @version 1.20 26 Jun 1998
 * @author Cay Horstmann
 * Copyright (C) Cay Horstmann 1998
 */

import java.awt.*;
import javax.swing.*;

class NotHelloWorldPanel extends JPanel
{  public void paintComponent(Graphics g)
   {  super.paintComponent(g);
      g.drawString("Not a Hello, World applet", 75, 100);
   }
}

public class NotHelloWorldApplet extends JApplet
{  public void init()
   {  Container contentPane = getContentPane();
      contentPane.add(new NotHelloWorldPanel());
   }
}





