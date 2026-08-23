import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        testMinHeap();
        System.out.println();
        testMaxHeap();
    }

    private static void testMinHeap() {
        System.out.println("=== MinHeap Test ===");
        Heap<Integer> minHeap = new MinHeap<>();

        int[] values = {5, 3, 8, 1, 9, 2, 7};
        for (int v : values) {
            minHeap.add(v);
        }

        System.out.print("Popped order (should be ascending): ");
        List<Integer> result = new ArrayList<>();
        while (!minHeap.isEmpty()) {
            result.add(minHeap.pop());
        }
        System.out.println(result);

        System.out.println("Sorted check: " + isSorted(result, true));

        // Peek/empty checks
        Heap<Integer> emptyHeap = new MinHeap<>();
        System.out.println("New heap isEmpty(): " + emptyHeap.isEmpty());
        try {
            emptyHeap.peek();
            System.out.println("ERROR: expected exception on peek of empty heap");
        } catch (Exception e) {
            System.out.println("peek() on empty heap correctly threw: " + e.getClass().getSimpleName());
        }
    }

    private static void testMaxHeap() {
        System.out.println("=== MaxHeap Test ===");
        Heap<Integer> maxHeap = new MaxHeap<>();

        int[] values = {5, 3, 8, 1, 9, 2, 7};
        for (int v : values) {
            maxHeap.add(v);
        }

        System.out.print("Popped order (should be descending): ");
        List<Integer> result = new ArrayList<>();
        while (!maxHeap.isEmpty()) {
            result.add(maxHeap.pop());
        }
        System.out.println(result);

        System.out.println("Sorted check: " + isSorted(result, false));

        // Duplicates and single-element edge case
        Heap<Integer> single = new MaxHeap<>();
        single.add(42);
        System.out.println("Single element peek: " + single.peek());
        System.out.println("Single element pop: " + single.pop());
        System.out.println("isEmpty after popping only element: " + single.isEmpty());

        Heap<Integer> dupHeap = new MaxHeap<>();
        int[] dups = {4, 4, 2, 4, 1, 2};
        for (int v : dups) dupHeap.add(v);
        List<Integer> dupResult = new ArrayList<>();
        while (!dupHeap.isEmpty()) dupResult.add(dupHeap.pop());
        System.out.println("Duplicates popped order: " + dupResult);
    }

    private static boolean isSorted(List<Integer> list, boolean ascending) {
        for (int i = 1; i < list.size(); i++) {
            if (ascending && list.get(i - 1) > list.get(i)) return false;
            if (!ascending && list.get(i - 1) < list.get(i)) return false;
        }
        return true;
    }
}