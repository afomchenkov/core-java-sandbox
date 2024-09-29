package Tasks.Strings;

import java.util.*;

/**
 * Given a list of words, find all pairs of unique indicies such that the
 * concatenation of the two words is a palindrome.
 * 
 * Example:
 * list = ['code', 'edoc', 'da', 'd']
 * return [(0, 1), (1, 0), (2, 3)]
 * 
 * 1. we can check each possible word pair for palindrome and add their indicies to the result
 * 2. insert all rods into a dict and then check the dict for each ford'prefixes and suffixes
 */
public class GeneratePalindromePairs {
    
    public boolean isPalindrome(String word) {
        // var right = word.length() - 1;
        // var left = 0;
        // while (left < right) {
        //     if (word.charAt(left++) != word.charAt(right--)) {
        //         return false;
        //     }
        // }
        // return true;

        var reversed = new StringBuilder(word).reverse().toString();
        return word.equals(reversed);
    }

    // first approach
    // Time complexity: O(n^2 * c)
    // n - number of words, c - length of the longest word
    public List<List<Integer>> palindromePairs1(List<String> words) {
        List<List<Integer>> result = new ArrayList<>();
        int wordsLen = words.size();
        
        for (int i = 0; i < wordsLen; ++i) {
            for (int j = 0; j < wordsLen; j++) {
                if (i == j) {
                    continue;
                }

                if (isPalindrome(words.get(i) + words.get(j))) {
                    result.add(Arrays.asList(i, j));
                }
            }
        }

        return result;
    }

    // second approach
    //
    // the dict will map each word to its index in the list
    // if the reverse of the word's prefix/suffix is in the dict and its corresponding
    // suffix/prefix is palindromic, we add it to result
    //
    // Example:
    // "aabc" -> "" is palindrome -> look for "cba" -> makes "cbaaaabc"
    // "aabc" -> "a" is palindrome -> look for "cba" -> makes "cbaaabc"
    // "aabc" -> "aa" is palindrome -> look for "cb" -> makes "cbaabc"
    // "aabc" -> "aab" is not palindrome -> terminate
    //
    // Time complexity: O(n * c^2)
    public List<List<Integer>> palindromePairs2(List<String> words) {
        List<List<Integer>> result = new ArrayList<>();
        int wordsLen = words.size();
        
        Map<String, Integer> dict = new HashMap<>();
        for (int i = 0; i < wordsLen; i++) {
            dict.put(words.get(i), i);
        }

        for (int i = 0; i < wordsLen; i++) {
            var word = words.get(i);

            for (int j = 0; j < word.length(); j++) {
                var prefix = word.substring(0, j);
                var suffix = word.substring(j);
                var reversedPrefix = new StringBuilder(prefix).reverse().toString();
                var reversedSuffix = new StringBuilder(suffix).reverse().toString();

                if (isPalindrome(suffix) && dict.containsKey(reversedPrefix)) {
                    if (i != dict.get(reversedPrefix)) {
                        result.add(Arrays.asList(i, dict.get(reversedPrefix)));
                    }
                }

                if (isPalindrome(prefix) && dict.containsKey(reversedSuffix)) {
                    if (i != dict.get(reversedSuffix)) {
                        result.add(Arrays.asList(dict.get(reversedSuffix), i));
                    }
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        var sol = new GeneratePalindromePairs();
        var result = sol.palindromePairs2(Arrays.asList("code", "edoc", "da", "d", "ad"));

        for (var it : result) {
            System.out.println(it.get(0));
            System.out.println(it.get(1));

            System.out.println("---");
        }
    }
}
