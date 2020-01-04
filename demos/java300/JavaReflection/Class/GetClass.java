package JavaReflection.Class;

/**
 * 说明：测试Class的获取
 *
 * @Auther: 11432_000
 * @Date: 2019/1/18 16:11
 * @Description:
 */
public class GetClass {

    @SuppressWarnings(value = "unused")
    public static void main(String[] args) {
        try {
            //1
            Class<?> clazz = Class.forName("JavaAnnotation.SxtStudent");
            //2
            Class<String> stringClass = String.class;
            //3
            String str = new String();
            Class<? extends String> aClass = str.getClass();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
