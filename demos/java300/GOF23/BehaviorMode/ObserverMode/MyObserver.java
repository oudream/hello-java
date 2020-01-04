package GOF23.BehaviorMode.ObserverMode;

import java.util.Observable;
import java.util.Observer;

/**
 * 说明：
 *
 * @Auther: 11432_000
 * @Date: 2019/1/30 14:17
 * @Description:
 */
public class MyObserver implements Observer {

    private int myState = -99;

    @Override
    public void update(Observable observable, Object o) {
        this.myState = ((Broadcast) observable).getState();
    }

    public int getMyState() {
        return myState;
    }
}
