package GOF23.BehaviorMode.ObserverMode;

/**
 * 说明：观察者模式
 *
 * @Auther: 11432_000
 * @Date: 2019/1/30 14:02
 * @Description:
 */
public class Test {
    public static void main(String[] args) {
        Broadcast publisherMan = new Broadcast();

        MyObserver observerMan = new MyObserver();
        MyObserver observerMan2 = new MyObserver();
        MyObserver observerMan3 = new MyObserver();
        MyObserver observerMan4 = new MyObserver();
        System.out.println(observerMan.getMyState());
        System.out.println(observerMan2.getMyState());
        System.out.println(observerMan3.getMyState());System.out.println(observerMan4.getMyState());

        //将观察者添加到广播中
        publisherMan.addObserver(observerMan);
        publisherMan.addObserver(observerMan2);
        publisherMan.addObserver(observerMan3);
        publisherMan.addObserver(observerMan4);


        publisherMan.setState(404);
        System.out.println(observerMan.getMyState());
        System.out.println(observerMan2.getMyState());
        System.out.println(observerMan3.getMyState());System.out.println(observerMan4.getMyState());

    }
}
