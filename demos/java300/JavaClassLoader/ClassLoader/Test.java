package JavaClassLoader.ClassLoader;

/**
 * 说明：测试ClassLoader类
 *
 * @Auther: 11432_000
 * @Date: 2019/1/22 16:11
 * @Description:
 */
public class Test {

    public static void main(String[] args) throws Exception{
        test02();
    }

    public static void test01(){
        //获取系统类加载器（Application Class Loader）
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        //获取父类加载器(extensions class loader)
        classLoader.getParent();
        //获取classPath路径
        System.out.println(System.getProperty("java.class.path"));
    }

    /** 测试自定义类加载器 */
    public static void test02()throws Exception{
        FileSystemClassLoader fileSystemClassLoader = new FileSystemClassLoader("D:\\TestJavaFile");
        Class<?> aClass = fileSystemClassLoader.findClass("tjut.mayao.test.Hello");
        System.out.println(aClass.getName());
    }

}
