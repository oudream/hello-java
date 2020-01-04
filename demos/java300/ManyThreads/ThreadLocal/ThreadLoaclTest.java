package ManyThreads.ThreadLocal;

/**
 * 说明：ThreadLocal测试
 *
 * @Auther: 11432_000
 * @Date: 2019/1/10 14:22
 * @Description:
 */
public class ThreadLoaclTest {

    private static int i;

    /** 设置初始值的两种方式 */
    private static ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> { return 1 + 99;});
    private static ThreadLocal<Integer> threadLocal_2 = new ThreadLocal(){
        @Override
        protected Object initialValue() {
            return 99;
        }
    };
    private static ThreadLocal<Integer> threadLocal_3 = new InheritableThreadLocal();

    public static void main(String[] args) throws Exception{
//        System.out.println(Thread.currentThread().getName() + "-->" + threadLocal.get() + "-->" +threadLocal_2.get());
//        for (i = 0; i < 10; i++) {
//            new Thread(() -> {
//                //设置该线程的存储在ThreadLocal值
//                threadLocal.set(i + 100);
//                //获取该线程的存储在ThreadLocal值
//                System.out.println(Thread.currentThread().getName() + "-->" + threadLocal.get() + "-->" +threadLocal_2.get());
//            }).start();
//            Thread.sleep(100);
//        }

        threadLocal_3.set(1024);
        new Thread(() ->{
            System.out.println(threadLocal_3.get());
        }).start();
    }
}
