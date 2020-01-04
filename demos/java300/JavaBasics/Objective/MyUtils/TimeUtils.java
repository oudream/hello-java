package JavaBasics.Objective.MyUtils;

import java.lang.reflect.Method;

/**
 * 说明：关于时间的方法
 *
 * @Auther: 11432_000
 * @Date: 2018/11/28 11:04
 * @Description:
 */
public class TimeUtils<T> {

    public static void showExecutiveTime(Object instance, String methodName,Object[] args){
        /**
         *
         * 功能描述: 计算执行时间,是代理但不是代理模式。
         *
         * @param: [instance, methodName, args]
         * @return: void
         * @auther: 11432_000
         * @date: 2018/11/28 13:27
         */
        Class[] classes = null;
        if (args != null){
            classes = new Class[args.length];
            for (int i = 0;i< args.length;i++){
                classes[i] = args[i].getClass();
            }
        }
        try {
            Method method = instance.getClass().getMethod(methodName, classes);
            long befor = System.currentTimeMillis();
            Object invoke = method.invoke(instance, args);
            long after = System.currentTimeMillis();
            System.out.println("耗时：" + (after - befor) + "ms");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
