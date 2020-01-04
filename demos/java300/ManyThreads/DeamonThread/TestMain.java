package ManyThreads.DeamonThread;

/**
 * 说明：
 *
 * @Auther: 11432_000
 * @Date: 2019/1/7 14:28
 * @Description:
 */
public class TestMain {
    public static void main(String[] args) {
        Thread thread = new Thread(new MyThread001(),"001");
        thread.setDaemon(true);
        thread.start();
        new Thread(new MyThread002(),"002").start();

    }
}
class MyThread001 implements  Runnable{
    @Override
    public void run() {
        for (;true;){
            System.out.print(Thread.currentThread().getName() + "\t");
        }
    }
}
class MyThread002 implements  Runnable{
    @Override
    public void run() {
        for (int i = 0 ; i < 100 ; i++){
            System.out.print(Thread.currentThread().getName()+ "-" + i +"\t");
        }
    }
}
