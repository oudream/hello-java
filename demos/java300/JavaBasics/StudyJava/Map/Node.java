package JavaBasics.StudyJava.Map;

/**
 * 说明：
 *
 * @Auther: 11432_000
 * @Date: 2018/12/18 14:09
 * @Description:
 */
public class Node<K,V> {
    final K key;
    V value;
    Node<K,V> next;
    final int hash;

    public Node(K key, V value, Node<K, V> next, int hash) {
        this.key = key;
        this.value = value;
        this.next = next;
        this.hash = hash;
    }
}
