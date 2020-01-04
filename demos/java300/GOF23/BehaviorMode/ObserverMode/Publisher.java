package GOF23.BehaviorMode.ObserverMode;

import java.util.ArrayList;
import java.util.List;

/**
 * 说明：
 *
 * @Auther: 11432_000
 * @Date: 2019/1/30 13:47
 * @Description:
 */
public abstract class Publisher {

    protected List<Observer> observers = new ArrayList<>();


    public void register(Observer...reObservers){
        for (Observer observer : reObservers){
            this.observers.add(observer);
        }
    }

    public abstract void notifyAllObserver();
}
class PublisherMan extends Publisher{
    private int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
        notifyAllObserver();
    }

    @Override
    public void notifyAllObserver() {
        for (Observer observer : this.observers){
            observer.update(this);
        }
    }
}
