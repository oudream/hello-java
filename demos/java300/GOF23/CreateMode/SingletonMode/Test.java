package GOF23.CreateMode.SingletonMode;

import java.io.*;
import java.lang.reflect.Constructor;
import java.util.concurrent.CountDownLatch;

/**
 * 说明：测试单例模式 + 反射反序列化破解单例
 *
 * @Auther: 11432_000
 * @Date: 2019/1/23 15:40
 * @Description:
 */
public class Test {

    public static void main(String[] args)throws Exception {
        test04();
    }


    public static void tesy01(){
        StaticInnerClass in01 = StaticInnerClass.getInstance();
        StaticInnerClass in02 = StaticInnerClass.getInstance();

        System.out.println(in01.hashCode());
        System.out.println(in02.hashCode());
    }


    /** 通过反射跳过单例 */
    public static void test02()throws Exception{
        Class<?> clazz = Class.forName("GOF23.CreateMode.SingletonMode.LazyMan");
        Constructor<?> constructor = clazz.getDeclaredConstructor(null);
        constructor.setAccessible(true);
        LazyMan in01 = (LazyMan) constructor.newInstance();
        LazyMan in02 = (LazyMan) constructor.newInstance();

        System.out.println(in01.hashCode());
        System.out.println(in02.hashCode());
    }

    /** 通过反序列化跳过单例 */
    public static void test03()throws Exception{
        LazyMan lazyMan = LazyMan.getInstance();
        ObjectOutputStream outputStream = new ObjectOutputStream(new BufferedOutputStream(
                new FileOutputStream("D:\\idea-file\\javaDemo02\\src\\GOF23\\SingletonMode\\Files\\a.txt")));
        outputStream.writeObject(lazyMan);
        outputStream.flush();
        outputStream.close();

        ObjectInputStream inputStream = new ObjectInputStream(new BufferedInputStream(
                new FileInputStream("D:\\idea-file\\javaDemo02\\src\\GOF23\\SingletonMode\\Files\\a.txt")));
        LazyMan lazyMan01 = (LazyMan) inputStream.readObject();
        System.out.println(lazyMan.hashCode());
        System.out.println(lazyMan01.hashCode());
        inputStream.close();
    }

    public static void test04()throws Exception{
        final CountDownLatch countDownLatch = new CountDownLatch(10);
        long start = System.currentTimeMillis();
        for (int j = 0; j < 10; j++) {
            new Thread(() ->{
                for (int i = 0; i < 100000; i++) {
                    Object instance = StaticInnerClass.getInstance();
                }
                countDownLatch.countDown();
            }).start();
        }
        countDownLatch.await();
        long end = System.currentTimeMillis();
        System.out.println("执行时间：" + (end - start) + "ms");
    }
}
