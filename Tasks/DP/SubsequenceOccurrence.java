package Tasks.DP;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// Given two strings, find the number of times the second string occurs in the
// first string, whether continuous or discontinuous.
//
// The problem can be divided into sub-problems. Process all characters of both strings one by
// one starting from either from left or right side.

// m: Length of str1 (first string)
// n: Length of str2 (second string)
//
// If last characters of two strings are same, 
//    1. We consider last characters and get count for remaining 
//       strings. So we recur for lengths m-1 and n-1. 
//    2. We can ignore last character of first string and 
//       recurse for lengths m-1 and n.
// else 
// If last characters are not same, 
//    We ignore last character of first string and 
//    recurse for lengths m-1 and n.

public class SubsequenceOccurrence {
    public static int countRecursive(String str, String pattern, int strLen, int patternLen) {
        // if both str empty or str2 empty
        if ((strLen == 0 && patternLen == 0) || patternLen == 0) {
            return 1;
        }

        // if str1 empty and str2 not empty
        if (strLen == 0) {
            return 0;
        }

        // If last characters are same, recur for remaining strings by
        // 1. considering last characters of both strings
        // 2. ignoring last character of first string
        if (str.charAt(strLen - 1) == pattern.charAt(patternLen - 1)) {
            return countRecursive(str, pattern, strLen - 1, patternLen - 1) +
                    countRecursive(str, pattern, strLen - 1, patternLen);
        }

        // If last characters are different, ignore last char
        // of first string and recur for remaining string
        return countRecursive(str, pattern, strLen - 1, patternLen);
    }

    // top-down DP
    public static int countTopDownDP(String a, String b, int m, int n, int[][] dp) {
        // if both str empty or str2 empty
        if ((m == 0 && n == 0) || n == 0) {
            return 1;
        }

        // if str1 empty and str2 not empty
        if (m == 0) {
            return 0;
        }

        if (dp[m][n] != -1) {
            return dp[m][n];
        }

        // If last characters are same, recur for remaining strings by
        // 1. considering last characters of both strings
        // 2. ignoring last character of first string
        if (a.charAt(m - 1) == b.charAt(n - 1)) {
            return dp[m][n] = countTopDownDP(a, b, m - 1, n - 1, dp)
                    + countTopDownDP(a, b, m - 1, n, dp);
        }

        // If last characters are different, ignore last char of first
        // string and recur for remaining string
        return dp[m][n] = countTopDownDP(a, b, m - 1, n, dp);
    }

    // bottom-up DP
    public static int countSubsequenceOccurrence(String str, String pattern) {
        int strLen = str.length();
        int patternLen = pattern.length();

        // int lookup[][] = new int[m + 1][n + 1];
        var lookup = Stream.generate(() -> new ArrayList<Integer>(Collections.nCopies(patternLen + 1, 0)))
                .limit(strLen + 1)
                .collect(Collectors.toList());

        for (int i = 0; i <= strLen; ++i) {
            lookup.get(i).set(0, 1); // empty seq
        }

        // Fill lookup[][] in bottom up manner
        for (int i = 1; i <= strLen; ++i) {
            for (int j = 1; j <= patternLen; ++j) {
                var prev = lookup.get(i - 1).get(j);

                // If last characters are the same, we have two options -
                // 1. consider last characters of both strings in solution
                // 2. ignore last character of first string
                if (str.charAt(i - 1) == pattern.charAt(j - 1)) {
                    lookup.get(i).set(j, lookup.get(i - 1).get(j - 1) + prev);
                    continue;
                }
                // If last character are different, ignore last character of first string
                lookup.get(i).set(j, prev);
            }
        }

        return lookup.get(strLen).get(patternLen);
    }

    public static void main(String[] args) {
        System.out.println("--- --- ---");

        String str = "10101";
        String pattern = "10";
        // output 3 -> ["10...", "1..0.", "..10."]

        // dp:
        // 1 0 0
        // 1 1 0
        // 1 1 1
        // 1 2 1
        // 1 2 3
        // 1 3 3

        var result = countSubsequenceOccurrence(str, pattern);
        System.out.println(result);

        int[][] dp = new int[str.length() + 1][pattern.length() + 1];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        System.out.println(countTopDownDP(str, pattern, str.length(), pattern.length(), dp));
    }
}
