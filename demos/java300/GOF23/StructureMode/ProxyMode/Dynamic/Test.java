package GOF23.StructureMode.ProxyMode.Dynamic;

import GOF23.StructureMode.ProxyMode.CommonInter;
import GOF23.StructureMode.ProxyMode.Static.Real;

import java.lang.reflect.Proxy;

/**
 * 说明：
 *
 * @Auther: 11432_000
 * @Date: 2019/1/25 14:43
 * @Description:
 */
public class Test {
    public static void main(String[] args) {
        Real real = new Real();
        Invocation invocation = new Invocation(real);

        CommonInter commonInter =(CommonInter)
                //获取一个代理对象，参数：ClassLoader，抽象角色，处理器
                Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[]{CommonInter.class}, invocation);
        commonInter.sell();
    }
}
