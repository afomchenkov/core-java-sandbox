package Tasks.Strings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Subsequence {
    class Solution1 {
        String source, target;
        Integer leftBound, rightBound;

        private boolean rec_isSubsequence(int leftIndex, int rightIndex) {
            if (leftIndex == leftBound)
                return true;
            if (rightIndex == rightBound)
                return false;

            // consume both strings or just the target string
            if (source.charAt(leftIndex) == target.charAt(rightIndex))
                ++leftIndex;
            ++rightIndex;

            return rec_isSubsequence(leftIndex, rightIndex);
        }

        public boolean isSubsequence(String s, String t) {
            this.source = s;
            this.target = t;
            this.leftBound = s.length();
            this.rightBound = t.length();

            return rec_isSubsequence(0, 0);
        }
    }

    class Solution2 {

        public boolean isSubsequence(String s, String t) {
            Integer leftBound = s.length(), rightBound = t.length();
            Integer pLeft = 0, pRight = 0;

            while (pLeft < leftBound && pRight < rightBound) {
                // move both pointers or just the right pointer
                if (s.charAt(pLeft) == t.charAt(pRight)) {
                    pLeft += 1;
                }
                pRight += 1;
            }
            return pLeft == leftBound;
        }
    }

    class Solution3 {

        public boolean isSubsequence(String s, String t) {

            // precomputation, build the hashmap out of the target string
            HashMap<Character, List<Integer>> letterIndicesTable = new HashMap<>();
            for (int i = 0; i < t.length(); ++i) {
                if (letterIndicesTable.containsKey(t.charAt(i)))
                    letterIndicesTable.get(t.charAt(i)).add(i);
                else {
                    ArrayList<Integer> indices = new ArrayList<Integer>();
                    indices.add(i);
                    letterIndicesTable.put(t.charAt(i), indices);
                }
            }

            Integer currMatchIndex = -1;
            for (char letter : s.toCharArray()) {
                if (!letterIndicesTable.containsKey(letter))
                    return false; // no match, early exit

                boolean isMatched = false;
                // greedy match with linear search
                for (Integer matchIndex : letterIndicesTable.get(letter)) {
                    if (currMatchIndex < matchIndex) {
                        currMatchIndex = matchIndex;
                        isMatched = true;
                        break;
                    }
                }

                if (!isMatched)
                    return false;
            }

            // consume all characters in the source string
            return true;
        }
    }

    class Solution4 {

        public boolean isSubsequence(String s, String t) {

            Integer sourceLen = s.length(), targetLen = t.length();
            // the source string is empty
            if (sourceLen == 0)
                return true;

            int[][] dp = new int[sourceLen + 1][targetLen + 1];
            // DP calculation, we fill the matrix column by column, bottom up
            for (int col = 1; col <= targetLen; ++col) {
                for (int row = 1; row <= sourceLen; ++row) {
                    if (s.charAt(row - 1) == t.charAt(col - 1))
                        // find another match
                        dp[row][col] = dp[row - 1][col - 1] + 1;
                    else
                        // retrieve the maximal result from previous prefixes
                        dp[row][col] = Math.max(dp[row][col - 1], dp[row - 1][col]);
                }
                // check if we can consume the entire source string,
                // with the current prefix of the target string.
                if (dp[sourceLen][col] == sourceLen)
                    return true;
            }

            // matching failure
            return false;
        }
    }

    public static void main(String[] args) {

    }
}
