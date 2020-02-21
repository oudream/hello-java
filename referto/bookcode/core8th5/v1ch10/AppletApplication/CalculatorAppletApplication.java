/**
 * @version 1.20 27 Jun 1998
 * @author Cay Horstmann
 */

public class CalculatorAppletApplication 
   extends CalculatorApplet
// It's an applet. It's an application. It's BOTH!
{  public static void main(String[] args)
   {  new AppletFrame(new CalculatorApplet(), 180, 180);
   }
}

