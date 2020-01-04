package ManyThreads.ReentrantLock;

/**
 * 说明：自定义非可重入锁
 *
 * @Auther: 11432_000
 * @Date: 2019/1/10 15:28
 * @Description:
 */
public class MyLock02 {

    private boolean isLock = false;

    public synchronized void lock()throws InterruptedException{
        while (isLock){
            this.wait();
        }
        isLock = true;
    }

    public synchronized void unlock(){
        isLock = false;
        notifyAll();
    }
}
