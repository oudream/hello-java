package ManyThreads.Priority;

/**
 * 说明：
 *
 * @Auther: 11432_000
 * @Date: 2019/1/7 14:18
 * @Description:
 */
public class TestMain {
    public static void main(String[] args) {
        Thread thread1 = new Thread(new MyPriority() ,"001");
        Thread thread2 = new Thread(new MyPriority() ,"002");
        thread1.setPriority(Thread.MIN_PRIORITY);
        thread2.setPriority(Thread.MAX_PRIORITY);
        thread1.start();
        thread2.start();
    }
}
class MyPriority implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName());
            Thread.yield();
        }

    }
}
