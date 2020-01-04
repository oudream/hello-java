package JavaBasics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 说明：
 *
 * @Auther: 11432_000
 * @Date: 2018/12/19 10:57
 * @Description:
 */
public class Table {

    public static void main(String[] args) {
        List<Map<String ,String>> mapList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Map<String ,String> map = new HashMap<>();
            map.put("Sno", "2015000" + i);
            map.put("Sname", "麻尧" + i);
            map.put("Sage", "" + i);
            mapList.add(map);
        }
        for (Map<String ,String> map : mapList){
            for (String key :map.keySet()){
                System.out.print(map.get(key) + "\t");
            }
            System.out.println();
        }
    }
}
