package GOF23.BehaviorMode.StrategyMode;

/**
 * 说明：
 *
 * @Auther: 11432_000
 * @Date: 2019/1/29 14:06
 * @Description:
 */
public class Context {

    private Strategy strategy;

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    public Context() {
    }

    public double getPrice(int p){
        return strategy.getPrice(p);
    }
}
