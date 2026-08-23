import java.util.ArrayList;
import java.util.List;

public class MinHeap<T extends Comparable<T>> implements Heap<T> {
    private List<T> list;

    public  MinHeap(){
        list=new ArrayList<>();
    }

    @Override
    public void add(T val) {
        list.add(val);
        insertHeapify(list.size()-1);
    }

    @Override
    public T pop() {
        if (isEmpty()) throw new java.util.NoSuchElementException("Heap is empty");
        swap(0, list.size() - 1);
        T val = list.remove(list.size() - 1);
        if (!isEmpty()) {
            deleteHeapify(0);
        }
        return val;
    }

    @Override
    public T peek() {
        return list.getFirst();
    }

    @Override
    public boolean isEmpty() {
        return list.size()==0;
    }

    private void swap(int index1,int index2){
        T val=list.get(index1);
        list.set(index1,list.get(index2));
        list.set(index2,val);
    }

    private  void insertHeapify(int index){
        if(index==0) return;
        int parent=(index-1)/2;
        if(list.get(index).compareTo(list.get(parent))<0){
            swap(index,parent);
            insertHeapify(parent);
        }
    }

    private void deleteHeapify(int index){
        int left = 2 * index + 1;
        int right = 2 * index + 2;
        int smallest = index;

        if (left < list.size() && list.get(left).compareTo(list.get(smallest)) < 0) {
            smallest = left;
        }
        if (right < list.size() && list.get(right).compareTo(list.get(smallest)) < 0) {
            smallest = right;
        }
        if (smallest != index) {
            swap(smallest, index);
            deleteHeapify(smallest);
        }
    }
}
