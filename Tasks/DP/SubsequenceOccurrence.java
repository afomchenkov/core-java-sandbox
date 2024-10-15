package Tasks.DP;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    public static int countSubsequenceOccurrence(String str, String pattern) {
        int strLen = str.length();
        int patternLen = pattern.length();

        var dp = Stream.generate(() -> new ArrayList<Integer>(Collections.nCopies(patternLen + 1, 0)))
                .limit(strLen + 1)
                .collect(Collectors.toList());

        for (int i = 0; i <= strLen; ++i) {
            dp.get(i).set(0, 1); // empty seq
        }

        for (int i = 1; i <= strLen; ++i) {
            for (int j = 1; j <= patternLen; ++j) {
                var prev = dp.get(i - 1).get(j);

                if (str.charAt(i - 1) == pattern.charAt(j - 1)) {
                    dp.get(i).set(j, dp.get(i - 1).get(j - 1) + prev);
                    continue;
                }
                
                dp.get(i).set(j, prev);
            }
        }

        return dp.get(strLen).get(patternLen);
    }

    public static void main(String[] args) {
        System.out.println("--- --- ---");

        String str = "10101";
        String patter = "10";
        // output 3 -> ["10...", "1..0.", "..10."]

        // dp:
        // 1 0 0 
        // 1 1 0 
        // 1 1 1 
        // 1 2 1 
        // 1 2 3 
        // 1 3 3 

        var result = countSubsequenceOccurrence(str, patter);
        System.out.println(result);
    }
}
