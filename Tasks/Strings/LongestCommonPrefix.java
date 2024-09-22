package Tasks.Strings;

import java.util.*;

class Trie {
    boolean isLeaf = false;
    Map<Character, Trie> letter = new HashMap<>();
}

public class LongestCommonPrefix {

    // brute force solution
    static String findLongestCommonPrefix(List<String> items) {
        // sort and take shortest string A as assumed longest prefix
        // iterate over the rest of the strings unless A is a prefix
        // slice A by one if not a prefix
        items.sort(Comparator.comparingInt(String::length));
        String prefix = items.get(0);

        for (int i = 1; i < items.size(); ++i) {
            var str = items.get(i);

            if (str.startsWith(prefix)) {
                continue;
            }

            while (prefix.length() > 0 && !str.startsWith(prefix)) {
                prefix = prefix.substring(0, prefix.length() - 1);
            }

            if (prefix.length() == 0) {
                break;
            }
        }

        return prefix;
    }

    static void insertTrie(Trie root, String word) {
        for (var ch : word.toCharArray()) {
            if (!root.letter.containsKey(ch)) {
                root.letter.put(ch, new Trie());
            }
            root = root.letter.get(ch);
        }

        // set leaf mark
        root.isLeaf = true;
    }

    static void printTrie(Trie root, String word) {
        if (root.isLeaf) {
            System.out.println(word);
            System.out.println("----");
        }

        for (var it : root.letter.entrySet()) {
            var ch = it.getKey();
            var trie = it.getValue();
            printTrie(trie, word + ch);
        }
        
    }

    // use Trie DS
    static String findLongestCommonPrefixTrie(List<String> items) {
        Trie root = new Trie();

        for (var word : items) {
            insertTrie(root, word);
        }
        
        printTrie(root, "");

        String lcp = "";
        Trie curr = root;
        // if char has more than one child or isLeaf, we should stop
        // and this will be a longest common prefix
        while (curr != null && !curr.isLeaf && curr.letter.size() == 1) {
            var it = curr.letter.entrySet().iterator().next();

            lcp += it.getKey();
            curr = it.getValue();
        }

        return lcp;
    }

    public static void main(String[] args) {

        List<String> items = Arrays.asList("code", "coder", "coding", "codable", "codec", "codecs", "coded",
                "codeless", "codependence", "codependency", "codependent",
                "codependents", "codes", "codesign", "codesigned", "codeveloped",
                "codeveloper", "codex", "codify", "codiscovered", "codrive");
        
        // var prefix = findLongestCommonPrefix(items);
        var prefix = findLongestCommonPrefixTrie(items);
        System.out.println("LCP " + prefix);
    }
}
