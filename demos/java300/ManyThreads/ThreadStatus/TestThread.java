package ManyThreads.ThreadStatus;

/**
 * 说明：
 *
 * @Auther: 11432_000
 * @Date: 2018/12/28 14:08
 * @Description:
 */
public class TestThread implements Runnable{

    @Override
    public void run() {
        testYield();
    }

    public void testYield(){
        System.out.println(Thread.currentThread().getName() + "-->" + "start");
        Thread.yield();
        System.out.println(Thread.currentThread().getName() + "-->" + "stop");
    }
}
