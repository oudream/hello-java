package JavaIO.ZhuangSHiQi;

import java.util.HashMap;

/**
 * 说明：装饰器模式的四个组件
 *
 * @Auther: 11432_000
 * @Date: 2018/12/21 09:53
 * @Description:
 */
public class TestMain {
    public static void main(String[] args) {

    }
}
/** 抽象组件 */
interface Human{
    void run();
    void fly();
}
/** 具体组件 */
class man implements Human{

    private int speed = 30;

    @Override
    public void run() {
        System.out.println("人跑步时速为：" + this.speed + "km/h");
    }

    @Override
    public void fly() {
        System.out.println("人不会飞");
    }
}
/** 抽象装饰类 */
abstract class SuperMan implements Human {
    private Human human;

    public SuperMan(Human human) {
        this.human = human;
    }

    public Human getHuman() {
        return human;
    }

    public void setHuman(Human human) {
        this.human = human;
    }
}
/** 装饰类 */
class SpiderMan extends SuperMan{
    public SpiderMan(Human human) {
        super(human);
    }

    @Override
    public void run() {
        this.getHuman().run();
        System.out.println("超人时速为:" + 100 + "km/h");
    }

    @Override
    public void fly() {
        System.out.println("超人可以飞");
    }
}
