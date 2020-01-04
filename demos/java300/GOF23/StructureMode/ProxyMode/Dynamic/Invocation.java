package GOF23.StructureMode.ProxyMode.Dynamic;

import GOF23.StructureMode.ProxyMode.CommonInter;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 说明：执行代理方法的处理器
 *
 * @Auther: 11432_000
 * @Date: 2019/1/25 14:40
 * @Description:
 */
public class Invocation implements InvocationHandler {

    private CommonInter comparable;

    public Invocation(CommonInter comparable) {
        this.comparable = comparable;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        method.invoke(comparable,objects);
        if(o instanceof Proxy){
            System.out.println("true");
        }
        return null;
    }
}
