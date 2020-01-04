package ManyThreads.Runnable;

/**
 * 说明：
 *
 * @Auther: 11432_000
 * @Date: 2018/12/25 15:49
 * @Description:
 */
public class SecondThread {
    public static void main(String[] args) {
        new Thread(new MySecondThread(),"001").start();
        new Thread(new MySecondThread(),"002").start();
        new Thread(new MySecondThread(),"003").start();
    }
}
class MySecondThread implements Runnable{
    private static int number = 99;
    @Override
    public void run() {
        while (true){
            if (number <= 0){
                return;
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "抢到了-->" + number);
            number --;
        }
    }
}
