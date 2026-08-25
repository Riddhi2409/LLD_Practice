package strategy;

import utilityClasses.LFU.DoublyLinkedList;
import utilityClasses.LFU.Node;

import java.util.HashMap;
import java.util.Map;

public class LFUEvictionAlgo<K> implements EvictionAlgo<K>{

    private final Map<K, Node<K>> keyNode;
    private final Map<Integer, DoublyLinkedList<K>> freqList;
    private int minFreq;

    public LFUEvictionAlgo() {
        this.keyNode = new HashMap<>();
        this.freqList = new HashMap<>();
        this.minFreq = 0;

    }

    @Override
    public synchronized void keyAccessed(K key) throws Exception {
        if(keyNode.containsKey(key)){
            Node<K> node=keyNode.get(key);
            int currentFreq=node.getFreq();
            DoublyLinkedList<K> currList=freqList.get(currentFreq);
            currList.remove(node);
            if (currentFreq == minFreq && currList.getSize() == 0) {
                minFreq++;
            }

            node.setFreq(currentFreq+1);
            freqList.computeIfAbsent(node.getFreq(),k -> new DoublyLinkedList<>()).addFirst(node);

        }
        else{
            Node<K> newNode = new Node<>(key);
            keyNode.put(key, newNode);

            freqList.computeIfAbsent(1, k -> new DoublyLinkedList<>()).addFirst(newNode);
            this.minFreq = 1;
        }
    }

    @Override
    public K evictKey() throws Exception {
        DoublyLinkedList<K> minFreqDLL = freqList.get(minFreq);
        if (minFreqDLL == null || minFreqDLL.getSize() == 0) {
            return null;
        }

        // Evict LRU node from lowest frequency list
        Node<K> evictedNode = minFreqDLL.removeLast();
        if (evictedNode != null) {
            keyNode.remove(evictedNode.getKey());
            return evictedNode.getKey();
        }

        return null;
    }
}
