package Comparator;
import java.util.*;

public class ComparatorExample {
    public static void main(String[] args) {

        // Comparator<Integer> com1 = new Comparator<Integer>() {
        //     public int compare(Integer a, Integer b) {
        //         return a - b;
        //     }
        // };
        Comparator<Integer> com2 = (a, b) -> a - b;

        List<Integer> nums = new ArrayList<>();
        nums.add(34);
        nums.add(2);
        nums.add(11);
        nums.add(8);
        nums.add(21);

        Collections.sort(nums, com2);

        System.out.println(nums);
    }
}
