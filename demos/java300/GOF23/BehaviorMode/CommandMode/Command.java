package GOF23.BehaviorMode.CommandMode;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 说明：
 *
 * @Auther: 11432_000
 * @Date: 2019/1/28 15:49
 * @Description:
 */
public interface Command {
    void execut();
}

class MyCommand implements Command{

    private List<Receiver> list = new ArrayList<>();

    @Override
    public void execut() {
        for (Receiver receiver : list){
            receiver.run();
        }
    }

    public void add(Receiver receiver){
        list.add(receiver);
    }
}
