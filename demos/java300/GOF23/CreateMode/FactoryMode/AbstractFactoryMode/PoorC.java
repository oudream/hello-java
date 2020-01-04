package GOF23.CreateMode.FactoryMode.AbstractFactoryMode;

/**
 * 说明：
 *
 * @Auther: 11432_000
 * @Date: 2019/1/24 14:25
 * @Description:
 */
public class PoorC implements C {
    @Override
    public void say() {
        System.out.println("产品C 品质：合格");
    }
}
