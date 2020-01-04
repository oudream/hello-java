package GOF23.StructureMode.ProxyMode.Static;

import GOF23.StructureMode.ProxyMode.CommonInter;

/**
 * 说明：
 *
 * @Auther: 11432_000
 * @Date: 2019/1/25 14:22
 * @Description:
 */
public class Real implements CommonInter {
    @Override
    public void buy() {
        System.out.println("我买了");
    }

    @Override
    public void send() {
        System.out.println("我送人了");
    }

    @Override
    public void look() {
        System.out.println("我挑选商品");
    }

    @Override
    public void sell() {
        System.out.println("我卖了");
    }
}
