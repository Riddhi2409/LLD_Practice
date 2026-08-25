package utilityClasses;

public class DoublyLinkedList<K>{
    private final Node<K> head;
    private final Node<K> tail;

    public DoublyLinkedList() {
        head=new Node<>(null);
        tail=new Node<>(null);
        head.next=tail;
        tail.prev=head;
    }

    public void addFirst(Node<K> node){
        node.next=head.next;
        node.prev=head;
        head.next.prev=node;
        head.next=node;
    }

    public void remove(Node<K> node){
        if(node==null) return;
        node.prev.next=node.next;
        node.next.prev=node.prev;
    }

    public void moveToHead(Node<K> node) {
        remove(node);
        addFirst(node);
    }

    public Node<K> removeLast(){
        if(tail.prev==head) return null;
        Node<K> node=tail.prev;
        remove(node);
        return node;
    }
}
