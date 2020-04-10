package hello.java.lang.annotation.java300;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 说明：
 *
 * @Auther: 11432_000
 * @Date: 2019/1/18 14:13
 * @Description:
 */
@Target(value = {ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {

    String name() default "";
    int number()default 0;
    String[] strs()default {};
}
