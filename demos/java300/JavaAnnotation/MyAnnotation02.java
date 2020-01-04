package JavaAnnotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 说明：
 *
 * @Auther: 11432_000
 * @Date: 2019/1/18 14:28
 * @Description:
 */
@Target(value = {ElementType.METHOD,ElementType.TYPE})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface MyAnnotation02 {
}
