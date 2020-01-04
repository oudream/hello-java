package JavaReflection.Class;

import JavaAnnotation.SxtStudent;

import java.lang.reflect.Field;

/**
 * 说明：反射调用属性
 *
 * @Auther: 11432_000
 * @Date: 2019/1/18 17:11
 * @Description:
 */
public class UsedField {

    public static void main(String[] args) throws Exception{
        SxtStudent sxtStudent = new SxtStudent();
        Class<? extends SxtStudent> clazz = sxtStudent.getClass();
        Field name = clazz.getDeclaredField("name");
        //设置访问权限，解除安全检查。
        name.setAccessible(true);
        //get/set方法，均需传入想要操作的对象
        System.out.println(name.get(sxtStudent));
        name.set(sxtStudent,"hhhhh");
        System.out.println(name.get(sxtStudent));
    }
}
