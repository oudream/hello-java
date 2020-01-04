package GOF23.CreateMode.FactoryMode.AbstractFactoryMode;

/**
 * 说明：
 *
 * @Auther: 11432_000
 * @Date: 2019/1/24 14:29
 * @Description:
 */
public class GoodFactory implements Factory {
    @Override
    public A getA() {
        return new GoodA();
    }

    @Override
    public B getB() {
        return new GoodB();
    }

    @Override
    public C getC() {
        return new GoodC();
    }
}
