package JavaBasics.StudyJava.Collection;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 说明：Collection接口中的方法
 *
 * @Auther: 11432_000
 * @Date: 2018/12/14 10:45
 * @Description:
 */
public class TestCollection {

    public static void main(String[] args) {
        Collection collection = new ArrayList();
        Collection collection2 = new ArrayList();
        //返回当前集合大小
        collection.size();
        //判断集合是否为空
        collection.isEmpty();
        //在末尾添加一个元素
        collection.add("kkk");
        //移除一个元素，删引用
        collection.remove("kkk");
        //清空集合
        collection.clear();
        //转换为数组
        collection.toArray();
        //判断是否包含某元素（依赖equals方法）
        collection.contains("kkk");
        //排序，依赖Comparable接口的实现
        //((ArrayList) collection).sort();
        //将一个集合添加到另一个容器中
        ((ArrayList) collection).addAll(0 ,collection2);
        //将某集合中的内容从另一个集合中删除
        collection.removeAll(collection2);
        //取两个集合的交集
        collection.retainAll(collection2);
    }


}
