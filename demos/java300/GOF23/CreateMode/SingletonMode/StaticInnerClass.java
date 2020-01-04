package GOF23.CreateMode.SingletonMode;

/**
 * 说明：静态内部类
 *
 * @Auther: 11432_000
 * @Date: 2019/1/23 15:26
 * @Description:
 */
public class StaticInnerClass {

    private StaticInnerClass(){ }

    private static class Inner{
        private static final StaticInnerClass INSTANCE = new StaticInnerClass();
    }

    public static StaticInnerClass getInstance(){
        return Inner.INSTANCE;
    }
}
