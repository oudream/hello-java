package JavaBasics.StudyJava.Collection;

/**
 * 说明：自定义LinkedList
 *
 * @Auther: 11432_000
 * @Date: 2018/12/17 10:24
 * @Description:
 */
public class MyLinkList<T> {
    private Node head;
    private Node last;
    private int size;

    public MyLinkList() {
        this.size = 0;
        this.head = new Node();
    }

    public void add(T value){
        if (head.getNext() == null){
            Node newNode = new Node(this.head , value);
            head.setNext(newNode);
            this.last = newNode;
        }else {
            Node newNode = new Node(this.last ,value);
            this.last.setNext(newNode);
            this.last = newNode;
        }
        this.size ++;
    }

    public T get(int index){
        checkIndex(index);
        Node node = getIndexNode(index);
        return node != null ? (T) node.getElement() : null;
    }

    private void checkIndex(int index){
        if (index >= this.size){
            throw new RuntimeException("越界");
        }
    }

    private Node getIndexNode(int index){
        Node node;
        if (index <= size >> 1){
            node = this.head.getNext();
            for (int i = 0; i < index; i++) {
                node = node.getNext();
            }
        }else{
            node = this.last;
            for (int i = this.size - 1; i > index; i--) {
                node = node.getPrevious();
            }
        }
        return node;
    }

    @Override
    public String toString(){
        Node node = this.head;
        StringBuilder str = new StringBuilder("[");
        while (node.getNext() != null){
            Object element = (String) node.getNext().getElement();
            str.append(element + ",");
            node = node.getNext();
        }
        str.deleteCharAt(str.length() - 1);
        str.append("]");
        return str.toString();
    }

    public void remove(int index){
        checkIndex(index);
        Node node = getIndexNode(index);
        if (node != null){
            Node up = node.getPrevious();
            Node down = node.getNext();
            if (up != null){
                up.setNext(down);
            }
            if (down != null){
                down.setPrevious(up);
            }
            this.size --;
        }
    }

    public void insert(int index ,T value){
        checkIndex(index);
        Node indexNode = getIndexNode(index);
        if (indexNode != null){
            Node newNode = new Node(value);
            Node up = indexNode.getPrevious();
            if (up != null){
                newNode.setPrevious(up);
                up.setNext(newNode);
            }
            if (indexNode != null){
                indexNode.setPrevious(newNode);
                newNode.setNext(indexNode);
            }
            this.size ++;
        }
    }
}



