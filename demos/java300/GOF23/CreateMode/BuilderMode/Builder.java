package GOF23.CreateMode.BuilderMode;

/**
 * 说明：
 *
 * @Auther: 11432_000
 * @Date: 2019/1/24 15:03
 * @Description:
 */
public class Builder implements Build {
    @Override
    public A buildA() {
        return new A("A001");
    }

    @Override
    public B buildB() {
        return new B("B001");
    }

    @Override
    public C buildC() {
        return new C("C001");
    }
}
