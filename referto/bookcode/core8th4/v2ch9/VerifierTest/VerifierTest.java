/**
 * @version 1.00 1997-09-10
 * @author Cay Horstmann
 */

import java.awt.*;
import java.applet.*;

public class VerifierTest extends Applet
{  public static void main(String[] args)
   {  System.out.println("1 + 2 == " + fun());
   }

   static int fun()
   {  int m;
      int n;
      m = 1;
      n = 2;
      // used hex editor to change to "m = 2" in class file
      int r = m + n;
      return r;
   }

   public void paint(Graphics g)
   {  g.drawString("1 + 2 == " + fun(), 20, 20);
   }
}
