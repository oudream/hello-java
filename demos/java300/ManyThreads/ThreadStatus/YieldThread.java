package ManyThreads.ThreadStatus;

/**
 * 说明：yield方法的测试。
 *
 * @Auther: 11432_000
 * @Date: 2018/12/28 14:06
 * @Description:
 */
public class YieldThread {

    public static void main(String[] args) {
        new Thread(new TestThread() ,"001").start();
        new Thread(new TestThread() ,"002").start();
    }
}
