public class Main {
    public static void main(String[] args) {
        CustomHashMap<String, Integer> map = new CustomHashMap<>();

        // Basic put/get
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 3);
        System.out.println("get(a) = " + map.get("a")); // 1
        System.out.println("get(b) = " + map.get("b")); // 2
        System.out.println("get(z) = " + map.get("z")); // null
        System.out.println("size = " + map.getSize());  // 3

        // Update existing key (shouldn't increase size)
        map.put("a", 100);
        System.out.println("get(a) after update = " + map.get("a")); // 100
        System.out.println("size after update = " + map.getSize());  // 3

        // Remove
        map.remove("b");
        System.out.println("get(b) after remove = " + map.get("b")); // null
        System.out.println("size after remove = " + map.getSize());  // 2

        // Remove non-existent key (should be a no-op, not throw)
        map.remove("doesNotExist");
        System.out.println("size after removing missing key = " + map.getSize()); // 2

        // Force a resize by inserting enough entries
        // INITIAL_SIZE=4, LOAD_FACTOR=0.75 -> resize triggers once size > 3
        for (int i = 0; i < 20; i++) {
            map.put("key" + i, i);
        }
        System.out.println("size after bulk insert = " + map.getSize()); // 22
        for (int i = 0; i < 20; i++) {
            Integer v = map.get("key" + i);
            if (v == null || v != i) {
                System.out.println("MISMATCH at key" + i + ": got " + v);
            }
        }
        System.out.println("Bulk insert verification done.");

        // Demonstrate the negative hashCode bug (Integer key with negative hashCode)
        CustomHashMap<Integer, String> intMap = new CustomHashMap<>();
        try {
            intMap.put(-5, "negative five"); // Integer.hashCode(-5) == -5
            System.out.println("put(-5) succeeded: " + intMap.get(-5));
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("BUG CONFIRMED: negative hashCode crashed put() -> " + e.getMessage());
        }
    }
}