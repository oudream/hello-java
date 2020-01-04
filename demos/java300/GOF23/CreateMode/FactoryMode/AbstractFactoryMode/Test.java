package GOF23.CreateMode.FactoryMode.AbstractFactoryMode;

/**
 * 说明：
 *
 * @Auther: 11432_000
 * @Date: 2019/1/24 14:35
 * @Description:
 */
public class Test {

    public static void main(String[] args) {
        Factory goodFactory = new CommonFactory();
        goodFactory.getA().say();
        goodFactory.getB().say();
        goodFactory.getC().say();
    }
}
