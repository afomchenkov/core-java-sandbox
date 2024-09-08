package Tasks.Tries;

import java.util.*;

// Given a list of words (without duplicates), return all concatenated  words in the given list of words
//
// A concatenated word is defined as a string that is comprised entierly of at least
// two shorter words in the given array
//
// Input ["cat", "cats", "catsdogscats", "dog", "dogcatsdog", "hippopotamuses", "rat", "ratcatdogcat"]
// Output ["catsdogcats", "dogcatsdog", ratcatdogcat]
//
// "catsdogcats"  -> cats + dog + cats
// "dogcatsdog"   -> dog + cats + dog
// "ratcatdogcat" -> rat + cat + dog + cat

public class ConcatenatedWords {
    public static List<String> findAllConcatenatedWordsInDict(String[] words) {
        List<String> result = new ArrayList<>();
        Set<String> preWords = new HashSet<>();

        Arrays.sort(words,
            new Comparator<String>() {
                public int compare(String s1, String s2) {
                    return s1.length() - s2.length();
                }
            }
        );

        for (int i = 0; i < words.length; ++i) {
            if (canForm(words[i], preWords)) {
                result.add(words[i]);
            }
            preWords.add(words[i]);
        }

        return result;
    }

    private static boolean canForm(String word, Set<String> dict) {
        if (dict.isEmpty()) {
            return false;
        }

        boolean[] dp = new boolean[word.length() + 1];
        dp[0] = true;

        for (int i = 1; i <= word.length(); ++i) {
            for (int j = 0; j < i; ++j) {
                if (!dp[j]) {
                    continue;
                }
                if (dict.contains(word.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[word.length()];
    }
}
