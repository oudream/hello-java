package JavaBasics.Objective.MyUtils;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 说明：CGLIB代理
 *
 * @Auther: 11432_000
 * @Date: 2018/11/28 10:38
 * @Description:
 */
public class CglibProxy implements MethodInterceptor {

    private Enhancer enhancer = new Enhancer();

    public Object getProxy(Class clazz){
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        long beginTime = System.currentTimeMillis();
        Object result = methodProxy.invokeSuper(o, objects);
        long afterTime = System.currentTimeMillis();
        System.out.println("执行耗时：" + (afterTime - beginTime) + "ms");
        return result;
    }
}
