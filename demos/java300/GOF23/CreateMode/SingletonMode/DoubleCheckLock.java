package GOF23.CreateMode.SingletonMode;

/**
 * 说明：双检锁模式
 *
 * @Auther: 11432_000
 * @Date: 2019/1/23 15:15
 * @Description:
 */
public class DoubleCheckLock {

    private static DoubleCheckLock instance = null;

    private DoubleCheckLock(){}

    public static DoubleCheckLock getInstance(){
        if (instance == null){
            synchronized(DoubleCheckLock.class){
                if (instance == null){
                    instance = new DoubleCheckLock();
                }
            }
        }
        return instance;
    }
}
