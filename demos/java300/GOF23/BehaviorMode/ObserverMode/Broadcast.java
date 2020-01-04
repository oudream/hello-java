package GOF23.BehaviorMode.ObserverMode;

import java.util.Observable;

/**
 * 说明：
 *
 * @Auther: 11432_000
 * @Date: 2019/1/30 14:18
 * @Description:
 */
public class Broadcast extends Observable {
    private int state;

    public void setState(int state) {
        this.state = state;
        //提示发生改变
        setChanged();
        //通知所有观察者
        notifyObservers(state);
    }

    public int getState() {
        return state;
    }
}
