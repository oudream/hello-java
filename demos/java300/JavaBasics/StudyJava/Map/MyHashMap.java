package JavaBasics.StudyJava.Map;

/**
 * 说明：自定义HashMap
 *
 * @Auther: 11432_000
 * @Date: 2018/12/18 14:08
 * @Description:
 */
public class MyHashMap<K,V>{

    private int size;
    private int length;
    private Node<K,V>[] hashTable;

    public MyHashMap() {
        this.hashTable = new Node[16];
        this.size = 16;
    }

    public MyHashMap(int size) {
        this.size = size;
        this.hashTable = new Node[size];
    }

    public void put(K key ,V value){
        Node<K,V> head;
        Node<K,V> newNode = new Node<>(key ,value ,null ,hashAlgorithm(key));
        head = this.hashTable[newNode.hash];
        if (head != null){
            addEntry(head ,newNode);
        }else {
            this.hashTable[newNode.hash] = newNode;
        }
        this.length ++;
    }

    public V get(K key){
        Node<K,V> head;
        if (key == null){
            head = this.hashTable[0];
            while (head != null){
                if (0 == head.hash && head.key == null){
                    return head.value;
                }
                head = head.next;
            }
        }else {
            int hash = hashAlgorithm(key);
            head = this.hashTable[hash];
            while (head != null){
                boolean isEqual = head.key == key || head.equals(key);
                if (hash == head.hash && isEqual){
                    return head.value;
                }
                head = head.next;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("[");
        for (int i = 0; i < this.size; i++) {
            Node node = this.hashTable[i];
            while (node != null){
                stringBuilder.append(node.key + ":" + node.value + ",");
                node = node.next;
            }
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    public int length(){
        return this.length;
    }

    private int hashAlgorithm(K key){
        /**
         *
         * 功能描述: 通过hashCode计算索引值（取余，位运算版）
         *
         * @param: [key]
         * @return: int
         * @auther: 11432_000
         * @date: 2018/12/18 14:25
         */
        if (key == null){
            return 0;
        }
        int hashCode = key.hashCode();
        return hashCode & (this.size - 1);
    }

    private void addEntry(Node head ,Node newNode){
        while (head.next != null){
            boolean isEqual = (head.key == newNode.key || head.key.equals(newNode.key));
            if (head.hash == newNode.hash && isEqual){
                head.value = newNode.value;
                return;
            }
            head = head.next;
        }
        head.next = newNode;
    }
}
