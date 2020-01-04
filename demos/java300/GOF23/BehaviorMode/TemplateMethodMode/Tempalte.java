package GOF23.BehaviorMode.TemplateMethodMode;

/**
 * 说明：模板类
 *
 * @Auther: 11432_000
 * @Date: 2019/1/29 14:35
 * @Description:
 */
public abstract class Tempalte {

    public void first(){
        System.out.println("烧水");
    }

    public abstract void second();
    public void last(){
        System.out.println("收拾茶具");
    }

    public final void tea(){
        first();
        second();
        last();
    }
}
