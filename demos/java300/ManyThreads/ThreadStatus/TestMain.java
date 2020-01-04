package ManyThreads.ThreadStatus;

/**
 * 说明：
 *
 * @Auther: 11432_000
 * @Date: 2019/1/7 13:49
 * @Description:
 */
public class TestMain {

    public static void main(String[] args)throws Exception {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(1000);
                    if (i % 2 == 0){
                        Thread.yield();
                    }
                    System.out.println(Thread.currentThread().getState());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        System.out.println(thread.getState());
        thread.start();
        System.out.println(thread.getState());
        Thread.yield();
        System.out.println(thread.getState());
        Thread.sleep(15000);
        System.out.println(thread.getState());

    }
}
