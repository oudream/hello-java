package JavaBasics.StudyJava.oop;

/**
 * 说明：
 *
 * @Auther: 11432_000
 * @Date: 2018/12/6 09:42
 * @Description:
 */
public class TestMain {

    public static void main(String[] args){
        class Cc{
            public int X = 5418;
        }
        AbstractClass abstractClass = new SubClass();
        abstractClass.say();
        abstractClass.show();
        InnerClass.Inner inner = new InnerClass().new Inner();
        InnerClass.Inner2 inner2 = new InnerClass.Inner2();
        System.out.println(inner.anInt);
        System.out.println(inner2.anInt + " " + InnerClass.Inner2.staticAnInt);
        TestMain.show(new MyInterface() {
            @Override
            public void hh() {
                System.out.println("我是匿名内部类");
            }
        });
        Cc cc = new Cc();
        System.out.println(cc.X);
    }

    public static void show(MyInterface myInterface){
        myInterface.hh();
    }
}
