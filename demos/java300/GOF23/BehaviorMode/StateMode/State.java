package GOF23.BehaviorMode.StateMode;

/**
 * 说明：状态类接口
 *
 * @Auther: 11432_000
 * @Date: 2019/1/29 15:04
 * @Description:
 */
public interface State {
    public void handle();
}
class State01 implements State{
    @Override
    public void handle() {
        System.out.println("运行中");
    }
}

class State02 implements State{
    @Override
    public void handle() {
        System.out.println("故障中");
    }
}

class State03 implements State{
    @Override
    public void handle() {
        System.out.println("待机中");
    }
}
