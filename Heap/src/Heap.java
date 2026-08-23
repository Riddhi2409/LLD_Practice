public interface Heap<T> {
    public void add(T val);
    public T pop();
    public T peek();
    public boolean isEmpty();
}