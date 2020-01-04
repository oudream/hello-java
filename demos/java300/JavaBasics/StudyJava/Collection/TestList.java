package JavaBasics.StudyJava.Collection;

import java.util.ArrayList;
import java.util.List;

/**
 * 说明：
 *
 * @Auther: 11432_000
 * @Date: 2018/12/14 14:00
 * @Description:
 */
public class TestList {
    public static void main(String[] args) {
        List<String> list = new ArrayList();
        int index = 0;
        //取值
        list.get(index);
        //添加+插入
        list.add(index ,"");
        list.add("");
        //删除
        list.remove(index);
        list.remove("");
        //更新
        list.set(index ,"");
        //查找指定元素第一次出现的索引，没有返回-1
        list.indexOf("");
        list.lastIndexOf("");
    }
}
