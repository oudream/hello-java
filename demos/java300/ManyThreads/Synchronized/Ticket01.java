package ManyThreads.Synchronized;

/**
 * 说明：线程不安全
 *
 * @Auther: 11432_000
 * @Date: 2019/1/7 15:10
 * @Description:
 */
public class Ticket01 implements Runnable{
    private int number = 10;
    private boolean flag = true;

    @Override
    public void run() {
        test01();
    }

    private void test01(){
        while (flag){
            if (number <= 0){
                flag = false;
            }
            System.out.println(Thread.currentThread().getName() + "-->" + number);
            number --;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
