
class Node<k,v> {
    k key;
    v val;

    Node prev;
    Node next;
    Node(k key, v val){
        this.key = key;
        this.val = val;
    }
}

public class CustomHashMap<k,v> {
    private final int INITIAL_SIZE=4;
    private final int MAX_CAPACITY=1<<30;
    private final double LOAD_FACTOR=0.75;

    private int countOfNodes = 0;

    private Node[] map;

    public CustomHashMap(){
        map=new Node[INITIAL_SIZE];
        for(int i=0;i<4;i++){
            map[i]=new Node<>(null,null);
            map[i].next=new Node<>(null,null);
            map[i].next.prev=map[i];
        }
    }

    public int getSize(){
        return countOfNodes;
    }

    public Node findNode(k key){
        int bucket = (key.hashCode() & 0x7fffffff) % map.length;
        Node head = map[bucket];

        while(head != null){
            if(head.key != null && head.key.equals(key)){
                return head;
            }
            head = head.next;
        }
        return null;
    }

    public v get(k key){
        Node node = findNode(key);
        return node == null ? null : (v) node.val;
    }

    public void remove(k key){
        Node node=findNode(key);
        if(node==null) return;
        Node prevNode = node.prev;
        Node nextNode = node.next;

        prevNode.next = nextNode;
        nextNode.prev = prevNode;

        countOfNodes--;
    }

    private void rehash(int newSize){
        if(newSize > MAX_CAPACITY){
            System.out.println("Hashmap is exceeding max capacity");
            return;
        }

        Node[] newMap=new Node[newSize];
        for (int i = 0; i < newSize; i++) {
            newMap[i] = new Node<>(null, null);
            newMap[i].next = new Node<>(null, null);
            newMap[i].next.prev = newMap[i];
        }
        for(Node<k,v> curr:map){
            while(curr!=null){
                if(curr.key==null){
                    curr=curr.next;
                    continue;
                }
                int bucket=(curr.key.hashCode() & 0x7fffffff) % newSize;
                Node newHead= newMap[bucket];
                Node oldFirst=newHead.next;
                Node nextNode=curr.next;
                newHead.next=curr;
                curr.next=oldFirst;
                curr.prev=newHead;
                oldFirst.prev=curr;

                curr=nextNode;
            }
        }
        map=newMap;
    }

    public void put(k key,v val){
        Node node=findNode(key);
        if(node != null){
            node.val=val;
            return;
        }
        int bucket=(key.hashCode() & 0x7fffffff) % map.length;
        Node head= map[bucket];

        Node newNode = new Node(key, val);
        Node old_first = head.next;
        head.next=newNode;
        newNode.next=old_first;
        newNode.prev=head;
        old_first.prev=newNode;

        countOfNodes++;

        if(countOfNodes > LOAD_FACTOR * map.length){
            rehash(map.length * 2);
        }

    }

}
