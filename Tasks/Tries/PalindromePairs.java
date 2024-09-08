package Tasks.Tries;

import java.util.*;

// Given a list of unique words, find all pairs of distinct indicies(i, j) in the given list, so that
// the concatenation of the two words words[i] + words[j] is a palindrome.
//
// Given words = ["bat", "tab", "cat"]
// return [[0, 1], [1, 0]]
// palindromes ["battab", "tabbat"]
//
// Given words = ["abcd", "dcba", "lls", "s", "sssll"]
// return [[0, 1], [1, 0], [3, 2], [2, 4]]
// ["dcbaabcd", "abcddcba", "slls", "llssssll"]

public class PalindromePairs {
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> ret = new ArrayList<>();

        if (words == null || words.length < 2) {
            return ret;
        }

        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < words.length; ++i) {
            map.put(words[i], i);
        }

        for (int i = 0; i < words.length; ++i) {
            for (int j = 0; j < words[i].length(); ++j) {
                // word   = "0 . . . j . . . . . N"
                // splits =  |  str1 |   str2    |
                String str1 = words[i].substring(0, j);
                String str2 = words[j].substring(j);

                if (isPalindrome(str1)) {
                    String str2rvs = new StringBuilder(str2).reverse().toString();
                    if (map.containsKey(str2rvs) && map.get(str2rvs) != i) {
                        List<Integer> list = Arrays.asList(map.get(str2rvs), i);
                        ret.add(list);
                    }
                }
                if (isPalindrome(str2)) {
                    String str1rvs = new StringBuilder(str1).reverse().toString();
                    boolean avoidDuplicates = str2.length() != 0;
                    if (map.containsKey(str1rvs) && map.get(str1rvs) != i && avoidDuplicates) {
                        List<Integer> list = Arrays.asList(map.get(str1rvs), i);
                        ret.add(list);
                    }
                }
            }
        }

        return ret;
    }

    private boolean isPalindrome(String str) {
        int left = 0;
        int right = str.length() - 1;
        
        while (left <= right) {
            if (str.charAt(left++) != str.charAt(right--)) {
                return false;
            } 
        }

        return true;
    }
}
