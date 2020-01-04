package JavaBasics.StudyJava.oop;

/**
 * 说明：
 *
 * @Auther: 11432_000
 * @Date: 2018/12/6 09:28
 * @Description:
 */
public abstract class AbstractClass {
    public abstract void show();

    public void say(){
        System.out.println("say()被调用了");
    }
}

class SubClass extends AbstractClass {

    @Override
    public void show() {
        System.out.println("我是子类的show");
    }
}
