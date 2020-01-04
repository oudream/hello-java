package ManyThreads.Callable;

import java.util.concurrent.*;

/**
 * 说明：
 *
 * @Auther: 11432_000
 * @Date: 2018/12/25 16:16
 * @Description:
 */
public class TestMain {

    public static void main(String[] args)throws Exception {
        //创建线程
        MyCallable myCallable = new MyCallable();
        //创建线程池
        ExecutorService executorService = new ThreadPoolExecutor(4 ,10 ,60 , TimeUnit.SECONDS ,new ArrayBlockingQueue<Runnable>(10));
        //执行线程
        Future<String> submit = executorService.submit(myCallable);
        //获取执行返回的结果
        String s = submit.get();
        System.out.println(s);
    }
}
class MyCallable implements Callable<String>{
    @Override
    public String call() throws Exception {
        return "mayao";
    }
}
