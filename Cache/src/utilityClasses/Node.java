package utilityClasses;

public class Node<K> {
    private final K value;
    Node<K> prev;
    Node<K> next;

    public Node(K value) {
        this.value = value;
        this.prev=null;
        this.next=null;
    }

    public K getValue() {
        return value;
    }
}
