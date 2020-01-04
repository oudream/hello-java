package ManyThreads;

/**
 * 说明：单例模式 -- DCL双检锁
 *
 * @Auther: 11432_000
 * @Date: 2019/1/10 13:57
 * @Description:
 */
public class SingletonModel {

    private static volatile SingletonModel singletonModel;

    private SingletonModel(){}

    public static SingletonModel getInstance(){
        if (singletonModel == null){
            synchronized (SingletonModel.class){
                if (singletonModel == null){
                    singletonModel = new SingletonModel();
                }
            }
        }
        return singletonModel;
    }

}
