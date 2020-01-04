package GOF23.BehaviorMode.CommandMode;

/**
 * 说明：
 *
 * @Auther: 11432_000
 * @Date: 2019/1/28 15:59
 * @Description:
 */
public class F {
    private Command command;

    public F(Command command) {
        this.command = command;
    }

    public void runAll(){
        command.execut();
    }
}
