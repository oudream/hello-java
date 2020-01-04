package ManyThreads.Synchronized;

/**
 * 说明：线程安全
 *
 * @Auther: 11432_000
 * @Date: 2019/1/7 15:10
 * @Description:
 */
public class Ticket02 implements Runnable{
    private int number = 10;
    private boolean flag = true;

    @Override
    public void run() {
        test01();
    }

    /** 双检锁 */
    private void test01(){
        //第一次检查：考虑没有票的情况
        if (number <= 0){
            flag = false;
            return;
        }
        while (flag){
            synchronized (this){
                //多个线程等待最后一张票
                if (number <= 0){
                    flag = false;
                    return;
                }
                System.out.println(Thread.currentThread().getName() + "-->" + number);
                number --;
            }
            sleepd();
        }
        System.out.println(number);
    }

    private void sleepd(){
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
