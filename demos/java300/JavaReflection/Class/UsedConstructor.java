package JavaReflection.Class;

import JavaAnnotation.SxtStudent;

import java.lang.reflect.Constructor;

/**
 * 说明：反射调用构造器
 *
 * @Auther: 11432_000
 * @Date: 2019/1/18 16:51
 * @Description:
 */
public class UsedConstructor {
    public static void main(String[] args) throws Exception{
        Class<SxtStudent> clazz = SxtStudent.class;
        //直接通过Class对象创建对象（调用无参构造器）
        SxtStudent sxtStudent = clazz.newInstance();
        //获取相应参数的构造器
        Constructor<SxtStudent> constructor = clazz.getDeclaredConstructor(int.class, String.class, int.class);
        //通过构造器创建对象，需要实际参数。
        SxtStudent student = constructor.newInstance(0001, "麻尧", 21);
    }
}
