package GOF23.BehaviorMode.MediatorMode;

/**
 * 说明：
 *
 * @Auther: 11432_000
 * @Date: 2019/1/28 15:07
 * @Description:
 */
public interface Child {
    void request();
    void doMyself();
}

class Child01 implements Child{

    private Mediator mediator;

    public Child01(Mediator mediator) {
        this.mediator = mediator;
        mediator.register("研发部" ,this);
    }

    @Override
    public void request() {
        System.out.println("资金不足！！！！！！！");
        this.mediator.approval("财务部");
    }

    @Override
    public void doMyself() {
        System.out.println("科研部，专心科研");
    }
}

class Child02 implements Child{

    private Mediator mediator;

    public Child02(Mediator mediator) {
        this.mediator = mediator;
        mediator.register("财务部" ,this);
    }

    @Override
    public void request() {
        System.out.println("没钱了！，研发部块干活");
        this.mediator.approval("研发部");
    }

    @Override
    public void doMyself() {
        System.out.println("财务部，分配资金");
    }
}
