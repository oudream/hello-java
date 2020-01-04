package JavaReflection.FanXing;

import JavaAnnotation.SxtStudent;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * 说明：反射获取泛型参数及其泛型类型
 *
 * @Auther: 11432_000
 * @Date: 2019/1/19 13:53
 * @Description:
 */
public class Test {

    public static void main(String[] args) throws Exception{
        Class<SxtStudent> clazz = SxtStudent.class;
        Method method = clazz.getDeclaredMethod("show", Map.class);
        //获取带有泛型的参数
        Type[] genericParameterTypes = method.getGenericParameterTypes();
        for (Type type : genericParameterTypes){
            if (type instanceof ParameterizedType){
                //获实际泛型类型
                Type[] actualTypeArguments = ((ParameterizedType) type).getActualTypeArguments();
                for (Type type1 : actualTypeArguments){ }
            }
        }
        //获取带有泛型的返回值
        Type genericReturnType = method.getGenericReturnType();
        if (genericReturnType instanceof ParameterizedType){
            //获实际泛型类型
            Type[] actualTypeArguments = ((ParameterizedType) genericReturnType).getActualTypeArguments();
        }
    }
}
