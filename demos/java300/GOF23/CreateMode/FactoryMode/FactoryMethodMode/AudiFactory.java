package GOF23.CreateMode.FactoryMode.FactoryMethodMode;

import GOF23.CreateMode.FactoryMode.Product;

/**
 * 说明：
 *
 * @Auther: 11432_000
 * @Date: 2019/1/24 13:57
 * @Description:
 */
public class AudiFactory implements FactoryInterface{
    @Override
    public Product getInstance() {
        return new Audi();
    }
}
