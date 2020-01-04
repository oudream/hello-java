package GOF23.CreateMode.FactoryMode.EasyFactoryMode;

import GOF23.CreateMode.FactoryMode.Product;

/**
 * 说明：简单工厂模式
 *
 * @Auther: 11432_000
 * @Date: 2019/1/24 13:43
 * @Description:
 */
@SuppressWarnings("all")
public class EasyFactory{
    public Product get(String type){
        if ("奥迪".equals(type)){
            return new Audi();
        }else if ("宝马".equals(type)){
            return new BaoMa();
        }else {
            return null;
        }
    }
}
