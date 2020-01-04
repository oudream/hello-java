package JavaReflection.OperationalAnnotation;

import JavaAnnotation.SxtField;
import JavaAnnotation.SxtStudent;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * 说明：反射操作注解
 *
 * @Auther: 11432_000
 * @Date: 2019/1/19 14:19
 * @Description:
 */
public class Test {

    public static void main(String[] args)throws Exception {
        Class<?> clazz = SxtStudent.class;
        Annotation[] annotations = clazz.getAnnotations();
        for (Annotation annotation : annotations){
            System.out.println(annotation);
        }
        //获取自己定义的属性
        clazz.getDeclaredFields();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields){
            Annotation[] annotation = field.getAnnotations();
            System.out.println(annotation);
        }
        //根据注解信息生成DDL语句，使用JDBC执行语句。
    }
}
