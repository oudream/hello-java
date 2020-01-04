package GOF23.BehaviorMode.StrategyMode;

/**
 * 说明：算法统一接口
 *
 * @Auther: 11432_000
 * @Date: 2019/1/29 13:54
 * @Description:
 */
public interface Strategy {
    double getPrice(int p);
}
class Strategy01 implements Strategy{
    @Override
    public double getPrice(int p) {
        System.out.println("新客户小批量，原价");
        return p;
    }
}

class Strategy02 implements Strategy{
    @Override
    public double getPrice(int p) {
        System.out.println("新客户大批量，9折");
        return p * 0.9;
    }
}

class Strategy03 implements Strategy{
    @Override
    public double getPrice(int p) {
        System.out.println("老客户小批量，9折");
        return p * 0.9;
    }
}

class Strategy04 implements Strategy{
    @Override
    public double getPrice(int p) {
        System.out.println("老客户大批量，八折");
        return p * 0.8;
    }
}
