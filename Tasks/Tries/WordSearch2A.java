package Tasks.Tries;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

record Letter(int i, int j) {
}

public class WordSearch2A {
    // Expensive solution, TLE expected
    static class Solution {
        private boolean dfs(
                int h,
                int w,
                int i,
                int j,
                HashSet<Letter> visited,
                char[][] board,
                HashSet<String> result,
                String curr,
                String word) {
            if (i < 0 || j < 0 || i >= h || j >= w) {
                return false;
            }

            curr += board[i][j];
            if (!word.startsWith(curr)) {
                return false;
            }

            var letter = new Letter(i, j);
            if (visited.contains(letter)) {
                return false;
            }
            visited.add(letter);
            if (word.equals(curr)) {
                result.add(word);
                return true;
            }

            var a = dfs(h, w, i + 1, j, visited, board, result, curr, word);
            var b = dfs(h, w, i, j + 1, visited, board, result, curr, word);
            var c = dfs(h, w, i - 1, j, visited, board, result, curr, word);
            var d = dfs(h, w, i, j - 1, visited, board, result, curr, word);

            // backtrack and remove a letter from the path
            visited.remove(letter);

            return a || b || c || d;
        }

        private void search(String word, char[][] board, HashSet<String> result) {
            int height = board.length;
            int width = board[0].length;

            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    char ch1 = board[i][j];
                    char ch2 = word.charAt(0);

                    if (ch1 == ch2) {
                        var found = dfs(height, width, i, j, new HashSet<>(), board, result, "", word);
                        if (found) {
                            return;
                        }
                    }
                }
            }
        }

        public List<String> findWords(char[][] board, String[] words) {
            HashSet<String> result = new HashSet<>();

            for (String word : words) {
                search(word, board, result);
            }

            return new ArrayList<>(result);
        }
    }

    public static void main(String[] args) {
        var sol = new Solution();
        var grid = new char[][] {
                // new char[] { 'o', 'a', 'a', 'n' },
                // new char[] { 'e', 't', 'a', 'e' },
                // new char[] { 'i', 'h', 'k', 'r' },
                // new char[] { 'i', 'f', 'l', 'v' },
                //
                new char[] { 'a','b','c','e' },
                new char[] { 'x','x','c','d' },
                new char[] { 'x','x','b','a' }
        };
        // new String[] { "oath", "pea", "eat", "rain" }
        var result = sol.findWords(grid, new String[] { "abc","abcd" });
        System.out.println("---- ---- ----");
        for (var row : result) {
            System.out.println(row);
        }
    }
}
