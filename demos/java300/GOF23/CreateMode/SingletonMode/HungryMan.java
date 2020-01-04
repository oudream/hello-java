package GOF23.CreateMode.SingletonMode;

/**
 * 说明：饿汉式
 *
 * @Auther: 11432_000
 * @Date: 2019/1/23 14:53
 * @Description:
 */
public class HungryMan {

    private static HungryMan instance = new HungryMan();

    private HungryMan() { }

    public static HungryMan getInstance(){
        return instance;
    }
}
