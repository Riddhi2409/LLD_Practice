package utilityClasses.LFU;

public class Node<K> {
    private final K key;
    int freq;
    Node<K> prev,next;

    public Node(K key) {
        this.key = key;
        freq=1;
        prev=null;
        next=null;

    }

    public K getKey() {
        return key;
    }

    public int getFreq() {
        return freq;
    }

    public void setFreq(int freq) {
        this.freq = freq;
    }
}
