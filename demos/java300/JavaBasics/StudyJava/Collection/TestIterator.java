package JavaBasics.StudyJava.Collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 说明：
 *
 * @Auther: 11432_000
 * @Date: 2018/12/19 09:41
 * @Description:
 */
public class TestIterator {
    public static void main(String[] args) {
        List<String> list = new ArrayList();
        for (int i = 0; i < 400; i++) {
            list.add("" + i);
        }
        Iterator<String> iterators = list.iterator();
        while (iterators.hasNext()){
            System.out.print(iterators.next() + "\t");
        }
    }
}
