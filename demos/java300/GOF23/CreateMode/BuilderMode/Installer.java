package GOF23.CreateMode.BuilderMode;

/**
 * 说明：
 *
 * @Auther: 11432_000
 * @Date: 2019/1/24 15:09
 * @Description:
 */
public class Installer implements Install{

    private Build build;

    public Installer(Build build) {
        this.build = build;
    }

    @Override
    public Product getProduct() {
        Product product = new Product();
        product.setA(build.buildA());
        product.setB(build.buildB());
        product.setC(build.buildC());
        return product;
    }
}
