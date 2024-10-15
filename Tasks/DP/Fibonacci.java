package Tasks.DP;

import java.util.*;

public class Fibonacci {
    public static List<Integer> fibNumbers(int n) {
        List<Integer> result = new ArrayList<>(Collections.nCopies(n, 0));

        // fib nums: [0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, ... ]
        // first n :  1  2  3  4  5  6  7   8   9  10  ...
        result.set(0, 0);
        result.set(1, 1);
        for (int i = 2; i < n; ++i) {
            var prevSum = result.get(i - 1) + result.get(i - 2);
            result.set(i, prevSum);
        }

        return result;
    }

    public static Integer fibSpaceOptimized(int n) {
        if (n <= 1) {
            return 0; 
        }

        int prev1 = 0;
        int prev2 = 1;

        for (int i = 2; i < n; ++i) {
            int curr = prev1 + prev2;
            prev1 = prev2;
            prev2 = curr;
        }

        return prev2;
    }

    public static void main(String[] args) {
        System.out.println("--- --- ---");

        var result = fibNumbers(9);
        for (var i : result) System.out.println(i);

        var fibNum = fibSpaceOptimized(9);
        System.out.println(fibNum);
    }
}
