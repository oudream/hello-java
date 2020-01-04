package GOF23.BehaviorMode.ChainOfResponsibilityMode;

/**
 * 说明：
 *
 * @Auther: 11432_000
 * @Date: 2019/1/28 13:48
 * @Description:
 */
public class Request {

    private int time;
    private String name;

    public Request(int time, String name) {
        this.time = time;
        this.name = name;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
