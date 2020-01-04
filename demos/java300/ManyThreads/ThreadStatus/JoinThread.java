package ManyThreads.ThreadStatus;

/**
 * 说明：测试join方法
 *
 * @Auther: 11432_000
 * @Date: 2018/12/28 14:27
 * @Description:
 */
public class JoinThread {

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 30; i++) {
                System.out.println(Thread.currentThread().getName() + "-->" + i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        thread.setPriority(10);
        System.out.println(thread.getState());
        System.out.println(Thread.activeCount());
        for (int i = 0; i < 30; i++) {
            System.out.println("main" + "-->" + i);
            try {
                Thread.sleep(100);
                if (i == 10){
                    thread.join(1000);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
