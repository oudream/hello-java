package ManyThreads.CAS;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 说明：
 *
 * @Auther: 11432_000
 * @Date: 2019/1/10 16:22
 * @Description:
 */
public class TestMain {
    private static AtomicInteger stock = new AtomicInteger(5);

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                Integer left = stock.decrementAndGet();
                if (left < 1){
                    System.out.println("抢完了");
                }
                System.out.println(Thread.currentThread().getName() + "抢到一件");
            }).start();
        }
    }
}
