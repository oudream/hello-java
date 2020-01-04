package ManyThreads.ReentrantLock;

/**
 * 说明：自定义可重入锁
 *
 * @Auther: 11432_000
 * @Date: 2019/1/10 15:27
 * @Description:
 */
public class MyLock {

    private boolean isLock = false;
    Thread lockBy = null;
    private int number = 0;

    public synchronized void lock()throws InterruptedException{
        while (isLock && lockBy != Thread.currentThread()){
            this.wait();
        }
        isLock = true;
        lockBy = Thread.currentThread();
        number ++;
    }

    public synchronized void unlock(){
        if (Thread.currentThread() == lockBy){
            number --;
            if (number == 0){
                isLock = false;
                lockBy = null;
                notifyAll();
            }
        }
    }
}
