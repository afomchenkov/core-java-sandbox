package Tasks.Tries;

// addWord("bad")
// addWord("dad")
// addWord("mad")
// search("pad") -> false
// search("bad") -> true
// search(".ad") -> true
// search("b..") -> true

public class WordDictionary {
    class TrieNode {
        public TrieNode[] children = new TrieNode[26];
        public String word = "";
    }

    private TrieNode root = new TrieNode();

    public void addWord(String word) {
        TrieNode node = root;

        for (char c : word.toCharArray()) {
            if (node.children[c - 'a'] == null) {
                node.children[c - 'a'] = new TrieNode();
            }
            node = node.children[c - 'a'];
        }

        node.word = word;
    }

    public boolean search(String word) {
        return match(word.toCharArray(), 0, root);
    }

    private boolean match(char[] chs, int k, TrieNode node) {
        if (k == chs.length) {
            return !node.word.equals("");
        }

        if (chs[k] == '.') {
            for (int i = 0; i < node.children.length; ++i) {
                if (node.children[i] != null) {
                    if (match(chs, k + 1, node.children[i])) {
                        return true;
                    }
                }
            }
        } else {
            if (node.children[chs[k] - 'a'] == null) {
                return false;
            }
            return match(chs, k + 1, node.children[chs[k] - 'a']);
        }

        return false;
    }
}
