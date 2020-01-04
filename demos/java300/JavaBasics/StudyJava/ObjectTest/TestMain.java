package JavaBasics.StudyJava.ObjectTest;

/**
 * 说明：
 *
 * @Auther: 11432_000
 * @Date: 2018/11/29 13:39
 * @Description:
 */
public class TestMain {

    public static void main(String[] args){
        OverrideObject overrideObject = new OverrideObject("mayao" ,20150001 , "男");
        Father overrideObject2 = new OverrideObject("lizhilei" ,20150001 , "男");
        System.out.println(overrideObject.toString());
        System.out.println(overrideObject.equals(overrideObject2));
        System.out.println(overrideObject.value + " fff  " + overrideObject.getFatherValue());
        System.out.println(overrideObject.getClass().getName());
    }
}
