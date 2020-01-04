package JavaClassLoader.ClassLoader;

/**
 * 说明：
 *
 * @Auther: 11432_000
 * @Date: 2019/1/23 14:14
 * @Description:
 */
public class ThreadClassLoader {

    public static void main(String[] args) {
        //设置当前线程的ClassLoader
        Thread.currentThread().setContextClassLoader(new FileSystemClassLoader("D:\\TestJavaFile"));
    }
}
