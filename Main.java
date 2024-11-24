
import java.util.*;

public class Main {

    public static void main(String[] args) {

        // String[] arr = {"string", "123", "123.321", "c"};
        // int[][] m = {{2, 4, 1, 0}};
        // var l = m.length;
        System.out.println("----");

        List<int[]> arr = new ArrayList<>();
        arr.add(new int[]{4, 5, 6});
        // var l = arr.size();

        int array[] = new int[10];
        // var arl1 = array.length;
        // Arrays.fill(array, 10);
        Arrays.setAll(array, p -> p + 1);

        for (var t : array) {
            System.out.println(t);
        }

        List<Integer> l1 = new ArrayList<>();
        l1.removeLast();

        Map<String, Integer> h = new HashMap<>();
        h.put("key", 123);
        // h.containsKey(h)
        // h.get(h)
        // h.containsValue(l1)

        var t = "test".toCharArray();

    }
}
