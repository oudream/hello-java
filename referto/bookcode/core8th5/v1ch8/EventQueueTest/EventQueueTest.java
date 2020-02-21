/**
 * @version 1.20 25 Jun 1998
 * @author Cay Horstmann
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class EventQueuePanel extends JPanel
   implements ActionListener
{  EventQueuePanel()
   {  JButton button = new JButton("Draw line");
      add(button);
      button.addActionListener(this);
   }
   
   public void actionPerformed(ActionEvent evt)
   {  Graphics g = getGraphics();

      displayPrompt(g, "Please click on a point");
      Point p = getClick();
      g.drawOval(p.x - 2, p.y - 2, 4, 4);
      displayPrompt(g, "Please click on another point");
      Point q = getClick();
      g.drawOval(q.x - 2, q.y - 2, 4, 4);
      g.drawLine(p.x, p.y, q.x, q.y);
      displayPrompt(g, "Done!");
      g.dispose();
   }

   public void displayPrompt(Graphics g, String s)
   {  y += 20;
      g.drawString(s, 0, y);
   }

   public Point getClick()
   {  EventQueue eq 
         = Toolkit.getDefaultToolkit()
            .getSystemEventQueue();
      while (true)
      {  try
         {  AWTEvent evt = eq.getNextEvent();
            if (evt.getID() == MouseEvent.MOUSE_PRESSED)
            {  MouseEvent mevt = (MouseEvent)evt;
               Point p = mevt.getPoint();
               Point top = getRootPane().getLocation();
               p.x -= top.x;
               p.y -= top.y;
               return p;         
            }
         }
         catch(InterruptedException e)
         {}
      }
   }
   
   private int y = 60;
}

class EventQueueFrame extends JFrame
{  public EventQueueFrame()
   {  setTitle("EventQueueTest");
      setSize(300, 200);
      addWindowListener(new WindowAdapter()
         {  public void windowClosing(WindowEvent e)
            {  System.exit(0);
            }
         } );

      Container contentPane = getContentPane();
      contentPane.add(new EventQueuePanel());
   }
}

public class EventQueueTest
{  public static void main(String[] args)
   {  Frame frame = new EventQueueFrame();
      frame.show();  
   }
}
