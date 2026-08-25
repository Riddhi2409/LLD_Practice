package utilityClasses.LFU;

public class DoublyLinkedList<K> {
    private final Node<K> head;
    private final Node<K> tail;
    int size;

    public DoublyLinkedList() {
        head=new Node<>(null);
        tail=new Node<>(null);
        head.next=tail;
        tail.prev=head;
        size=0;
    }

    public void addFirst(Node<K> node){
        node.next=head.next;
        node.prev=head;
        head.next.prev=node;
        head.next=node;
        size++;
    }
    public void remove(Node<K> node){
        if (node.prev != null && node.next != null) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            size--;
        }
    }

    public Node<K> removeLast() {
        if (size == 0) return null;
        Node<K> last = tail.prev;
        remove(last);
        return last;
    }

    public int getSize() {
        return size;
    }
}
