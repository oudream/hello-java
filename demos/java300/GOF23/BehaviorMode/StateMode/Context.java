package GOF23.BehaviorMode.StateMode;

/**
 * 说明：上下文
 *
 * @Auther: 11432_000
 * @Date: 2019/1/29 15:08
 * @Description:
 */
public class Context {
    private State state;


    public void setState(State state) {
        System.out.println("状态切换");
        this.state = state;
        state.handle();
    }
}
