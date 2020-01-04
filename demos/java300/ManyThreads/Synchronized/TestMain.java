package ManyThreads.Synchronized;

import jdk.management.resource.internal.inst.ThreadRMHooks;

import java.util.ArrayList;
import java.util.List;

/**
 * 说明：
 *
 * @Auther: 11432_000
 * @Date: 2019/1/7 15:07
 * @Description:
 */
public class TestMain {
    public static void main(String[] args) {
        test01();
    }

    public static void test01(){
        Ticket02 ticket01 = new Ticket02();
        Thread thread1 = new Thread(ticket01, "aa");
        Thread thread2 = new Thread(ticket01, "bb");
        Thread thread3 = new Thread(ticket01, "cc");
        thread1.start();
        thread2.start();
        thread3.start();
    }

    public static void test02(){
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < 10000; i++) {
            new Thread(() -> {
                synchronized (list){
                    list.add(Thread.currentThread().getName());
                }
            }).start();
        }
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(list.size());
    }
}
