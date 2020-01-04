package GOF23.BehaviorMode.ChainOfResponsibilityMode;

/**
 * 说明：0级
 *
 * @Auther: 11432_000
 * @Date: 2019/1/28 13:50
 * @Description:
 */
@SuppressWarnings("all")
public class LeaderLv0 implements Approval{

    private static final int MAX_TIME = 1;
    private Approval nextLeader;

    public LeaderLv0(Approval nextLeader) {
        this.nextLeader = nextLeader;
    }

    @Override
    public void approval(Request request) {
        if (request.getTime() <= MAX_TIME){
            System.out.println("LV0管理人同意了");
        }else {
            if (nextLeader != null){
                nextLeader.approval(request);
            }else {
                throw new RuntimeException("不想干滚");
            }
        }
    }

    public Approval getNextLeader() {
        return nextLeader;
    }

    public void setNextLeader(Approval nextLeader) {
        this.nextLeader = nextLeader;
    }
}
