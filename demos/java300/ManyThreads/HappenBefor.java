package ManyThreads;

/**
 * 说明：指令重排
 *
 * @Auther: 11432_000
 * @Date: 2019/1/10 13:20
 * @Description:
 */
public class HappenBefor {
    static int a = 0;
    static boolean b = false;

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            a = 0;
            b = false;
            Thread thread = new Thread(() -> {
                a = 1;
                b = true;
            });
            Thread thread1 = new Thread(() -> {
                if (b) {
                    a *= 1;
                }
                if (a == 0) {
                    System.out.println("我应该是0 -->" + a);
                }
            });
            thread.start();
            thread1.start();
        }


    }
}
