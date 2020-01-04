package ManyThreads.ThreadStatus;

/**
 * 说明：线程的停止和sleep方法的使用。
 *
 * @Auther: 11432_000
 * @Date: 2018/12/28 11:12
 * @Description:
 */
public class StopThread {
    public static void main(String[] args)throws Exception {
        MyThread myThread = new MyThread();
        Thread thread = new Thread(myThread, "001");
        thread.start();
        for (int i = 0; i < 10; i++) {
            Thread.sleep(500);
        }
        myThread.setFlag(false);
        for (int i = 0; i < 10; i++) {
            System.out.print(i + "\t");
            Thread.sleep(1000);
        }
    }
}
class MyThread implements Runnable{

    private boolean flag = true;

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        int i = 0;
        while (this.flag){
            System.out.println(Thread.currentThread().getName() + "-->" + i);
            try {
                Thread.sleep(99);
                i++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
