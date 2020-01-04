package JavaReflection.Class;

import JavaAnnotation.SxtStudent;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * 说明：Class对象的常用方法
 *
 * @Auther: 11432_000
 * @Date: 2019/1/18 16:30
 * @Description:
 */
public class UsedClass {
    @SuppressWarnings("unused")
    public static void main(String[] args) throws Exception{
        Class<SxtStudent> clazz = SxtStudent.class;
        //1、获取全路径。2、获取类名
        clazz.getName();
        clazz.getSimpleName();
        /* -------获取属性信息-------- */
        //返回public的所有属性
        clazz.getFields();
        //获取该类定义的所有属性
        clazz.getDeclaredFields();
        //获取指定名称的属性
        clazz.getField("FiledName");
        clazz.getDeclaredField("FiledName");
        /* ------获取方法------ */
        //1、获取所有方法。2、获取所有public方法
        Method[] declaredMethods = clazz.getDeclaredMethods();
        Method[] methods = clazz.getMethods();
        //获取指定名称和参数列表的方法,参数为所有参数的类类型
        clazz.getMethod("MethodName" ,String.class,int.class);
        clazz.getDeclaredMethod("MethodName" ,String.class,int.class);
        /* ------获取构造器------ */
        //1、获取public构造器。2、获取所有构造器
        Constructor<?>[] constructors = clazz.getConstructors();
        Constructor<?>[] declaredConstructors = clazz.getDeclaredConstructors();
        //获取指定参数的构造器
        clazz.getDeclaredConstructor(String.class);
    }
}
