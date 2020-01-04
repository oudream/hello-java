package ManyThreads.Lambda;

/**
 * 说明：部分内部类+lambda实现匿名内部类。
 *
 * @Auther: 11432_000
 * @Date: 2018/12/25 17:01
 * @Description:
 */
public class TestMain  {

    /** 静态内部类 */
    static class test01 implements Runnable{
        @Override
        public void run() {

        }
    }
    public void test(){
        /** 局部内部类 */
        class test02 implements Runnable{
            @Override
            public void run() { }
        }
        /** 匿名内部类 */
        new Thread(new Runnable(){
            @Override
            public void run() {

            }
        }).start();

        /** lambda简化匿名内部类 */
        new Thread(() -> { System.out.println(""); });

    }


}