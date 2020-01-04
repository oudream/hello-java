package ManyThreads.Lambda;

/**
 * 说明：
 *
 * @Auther: 11432_000
 * @Date: 2018/12/26 10:57
 * @Description:
 */
public class Lambda {
    public static void main(String[] args) {
        MyInterface myInterface = () -> System.out.println("hhhh");
        MyInterface2 myInterface2 = a -> {
            String substring = a.substring(0, 2);
            System.out.println(substring);
        };
        myInterface.show();
        myInterface2.show("ddddd");
        System.out.println();
    }
}
interface MyInterface{
    void show();
}
interface MyInterface2{
    void show(String msg);
}
