package GOF23.StructureMode.AdapterMode;

/**
 * 说明：适配器模式
 *
 * @Auther: 11432_000
 * @Date: 2019/1/25 13:52
 * @Description:
 */
public class Test {

    public static void main(String[] args) {
        TargetInterface target = new Adapter(new Adaptee());
        target.say();
    }
}
