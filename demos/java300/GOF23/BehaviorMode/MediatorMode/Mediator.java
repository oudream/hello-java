package GOF23.BehaviorMode.MediatorMode;

import java.util.HashMap;
import java.util.Map;

/**
 * 说明：
 *
 * @Auther: 11432_000
 * @Date: 2019/1/28 15:10
 * @Description:
 */
public class Mediator implements  MediatorInterface {

    private Map<String,Child> map = new HashMap<>();

    @Override
    public void register(String name,Child child) {
        map.put(name ,child);
    }

    @Override
    public void approval(String name) {
        Child child = map.get(name);
        System.out.println("总经理受理请求");
        child.doMyself();
    }
}

interface MediatorInterface{
    void register(String name ,Child child);
    void approval(String name);
}
