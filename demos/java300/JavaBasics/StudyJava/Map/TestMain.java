package JavaBasics.StudyJava.Map;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * 说明：
 *
 * @Auther: 11432_000
 * @Date: 2018/12/18 14:27
 * @Description:
 */
public class TestMain {

    public static void main(String[] args) {
        Map<Integer ,String> myHashMap = new HashMap<>();

        myHashMap.put(10,"aa");
        myHashMap.put(20,"bb");
        myHashMap.put(30,"cc");
        myHashMap.put(69,"ppp");
        myHashMap.put(53,"dd");
        myHashMap.put(85,"666");
        myHashMap.put(null,"dfff");
        Set<Map.Entry<Integer, String>> entries = myHashMap.entrySet();
        Iterator<Map.Entry<Integer, String>> iterators;
        for (iterators = entries.iterator(); iterators.hasNext();){

        }
        System.out.println();
        for (Integer integer : myHashMap.keySet()){
            System.out.print(myHashMap.get(integer) + "\t");
        }
    }
}
