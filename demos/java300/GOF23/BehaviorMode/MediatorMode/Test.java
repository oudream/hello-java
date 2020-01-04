package GOF23.BehaviorMode.MediatorMode;

/**
 * 说明：中介者模式
 *
 * @Auther: 11432_000
 * @Date: 2019/1/28 15:04
 * @Description:
 */
public class Test {

    public static void main(String[] args) {
        Mediator mediator = new Mediator();
        Child01 child01 = new Child01(mediator);
        Child02 child02 = new Child02(mediator);

        child01.request();
        child02.request();
    }
}
