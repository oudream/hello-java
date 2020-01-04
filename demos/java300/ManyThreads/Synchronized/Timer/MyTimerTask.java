package ManyThreads.Synchronized.Timer;

import java.util.TimerTask;

/**
 * 说明：定时任务
 *
 * @Auther: 11432_000
 * @Date: 2019/1/9 14:34
 * @Description:
 */
public class MyTimerTask extends TimerTask {
    @Override
    public void run() {
        System.out.println("啦啦啦啦啦啦啦啦绿绿绿绿");
    }
}
