/**
 * @version 1.10 1997-10-27
 * @author Cay Horstmann
 */

import java.util.*;

public interface TimerListener extends EventListener
{  public void timeElapsed(TimerEvent evt);
}

