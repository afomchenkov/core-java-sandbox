package Tasks.Tries;

import java.util.*;

// Given words = ["oath", "pea", "eat", "rain"]
// 
// board = [
//      ['o', 'a', 'a', 'n'],
//      ['e', 't', 'a', 'e'],
//      ['i', 'h', 'k', 'r'],
//      ['i', 'f', 'l', 'v'],
// ]
//
// return ["eat", "oath"]

//        root
//     [o, p, e, r]
//      |  |  |  |
//      a  e  a  a
//     /   |  |   \
//    t    a  t    i
//    |             \
//    h              n

public class WordSearch {
    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        String word;
    }

    public TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();

        for (String w : words) {
            TrieNode p = root;

            for (char c : w.toCharArray()) {
                int i = c - 'a';
                if (p.children[i] == null) {
                    p.children[i] = new TrieNode();
                }
                p = p.children[i];
            }

            p.word = w;
        }
        
        return root;
    }

    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        TrieNode root = buildTrie(words);

        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board.length; ++j) {
                dfs(board, i, j, root, res);
            }
        }

        return res;
    }

    public void dfs(char[][] board, int i, int j, TrieNode p, List<String> res) {
        char c = board[i][j];

        if (c == '@' || p.children[c - 'a'] == null) {
            return;
        }

        // move to the next letter
        p = p.children[c - 'a'];
        if (p.word != null) {
            res.add(p.word);
            // dedupe found word
            p.word = null;
        }

        board[i][j] = '@';

        if (i > 0) {
            dfs(board, i - 1, j, p, res);
        }
        if (j > 0) {
            dfs(board, i, j - 1, p, res);
        }
        if (i < board.length - 1) {
            dfs(board, i + 1, j, p, res);
        }
        if (j < board[0].length - 1) {
            dfs(board, i, j + 1, p, res);
        }

        // backtrack if word not found
        board[i][j] = c;
    }
}
