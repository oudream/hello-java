package GOF23.StructureMode.AdapterMode;

/**
 * 说明：适配器
 *
 * @Auther: 11432_000
 * @Date: 2019/1/25 13:48
 * @Description:
 */
public class Adapter implements TargetInterface{

    private Adaptee adaptee;

    public Adapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void say() {
        this.adaptee.say();
        System.out.println("转换为两口~");
    }
}
