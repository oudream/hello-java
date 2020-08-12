package hello.java.lang.annotation.java300;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class Demo {

    public static void main(String[] args) {
        try {
            Class<?> clazz = Class.forName("hello.java.lang.annotation.java300.SxtStudent");
            Annotation[] annotations = clazz.getAnnotations();
            for (Annotation annotation : annotations){
                System.out.println(annotation);
            }
            clazz.getDeclaredFields();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields){
                SxtField annotation = field.getAnnotation(SxtField.class);
                System.out.println(annotation);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
