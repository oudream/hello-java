/**
 * @version 1.10 1997-10-27
 * @author Cay Horstmann
 */

import java.util.*;

public class TimerEvent extends EventObject
{  public TimerEvent(Object source)
   {  super(source);
      now = new Date();
   }

   public Date getDate() { return now; }

   private Date now;
}

