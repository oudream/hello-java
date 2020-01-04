package JavaAnnotation;

/**
 * 说明：
 *
 * @Auther: 11432_000
 * @Date: 2019/1/18 13:39
 * @Description:
 */
public class Test01 {

    @SuppressWarnings("all")
    public static void main(String[] args) {
        test01();
    }


    @Override
    public String toString() {
        return super.toString();
    }

    @Deprecated
    @SuppressWarnings("all")
    public static void test01(){
        int a;
    }
}
