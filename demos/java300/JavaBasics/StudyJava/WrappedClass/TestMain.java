package JavaBasics.StudyJava.WrappedClass;

/**
 * 说明：
 *
 * @Auther: 11432_000
 * @Date: 2018/12/11 13:57
 * @Description:
 */
public class TestMain {

    public static void main(String[] args) {
        Integer integer = new Integer(100);
        Integer integer1 = Integer.valueOf("99");
        int i = integer.intValue();
        //将字符串转换为int，第二个参数代表字符串的进制。
        int i1 = Integer.parseInt("0100011" ,2);
        //十六进制
        int i2 = 0xabcd;
        //八进制
        int i3 = 0334;
        //二进制（不推荐）
        int i4 = 0b011110001;
        System.out.println(i2);
    }
}
