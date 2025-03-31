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

    private boolean _search(String word, List<WordSearchWildCard> _children) {
        WordSearchWildCard lastNode = new WordSearchWildCard();

        for (int i = 0; i < word.length(); i++) {
            var ch = word.charAt(i);
            if (ch == '.') {
                for (var node : _children) {
                    String sub = word.substring(i);
                    if (sub.length() == 1 && sub.charAt(0) =='.') {
                        return true;
                    }
                    if (_search(sub, node.children)) {
                        return true;
                    }
                }
            } else {
                int idx = findChildChar(ch, _children);

                if (idx == -1) {
                    return false;
                }
                
                lastNode = _children.get(idx);
                _children = lastNode.children;
            }
        }

        return lastNode.isWord;
    }
    
    public boolean search(String word) {
        return _search(word, children);
    }

    public static void main(String[] args) {
        WordSearchWildCard wordDictionary = new WordSearchWildCard();
        wordDictionary.addWord("bad");
        wordDictionary.addWord("dad");
        wordDictionary.addWord("mad");
        // System.out.println(wordDictionary.search("pad")); // return False
        // System.out.println(wordDictionary.search("bad")); // return True
        // System.out.println(wordDictionary.search(".ad")); // return True
        System.out.println(wordDictionary.search("b..")); // return True
    }
}
