package GOF23.CreateMode.SingletonMode;

import java.io.ObjectStreamException;
import java.io.Serializable;

/**
 * 说明：懒汉式
 *
 * @Auther: 11432_000
 * @Date: 2019/1/23 15:00
 * @Description:
 */
public class LazyMan implements Serializable {
    private static LazyMan instance = null;

    private LazyMan() {
    }

    public synchronized static LazyMan getInstance(){
        if (instance == null){
            instance = new LazyMan();
        }
        return instance;
    }

    public Object readResolve()throws ObjectStreamException{
        return instance;
    }
}
