package JavaCompilr;

import javax.tools.ToolProvider;
import java.lang.reflect.Method;

/**
 * 说明：java动态编译
 *
 * @Auther: 11432_000
 * @Date: 2019/1/19 14:34
 * @Description:
 */
public class JavaCompiler {
    public static void main(String[] args)throws Exception {
        //获取JavaCompiler对象
        javax.tools.JavaCompiler systemJavaCompiler = ToolProvider.getSystemJavaCompiler();
        //执行java文件 参数为：InputStream为JVM提供参数，OutputStream返回信息，OutputStream错误信息，文件路径数组
        //返回值：0执行成功，非0执行失败
        int run = systemJavaCompiler.run(null, null, null, "");


        Runtime runtime = Runtime.getRuntime();
        runtime.exec("java -cp 文件路径");

        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        Class<?> clazz = classLoader.loadClass("类路径");

        //调用main函数
        Method main = clazz.getMethod("main", String[].class);
        main.invoke(null,(Object)new String[]{});

    }
}
