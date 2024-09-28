package Tasks.Strings;

import java.util.*;

/**
 * Given a word W and a string S, find all indices in S which are the starting
 * locations of anagrams of W.
 * 
 * | An anagram is a word or phrase formed by rearranging the letters of a
 * different word or |
 * | phrase, typically using all the original letters exactly once. |
 * 
 * Example:
 * W = 'ab'
 * S = 'abxaba'
 * 
 * return [0, 3, 4]
 */
public class FindAnagramIndices {
    public static boolean isAnagram(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }

        char[] a1 = s1.toCharArray();
        char[] a2 = s2.toCharArray();
        Arrays.sort(a1);
        Arrays.sort(a2);

        return Arrays.equals(a1, a2);
    }

    // Brute force, go over each word-sized window in S and check if it forms an anagram
    // time complexity: O(w * s)
    public static List<Integer> anagramIndicies1(String word, String s) {
        List<Integer> result = new ArrayList<>();

        int wordLen = word.length();
        int sLen = s.length();

        for (int i = 0; i < sLen - wordLen + 1; i++) {
            var window = s.substring(i, i + wordLen);
            if (isAnagram(window, word)) {
                result.add(i);
            }
        }

        return result;
    }

    public static void deleteIfZero(Map<Character, Integer> dict, char ch) {
        if (dict.get(ch) == 0) {
            dict.remove(ch);
        }
    }

    public static void printHash(Map<Character, Integer> h) {
        System.out.println("--- Print start ---");
        for (var name: h.keySet()) {
            String key = name.toString();
            String value = h.get(name).toString();
            System.out.println(key + " " + value);
        }
        System.out.println("--- Print end ---");
    }

    // at each step we are recomputing the frequency counts of the entire window
    // when only a small part of it actually is updated
    //
    // Optimize:
    // - first, we make a frequency dict of both the initial window and the target word
    // - as we move along the string, we increment the count of each new character and decrement the count of the old
    // - if at any point there is no difference between the frequencies of the target word and the current 
    //   window, we add the corresponding starting index to our result
    public static List<Integer> anagramIndicies2(String word, String s) {
        List<Integer> result = new ArrayList<>();
        int wordLen = word.length();
        int sLen = s.length();

        Map<Character, Integer> freq = new HashMap<>();
        for (var ch : word.toCharArray()) {
            freq.put(ch, freq.getOrDefault(ch, 0) + 1);
        }

        for (var ch : s.substring(0, wordLen).toCharArray()) {
            if (freq.containsKey(ch)) {
                freq.put(ch, freq.get(ch) - 1);
                deleteIfZero(freq, ch);
            }
        }

        if (freq.isEmpty()) {
            result.add(0);
        }

        for (int i = wordLen; i < sLen; i++) {
            var startChar = s.charAt(i - wordLen);
            var endChar = s.charAt(i);

            // increment count of the new char
            freq.put(startChar, freq.getOrDefault(startChar, 0) + 1);
            deleteIfZero(freq, startChar);

            // decrement count of the old char
            freq.put(endChar, freq.getOrDefault(endChar, 0) - 1);
            deleteIfZero(freq, endChar);

            if (freq.isEmpty()) {
                result.add(i - wordLen + 1);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println("---- START ----");
        var result = FindAnagramIndices.anagramIndicies2("ab", "abxaba");
        
        for (int i : result) {
            System.out.println(i);
        }

        System.out.println("---- END ----");
    }
}
