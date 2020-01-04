package GOF23.BehaviorMode.StateMode;

/**
 * 说明：
 *
 * @Auther: 11432_000
 * @Date: 2019/1/29 15:09
 * @Description:
 */
public class Test {
    public static void main(String[] args) {
        Context context = new Context();
        State state01 = new State01();
        State state02 = new State02();
        State state03 = new State03();
        context.setState(state01);
        context.setState(state03);
    }
}
