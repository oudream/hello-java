package GOF23.BehaviorMode.StrategyMode;

/**
 * 说明：策略模式
 *
 * @Auther: 11432_000
 * @Date: 2019/1/29 13:54
 * @Description:
 */
public class Test {

    public static void main(String[] args) {
        Context context = new Context();
        Strategy04 strategy04 = new Strategy04();
        context.setStrategy(strategy04);
        System.out.println(context.getPrice(100));
    }
}
