package JavaReflection.JavaSsist;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 说明：
 *
 * @Auther: 11432_000
 * @Date: 2019/1/21 16:02
 * @Description:
 */
@Target(value = {ElementType.TYPE ,ElementType.METHOD })
@Retention(value = RetentionPolicy.RUNTIME)
public @interface Author {
    String name()default "";
    int year()default 1870;
}
