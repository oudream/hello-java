package JavaBasics.StudyJava.Map;

import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

/**
 * 说明：测试TreeMap和Comparable接口（实现排序）
 *
 * @Auther: 11432_000
 * @Date: 2018/12/19 08:43
 * @Description:
 */
public class TestTreeMap {

    public static void main(String[] args) {
        Map<TreeMapEntry ,String> map = new TreeMap<>();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            TreeMapEntry treeMapEntry = new TreeMapEntry();
            treeMapEntry.setId(random.nextInt(100));
            treeMapEntry.setName("mayao" + random.nextInt(1000));
            map.put(treeMapEntry ,"hhh" + i);
        }
        for (TreeMapEntry i : map.keySet()){
            System.out.println(i);
        }

    }
}

