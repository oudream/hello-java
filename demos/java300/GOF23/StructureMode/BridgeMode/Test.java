package GOF23.StructureMode.BridgeMode;

/**
 * 说明：桥接模式
 *
 * @Auther: 11432_000
 * @Date: 2019/1/25 16:01
 * @Description:
 */
public class Test {
    public static void main(String[] args) {
        new Desktop(new HuaShuo()).say();
        new Desktop(new Dell()).say();
    }
}
