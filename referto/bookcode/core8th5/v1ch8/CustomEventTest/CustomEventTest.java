/**
 * @version 1.20 25 Jun 1998
 * @author Cay Horstmann
 */

import java.awt.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;

class CustomEventPanel extends JPanel
   implements TimerListener
{  public CustomEventPanel()
   {  Timer t = new Timer(1000);
      t.addTimerListener(this);
   }

   public void timeElapsed(TimerEvent evt)
   {  Graphics g = getGraphics();
      g.fillRect(0, 0, ticks, 10);
      ticks++;
      g.dispose();
   }

   private int ticks = 0;
}

interface TimerListener extends EventListener
{  public void timeElapsed(TimerEvent evt);
}

class Timer extends Component implements Runnable
{  public Timer(int i) 
   {  interval = i;
      Thread t = new Thread(this);
      t.start();
      evtq = Toolkit.getDefaultToolkit()
         .getSystemEventQueue();
      enableEvents(0);
   }

   public void addTimerListener(TimerListener l)
   {  listener = l;
   }

   public void run()
   {  while (true)
      {  try { Thread.sleep(interval); } 
         catch(InterruptedException e) {}
         TimerEvent te = new TimerEvent(this); 
         evtq.postEvent(te);   
      }
   }

   public void processEvent(AWTEvent evt)
   {  if (evt instanceof TimerEvent)
      {  if (listener != null)
            listener.timeElapsed((TimerEvent)evt);
      }
      else super.processEvent(evt);
   }

   private int interval;
   private TimerListener listener;
   private static EventQueue evtq;
}

class TimerEvent extends AWTEvent
{  public TimerEvent(Timer t) { super(t, TIMER_EVENT); }
   public static final int TIMER_EVENT 
      = AWTEvent.RESERVED_ID_MAX  + 5555;
}

class CustomEventFrame extends JFrame
{  public CustomEventFrame()
   {  setTitle("CustomEventTest");
      setSize(300, 50);
      addWindowListener(new WindowAdapter()
         {  public void windowClosing(WindowEvent e)
            {  System.exit(0);
            }
         } );

      Container contentPane = getContentPane();
      contentPane.add(new CustomEventPanel());
   }
}

public class CustomEventTest
{  public static void main(String[] args)
   {  JFrame frame = new CustomEventFrame();
      frame.show();  
   }
}
