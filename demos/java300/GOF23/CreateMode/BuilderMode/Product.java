package GOF23.CreateMode.BuilderMode;

/**
 * 说明：产品 + 组件
 *
 * @Auther: 11432_000
 * @Date: 2019/1/24 14:54
 * @Description:
 */
public class Product {
    private A a;
    private B b;
    private C c;

    public A getA() {
        return a;
    }

    public void setA(A a) {
        this.a = a;
    }

    public B getB() {
        return b;
    }

    public void setB(B b) {
        this.b = b;
    }

    public C getC() {
        return c;
    }

    public void setC(C c) {
        this.c = c;
    }

    @Override
    public String toString() {
        return "Product{" +
                "a=" + a.getName() +
                ", b=" + b.getName() +
                ", c=" + c.getName() +
                '}';
    }
}
class A{
    private String name;

    public A(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
class B{
    private String name;

    public B(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
class C{
    private String name;

    public C(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
