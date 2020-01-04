package JavaBasics.StudyJava.Collection;

/**
 * 说明：
 *
 * @Auther: 11432_000
 * @Date: 2018/12/17 10:55
 * @Description:
 */
public class Node{
    private Node previous;
    private Node next;
    private Object element;

    public Node(Node previous, Node next, Object element) {
        this.previous = previous;
        this.next = next;
        this.element = element;
    }

    public Node(Node previous, Node next) {
        this.previous = previous;
        this.next = next;
    }

    public Node(Node previous, Object element) {
        this.previous = previous;
        this.element = element;
    }

    public Node(Object element) {
        this.element = element;
    }

    public Node() {
    }

    public Node getPrevious() {
        return previous;
    }

    public void setPrevious(Node previous) {
        this.previous = previous;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Object getElement() {
        return element;
    }

    public void setElement(Object element) {
        this.element = element;
    }
}
