package Tasks.DP;

import java.util.Arrays;

public class KnapsackProblem {

    /**
     * Problem statement:
     * 
     * Given a set of items, each with a weight and a value, determine the number of
     * each item to include in a collection so that the total weight is less than or
     * equal to a given limit and the total value is as large as possible.
     * 
     */

    public static void main(String args[]) {
        int w = 10; // total allowed weight
        int n = 4; // number of items
        int[] val = { 10, 40, 30, 50 };
        int[] wt = { 5, 4, 6, 3 };

        // Populate base cases
        int[][] mat = new int[n + 1][w + 1];
        for (int r = 0; r < w + 1; r++) {
            mat[0][r] = 0;
        }
        for (int c = 0; c < n + 1; c++) {
            mat[c][0] = 0;
        }

        for (int item = 1; item <= n; item++) {
            for (int capacity = 1; capacity <= w; capacity++) {
                int maxValWithoutCurr = mat[item - 1][capacity]; // This is guaranteed to exist
                int maxValWithCurr = 0; // We initialize this value to 0

                int weightOfCurr = wt[item - 1]; // We use "item - 1" to account for the extra row at the top
                if (capacity >= weightOfCurr) { // We check if the knapsack can fit the current item
                    maxValWithCurr = val[item - 1]; // If so, maxValWithCurr is at least the value of the current item

                    int remainingCapacity = capacity - weightOfCurr; // remainingCapacity must be at least 0
                    maxValWithCurr += mat[item - 1][remainingCapacity]; // Add the maximum value obtainable with the
                                                                        // remaining capacity
                }

                mat[item][capacity] = Math.max(maxValWithoutCurr, maxValWithCurr); // Pick the larger of the two
            }
        }

        System.out.println(mat[n][w]); // Final answer
        System.out.println(Arrays.deepToString(mat)); // Visualization of the table
    }
}
