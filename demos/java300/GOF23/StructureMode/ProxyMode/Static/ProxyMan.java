package GOF23.StructureMode.ProxyMode.Static;

import GOF23.StructureMode.ProxyMode.CommonInter;

/**
 * 说明：
 *
 * @Auther: 11432_000
 * @Date: 2019/1/25 14:24
 * @Description:
 */
public class ProxyMan implements CommonInter {

    private Real real;

    public ProxyMan(Real real) {
        this.real = real;
    }

    @Override
    public void buy() {
        real.buy();
    }

    @Override
    public void send() {
        real.send();
        System.out.println("代理帮你送了过去");
    }

    @Override
    public void look() {
        System.out.println("代理推荐商品给你");
    }

    @Override
    public void sell() {
        System.out.println("代理帮你卖了");
    }
}
