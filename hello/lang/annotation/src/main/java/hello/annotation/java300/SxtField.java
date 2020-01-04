package hello.annotation.java300;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 说明：属性注解
 *
 * @Auther: 11432_000
 * @Date: 2019/1/18 14:53
 * @Description:
 */
@Target(value = {ElementType.FIELD})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface SxtField {
    String columName();
    String type();
    int length();
}
