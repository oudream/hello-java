package GOF23.BehaviorMode.IteratorMode;

import java.util.ArrayList;
import java.util.List;

/**
 * 说明：
 *
 * @Auther: 11432_000
 * @Date: 2019/1/28 14:32
 * @Description:
 */
public class MyArray {

    private List<String> list = new ArrayList();

    public String get(int index){
        return list.get(index);
    }

    public void add(String value){
        list.add(value);
    }

    public void delete(String value){
        list.remove(value);
    }

    public MyIterator iterator(){
        return new MyArrayIterator();
    }

    private class MyArrayIterator implements MyIterator{

        private int max;
        private int index;

        public MyArrayIterator() {
            this.index = 0;
        }

        @Override
        public boolean hasNext() {
            if (index < list.size()){
                return true;
            }
            return false;
        }

        @Override
        public Object next() {
            return list.get(index ++);
        }
    }
}
