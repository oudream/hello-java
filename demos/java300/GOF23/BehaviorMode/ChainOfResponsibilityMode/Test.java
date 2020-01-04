package GOF23.BehaviorMode.ChainOfResponsibilityMode;

/**
 * 说明：责任链模式
 *
 * @Auther: 11432_000
 * @Date: 2019/1/28 13:44
 * @Description:
 */
public class Test {

    public static void main(String[] args) {
        LeaderLv0 leaderLv0 = new LeaderLv0(new LeaderLv1(new LeaderLv3(null)));
        Request mayao = new Request(2, "mayao");
        Request mayao1 = new Request(5, "mayao");
        Request mayao2 = new Request(100, "mayao");
        leaderLv0.approval(mayao);
        leaderLv0.approval(mayao1);
//        leaderLv0.approval(mayao2);
    }
}
