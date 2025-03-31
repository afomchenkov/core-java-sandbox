package Tasks.Tries;

import java.util.ArrayList;
import java.util.List;

public class Trie {
    List<Trie> children = new ArrayList<>();
    Character node = null;
    Boolean isWord = false;

    public Trie() {}
    public Trie(Character ch) {
        node = ch;
    }

    private int findChildChar(char ch, List<Trie> chlds) {
        for (int i = 0; i < chlds.size(); i++) {
            var child = chlds.get(i);

            if (child.node == ch) {
                return i;
            }
        }
        return -1;
    }
    
    public void insert(String word) {
        List<Trie> _children = children;

        Trie node = new Trie();
        for (var ch : word.toCharArray()) {
            int idx = findChildChar(ch, _children);

            if (idx == -1) {
                node = new Trie(ch);
                _children.add(node);
                _children = node.children;
                continue;
            }

            node = _children.get(idx);
            _children = node.children;
        }

        node.isWord = true;
    }
    
    public boolean search(String word) {
        List<Trie> _children = children;

        Trie lastNode = new Trie();
        for (var ch : word.toCharArray()) {
            int idx = findChildChar(ch, _children);

            if (idx == -1) {
                return false;
            }
            
            lastNode = _children.get(idx);
            _children = lastNode.children;
        }

        return lastNode.isWord;
    }
    
    public boolean startsWith(String prefix) {
        List<Trie> _children = children;

        for (var ch : prefix.toCharArray()) {
            int idx = findChildChar(ch, _children);

            if (idx == -1) {
                return false;
            }
            
            _children = _children.get(idx).children;
        }

        return true;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        trie.insert("app");
        trie.insert("coach");
        trie.insert("letter");
        
        System.out.println(trie.search("apple"));
        System.out.println(trie.search("apPle"));
        System.out.println(trie.search("letter"));
    }
}
