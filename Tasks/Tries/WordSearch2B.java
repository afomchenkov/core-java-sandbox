package Tasks.Tries;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WordSearch2B {
    static class TrieNode {
        HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();
        String word = null;

        public TrieNode() {
        }
    }

    static class Solution {
        char[][] _board = null;
        ArrayList<String> _result = new ArrayList<String>();

        public List<String> findWords(char[][] board, String[] words) {
            TrieNode root = new TrieNode();
            for (String word : words) {
                TrieNode node = root;

                for (Character letter : word.toCharArray()) {
                    if (node.children.containsKey(letter)) {
                        node = node.children.get(letter);
                    } else {
                        TrieNode newNode = new TrieNode();
                        node.children.put(letter, newNode);
                        node = newNode;
                    }
                }
                node.word = word;
            }

            _board = board;
            // Backtracking starting for each cell in the board
            for (int row = 0; row < board.length; ++row) {
                for (int col = 0; col < board[row].length; ++col) {
                    if (root.children.containsKey(board[row][col])) {
                        backtracking(row, col, root);
                    }
                }
            }

            return _result;
        }

        private void backtracking(int row, int col, TrieNode parent) {
            Character letter = _board[row][col];
            TrieNode currNode = parent.children.get(letter);

            if (currNode.word != null) {
                _result.add(currNode.word);
                currNode.word = null;
            }

            _board[row][col] = '#';

            int[] rowOffset = { -1, 0, 1, 0 };
            int[] colOffset = { 0, 1, 0, -1 };
            for (int i = 0; i < 4; ++i) {
                int newRow = row + rowOffset[i];
                int newCol = col + colOffset[i];
                if (newRow < 0 ||
                        newRow >= _board.length ||
                        newCol < 0 ||
                        newCol >= _board[0].length) {
                    continue;
                }
                if (currNode.children.containsKey(_board[newRow][newCol])) {
                    backtracking(newRow, newCol, currNode);
                }
            }

            _board[row][col] = letter;

            if (currNode.children.isEmpty()) {
                parent.children.remove(letter);
            }
        }
    }
}
