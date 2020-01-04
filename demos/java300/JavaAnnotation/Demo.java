package JavaAnnotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * 说明：解析注解
 *
 * @Auther: 11432_000
 * @Date: 2019/1/18 14:56
 * @Description:
 */
public class Demo {

    public static void main(String[] args) {
        try {
            Class<?> clazz = Class.forName("JavaAnnotation.SxtStudent");
            Annotation[] annotations = clazz.getAnnotations();
            for (Annotation annotation : annotations){
                System.out.println(annotation);
            }
            //获取自己定义的属性
            clazz.getDeclaredFields();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields){
                SxtField annotation = field.getAnnotation(SxtField.class);
                System.out.println(annotation);
            }
            //根据注解信息生成DDL语句，使用JDBC执行语句。
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
