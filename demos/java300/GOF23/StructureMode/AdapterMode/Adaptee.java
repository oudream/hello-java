package GOF23.StructureMode.AdapterMode;

/**
 * 说明：被适配的对象
 *
 * @Auther: 11432_000
 * @Date: 2019/1/25 13:45
 * @Description:
 */
public class Adaptee implements Common {
    @Override
    public void say() {
        System.out.println("我是三口插头");
    }
}
