package GOF23.BehaviorMode.ObserverMode;

import java.util.Random;

/**
 * 说明：观察者
 *
 * @Auther: 11432_000
 * @Date: 2019/1/30 13:36
 * @Description:
 */
public interface Observer {
    void update(Publisher publisher);
}
class ObserverMan implements Observer{

    public int myState;

    public ObserverMan() {
        Random random = new Random();
        this.myState = random.nextInt(1000);
    }

    @Override
    public void update(Publisher publisher) {
        this.myState = ((PublisherMan) publisher).getState();
    }

    public int getMyState() {
        return myState;
    }
}
