package GOF23.CreateMode.FactoryMode.AbstractFactoryMode;

/**
 * 说明：
 *
 * @Auther: 11432_000
 * @Date: 2019/1/24 14:34
 * @Description:
 */
public class CommonFactory implements Factory{
    @Override
    public A getA() {
        return new GoodA();
    }

    @Override
    public B getB() {
        return new PoorB();
    }

    @Override
    public C getC() {
        return new PoorC();
    }
}
