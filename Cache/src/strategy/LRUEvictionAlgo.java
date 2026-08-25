package strategy;

import utilityClasses.DoublyLinkedList;
import utilityClasses.Node;

import java.util.concurrent.ConcurrentHashMap;

public class LRUEvictionAlgo<K> implements EvictionAlgo<K>{
    private final DoublyLinkedList<K> dll=new DoublyLinkedList<>();
    private final ConcurrentHashMap<K, Node<K>> mapper=new ConcurrentHashMap<>();


    @Override
    public synchronized void keyAccessed(K key) throws Exception {
        if(mapper.contains(key)){
            dll.moveToHead(mapper.get(key));
        }
        else {
            Node<K> newNode = new Node<>(key);
            dll.addFirst(newNode);
            mapper.put(key, newNode);
        }
    }

    @Override
    public synchronized K evictKey() throws Exception {
        Node<K> evictedNode = dll.removeLast();
        if (evictedNode != null) {
            mapper.remove(evictedNode.getValue());
            return evictedNode.getValue();
        }
        return null;
    }
}
