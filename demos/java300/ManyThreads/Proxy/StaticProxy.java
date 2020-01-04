package ManyThreads.Proxy;

/**
 * 说明：静态代理
 *
 * @Auther: 11432_000
 * @Date: 2018/12/25 16:49
 * @Description:
 */
public class StaticProxy {
    public static void main(String[] args) {
        Marry marry = new You();
        Proxy proxy = new Proxy(marry);
        proxy.happyMarry();
    }
}
interface Marry{
    void happyMarry();
}
class You implements Marry{
    @Override
    public void happyMarry() {
        System.out.println("我是单身狗！！！！！");
    }
}
class Proxy implements Marry{
    private Marry target;

    public Proxy(Marry target) {
        this.target = target;
    }

    @Override
    public void happyMarry() {
        System.out.println("啦啦啦德玛西亚");
        this.target.happyMarry();
        System.out.println("我。。TM。。要。。转。。生");
    }
}
