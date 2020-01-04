package JavaReflection.Class;

import JavaAnnotation.SxtStudent;

import java.lang.reflect.Method;

/**
 * 说明：反射调用方法
 *
 * @Auther: 11432_000
 * @Date: 2019/1/18 17:01
 * @Description:
 */
public class UsedMethod {

    public static void main(String[] args)throws Exception {
        SxtStudent sxtStudent = new SxtStudent();
        Class<? extends SxtStudent> clazz = sxtStudent.getClass();
        Method setName = clazz.getDeclaredMethod("setName", String.class);
        //执行反射得到的方法，参数为一个该类的对象以及实际参数。
        setName.invoke(sxtStudent,"kkk");
        System.out.println(sxtStudent.getName());
    }
}
