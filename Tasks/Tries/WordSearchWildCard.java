package Tasks.Tries;

import java.util.ArrayList;
import java.util.List;

public class WordSearchWildCard {
    List<WordSearchWildCard> children = new ArrayList<>();
    Character letter = null;
    Boolean isWord = false;

    public WordSearchWildCard() { }
    public WordSearchWildCard(Character ch) {
        letter = ch;
    }

    private int findChildChar(char ch, List<WordSearchWildCard> chlds) {
        for (int i = 0; i < chlds.size(); i++) {
            var child = chlds.get(i);

            if (child.letter == ch) {
                return i;
            }
        }
        return -1;
    }
    
    public void addWord(String word) {
        List<WordSearchWildCard> _children = children;

        WordSearchWildCard node = new WordSearchWildCard();
        for (var ch : word.toCharArray()) {
            int idx = findChildChar(ch, _children);

            if (idx == -1) {
                node = new WordSearchWildCard(ch);
                _children.add(node);
                _children = node.children;
                continue;
            }

            node = _children.get(idx);
            _children = node.children;
        }

        node.isWord = true;
    }

    private boolean _search(String word, int idx, List<WordSearchWildCard> _children) {
        var ch = word.charAt(idx);
        var charIndex = findChildChar(ch, _children);

        if (ch == '.') {
            for (var node : _children) {
                if (idx + 1 >= word.length()) {
                    if (node.isWord) {
                        return true;
                    }
                    continue;
                }
                if (_search(word, idx + 1, node.children)) {
                    return true;
                }
            }
        } else {
            if (charIndex >= 0) {
                var nextNode = _children.get(charIndex);
                if (idx + 1 < word.length()) {
                    return _search(word, idx + 1, nextNode.children);   
                }
                return nextNode.isWord;
            }
        }

        return false;
    }
    
    public boolean search(String word) {
        return _search(word, 0, children);
    }

    public static void main(String[] args) {
        WordSearchWildCard wordDictionary = new WordSearchWildCard();
        // wordDictionary.addWord("bad");
        // wordDictionary.addWord("dad");
        // wordDictionary.addWord("mad");
        // wordDictionary.addWord("a");
        // System.out.println(wordDictionary.search("pad")); // return False
        // System.out.println(wordDictionary.search("bad")); // return True
        // System.out.println(wordDictionary.search(".ad")); // return True
        // System.out.println(wordDictionary.search("b..")); // return True
        // System.out.println(wordDictionary.search(".a")); // return False
        wordDictionary.addWord("at");
        wordDictionary.addWord("and");
        wordDictionary.addWord("an");
        wordDictionary.addWord("add");
        
        System.out.println(wordDictionary.search("b."));
        System.out.println(wordDictionary.search("."));
    }
}
