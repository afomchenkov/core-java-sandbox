package Tasks.Strings;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Given a string S and a list of words of the same length, find all starting indicies of substrings
 * in S that is a concatenation of each word in words exactly once and wihout any intervening characters.
 * 
 * Example:
 * s = "barfoothefoobarman"
 * words: ["foo", "bar"]
 * return [0,9] - order does not matter
 * 
 * "barfoo" - starts at 0
 * "foobar" - starts at 9
 * 
 * Solution:
 * - build a map for the words and their relative count in L
 * - traverse through S to check whether there is a match
 */
public class SubstringWithConcatenation {
    public static List<Integer> findSubstring(String S, String[] L) {
        List<Integer> res = new ArrayList<>();

        if (S == null || L == null || L.length == 0) {
            return res;
        }

        int wordLength = L[0].length();
        Map<String, Integer> map = new HashMap<>();
        for (String w : L) {
            map.put(w, map.containsKey(w) ? map.get(w) + 1 : 1);
        }

        // length of the string without concatenated substrings
        var length = S.length() - wordLength * L.length;
        for (int i = 0; i <= length; i++) {
            Map<String, Integer> copy = new HashMap<>(map);

            for (int j = 0; j < L.length; j++) {
                // next word
                var nextWordFrom = i + j * wordLength;
                var nextWordTo = nextWordFrom + wordLength;
                String str = S.substring(nextWordFrom, nextWordTo);

                // is in remaining words
                if (copy.containsKey(str)) {
                    int count = copy.get(str);
                    if (count == 1) {
                        copy.remove(str);
                    } else {
                        copy.put(str, count - 1);
                    }

                    // match
                    if (copy.isEmpty()) {
                        res.add(i);
                        break;
                    }
                } else {
                    // not in L
                    break;
                }
            }
        }

        return res;
    }

    public static void main(String[] args) {
        var S = "barfoothefoobarman";
        String[] L = { "foo", "bar" };
        var result = SubstringWithConcatenation.findSubstring(S, L);

        for (var i : result) {
            System.out.println(i);
        }
    }
}
