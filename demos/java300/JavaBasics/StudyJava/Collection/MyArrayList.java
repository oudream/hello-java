package JavaBasics.StudyJava.Collection;

/**
 * 说明：自定义ArrayList
 *
 * @Auther: 11432_000
 * @Date: 2018/12/14 10:19
 * @Description:
 */
public class MyArrayList<T> {

    private static final int MAX_ARRAY_SIZE = 2147483639;
    private static final int INIT_ARRAYS_SIZE = 10;
    private Object[] ts;
    private int length = 0;
    private int size;

    public MyArrayList(int size){
        this.ts = new Object[size];
        this.size = size;
    }

    public MyArrayList() {
        this(INIT_ARRAYS_SIZE);
    }

    public void add(T value){
        add(this.length, value);
    }

    public void add(int index ,T value){
        checkLength();
        checkIndex(index);
        if (index != this.length){
            System.arraycopy(this.ts ,index ,this.ts ,index + 1, this.length - index);
        }
        this.ts[index] = value;
        this.length ++;
    }

    public T get(int index){
        checkIndexByLength(index);
        return (T) this.ts[index];
    }
    public void remove(int index){
       checkIndexByLength(index);
       if (index != this.length - 1){
           System.arraycopy(this.ts ,index + 1,this.ts , index ,this.length - index - 1);
       }else {
           this.ts[this.length - 1] = null;
       }
       this.length --;
    }
    public boolean remove(T value){
        if (value == null){ return false; }
        int index = indexOf(value);
        if (index == -1){
            return false;
        }
        remove(index);
        return true;
    }

    public int indexOf(T value){
        return indexOf(0 ,value);
    }

    public int indexOf(int index ,T value){
        checkIndexByLength(index);
        for (int i = index; i < this.length; i++) {
            if (this.ts[i] != null && this.ts[i].equals(value)){
                return i;
            }
        }
        return -1;
    }

    public void clear(){
        if (this.length == 0){ return; }
        for (int i = 0; i < this.length; i++) {
            this.ts[i] = null;
        }
    }

    public T set(int index ,T value){
        checkIndex(index);
        T oldValue =(T) this.ts[index];
        this.ts[index] = value;
        return oldValue;
    }

    public boolean isEmpty(){
        if (this.length == 0){
            return true;
        }
        return false;
    }

    public int getLength() {
        return this.length;
    }

    public int getSize() {
        return this.size;
    }

    private void dilatation(){
        int newSize =this.size + (this.size >> 1);
        if (newSize >= MAX_ARRAY_SIZE){
            throw new RuntimeException("数组长度过长");
        }
        Object[] newObjects = new Object[newSize];
        System.arraycopy(this.ts , 0, newObjects ,0 ,this.length);
        this.size = newSize;
        this.ts = newObjects;
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        for (int i = 0; i < this.length; i++) {
            stringBuilder.append(this.ts[i].toString() + ",");
        }
        stringBuilder.deleteCharAt(stringBuilder.lastIndexOf(",")).append("]");
        return stringBuilder.toString();
    }

    private void checkLength(){
        if (this.length >= this.size){
            dilatation();
        }
    }
    private void checkIndex(int index){
        if (index >= this.size || index < 0){
            throw new RuntimeException("数组越界");
        }
    }

    private void checkIndexByLength(int index){
        checkIndex(index);
        if (index >= this.length){
            throw new RuntimeException("数组越界");
        }
    }
}
