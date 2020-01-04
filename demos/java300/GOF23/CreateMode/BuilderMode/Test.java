package GOF23.CreateMode.BuilderMode;

/**
 * 说明：
 *
 * @Auther: 11432_000
 * @Date: 2019/1/24 15:13
 * @Description:
 */
public class Test {

    public static void main(String[] args) {
        Install install = new Installer(new Builder());
        Product product = install.getProduct();
        System.out.println(product);
    }
}
