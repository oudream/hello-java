package ManyThreads.Synchronized.Timer;

import java.util.Timer;

/**
 * 说明：简单定时任务调度
 *
 * @Auther: 11432_000
 * @Date: 2019/1/9 14:37
 * @Description:
 */
public class Main {

    public static void main(String[] args)throws Exception {
        Timer timer = new Timer("wod" ,true);
        timer.schedule(new MyTimerTask() ,1000 ,500);
        Thread.sleep(10000);
    }
}
