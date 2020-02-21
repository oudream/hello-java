/**
 * @version 1.10 1999-09-28
 * @author Cay Horstmann
 */

import java.awt.*;
import java.util.*;
import java.io.*;

public class TimerBean implements Runnable, Serializable
{  public int getInterval() { return interval; }
   public void setInterval(int i) { interval = i; }

   public boolean isRunning() { return runner != null; }
   public void setRunning(boolean b)
   {  if (b && runner == null)
      {  runner = new Thread(this);
         runner.start();
      }
      else if (!b && runner != null)
      {  runner.interrupt();
         runner = null;
      }
   }

   public synchronized void addTimerListener
      (TimerListener l)
   {  timerListeners.addElement(l);
   }

   public synchronized void removeTimerListener
      (TimerListener l)
   {  timerListeners.removeElement(l);
   }

   public void fireTimerEvent(TimerEvent evt)
   {  Vector currentListeners = null;
      synchronized(this)
      {  currentListeners = (Vector)timerListeners.clone();
      }
      for (int i = 0; i < currentListeners.size(); i++)
      {  TimerListener listener
            = (TimerListener)currentListeners.elementAt(i);
         listener.timeElapsed(evt);
      }
   }

   public void run()
   {  if (interval <= 0) return;
      try
      {  while (!Thread.interrupted())
         {  Thread.sleep(interval);
            fireTimerEvent(new TimerEvent(this));
         }
      }
      catch(InterruptedException e)
      {
      }
   }

   private int interval = 1000;
   private Vector timerListeners = new Vector();
   private Thread runner;
}


