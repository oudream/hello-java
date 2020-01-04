package JavaBasics.StudyJava.Map;

import java.util.HashMap;
import java.util.Map;

/**
 * 说明：
 *
 * @Auther: 11432_000
 * @Date: 2018/12/17 14:45
 * @Description:
 */
public class TestMap {

    public static void main(String[] args) {
        Map<String, String> map = new HashMap();
        //元素存取
        map.put("01" ,"one");
        map.get("01");
        //删除
        map.remove("01");
        //查看Map中是否包含Key或Value
        map.containsKey("");
        map.containsValue("");
        //获取Map大小
        map.size();
        //复制一个Map到目标Map
        map.putAll(new HashMap<>());
        //检查Map是否为空
        map.isEmpty();
        //清空Map
        map.clear();
    }


}
