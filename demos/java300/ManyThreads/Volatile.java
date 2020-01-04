package ManyThreads;

/**
 * 说明：volatile关键字
 *
 * @Auther: 11432_000
 * @Date: 2019/1/10 13:51
 * @Description:
 */
public class Volatile {
    //死循环
    //private static boolean num = true;

    private volatile static boolean num = true;

    public static void main(String[] args)throws Exception {
        new Thread(() -> {
            while (num){}
        }).start();
        Thread.sleep(1000);
        num = false;
    }
}
