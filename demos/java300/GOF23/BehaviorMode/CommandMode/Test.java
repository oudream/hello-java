package GOF23.BehaviorMode.CommandMode;

/**
 * 说明：命令模式
 *
 * @Auther: 11432_000
 * @Date: 2019/1/28 16:01
 * @Description:
 */
public class Test {

    public static void main(String[] args) {
        MyCommand command = new MyCommand();
        command.add(new Receiver());
        command.add(new Receiver());
        F f= new F(command);
        f.runAll();
    }
}
