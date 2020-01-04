package GOF23.StructureMode.ProxyMode.Static;

/**
 * 说明：
 *
 * @Auther: 11432_000
 * @Date: 2019/1/25 14:26
 * @Description:
 */
public class Test {

    public static void main(String[] args) {
        ProxyMan proxyMan = new ProxyMan(new Real());
        proxyMan.look();
        proxyMan.buy();
        proxyMan.sell();
    }
}
