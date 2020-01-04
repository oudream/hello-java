package JavaBasics.StudyJava.Collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 说明：测试Collections工具类
 *
 * @Auther: 11432_000
 * @Date: 2018/12/19 10:37
 * @Description:
 */
public class TestCollections {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("" + i);
        }
        //随机排列
        Collections.shuffle(list);
        //翻转数组
        Collections.reverse(list);
        //排序（从小到大）,需要CompareTo方法
        Collections.sort(list);
        //查找有么有某个元素（二分法,需要先有序）
        Collections.binarySearch(list ,"2");
        System.out.println(list);
    }
}
