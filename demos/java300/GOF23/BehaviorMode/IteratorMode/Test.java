package GOF23.BehaviorMode.IteratorMode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 说明：迭代器模式
 *
 * @Auther: 11432_000
 * @Date: 2019/1/28 14:43
 * @Description:
 */
public class Test {

    public static void main(String[] args) {
       MyArray myArray = new MyArray();
       myArray.add("55656");
        MyIterator iterator = myArray.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
