package Tasks.Strings;

/**
 * Find the length of the longest substring T in a given string where every
 * character in T appears no less than K times.
 * 
 * Example:
 * s = "aaabb"
 * k = 3
 * output = 3 -> "aaa": a repeated 3 times
 * 
 * s = "ababbc"
 * k = 2
 * output = 5 -> "ababb": a repeated 2 times, b is repeated 3 times
 * 
 */
public class LongestSubstrWithAtLeastKRepeats {
    public int longestSubstring(String s, int k) {
        char[] str = s.toCharArray();
        return traverse(str, 0, s.length(), k);
    }

    private int traverse(char[] str, int start, int end, int k) {
        if (end - start < k) {
            return 0;
        }

        // build the letters counter for a given substring
        int[] count = new int[26];
        for (int i = start; i < end; i++) {
            int idx = str[i] - 'a';
            count[idx]++;
        }

        for (int i = 0; i < 26; i++) {
            // count[i] = 0  ->  i + 'a' does not exist in string, we skip it
            if (count[i] < k && count[i] > 0) {
                for (int j = start; j < end; j++) {
                    if (str[j] == i + 'a') {
                        int left = traverse(str, start, j, k);
                        int right = traverse(str, j + 1, end, k);

                        return Math.max(left, right);
                    }
                }
            }
        }

        return end - start;
    }

    public static void main(String[] args) {
        var sol = new LongestSubstrWithAtLeastKRepeats();
        var res = sol.longestSubstring("ababb", 2);
        System.out.println(res);
    }
}
