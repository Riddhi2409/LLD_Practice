package strategy;

public interface EvictionAlgo<K> {

    // Notifies the eviction algorithm that the given key was accessed
    void keyAccessed(K key) throws Exception;

    // Selects and removes one key to be evicted (from the cache).
    K evictKey() throws Exception;
}
