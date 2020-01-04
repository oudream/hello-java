package GOF23.StructureMode.DecoratorMode;

/**
 * 说明：装饰模式
 *
 * @Auther: 11432_000
 * @Date: 2019/1/26 15:43
 * @Description:
 */
public class Test {

    public static void main(String[] args) {
        new WaterCar(new FlyCar(new Car())).move();
    }
}
