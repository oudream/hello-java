package JavaBasics.StudyJava.Collection;

import java.util.HashMap;

/**
 * 说明：
 *
 * @Auther: 11432_000
 * @Date: 2018/12/19 09:19
 * @Description:
 */
public class MyHashSet<K> {

    private static final Object PRESENT = new Object();
    private HashMap<K,Object> map;

    public MyHashSet() {
        map = new HashMap<>();
    }

    public boolean add(K value){
        return map.put(value ,PRESENT) == null;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("[");
        for (K k : map.keySet()){
            stringBuilder.append(k.toString() + ",");
        }
        stringBuilder.replace(stringBuilder.length() - 1,stringBuilder .length() ,"]");
        return stringBuilder.toString();
    }
}
