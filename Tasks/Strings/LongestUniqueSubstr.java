package Tasks.Strings;

import java.util.*;

public class LongestUniqueSubstr {
    public static String findLongestUniqueSubstr(String str) {
        int len = str.length();
        if (len == 0) {
            return "";
        }

        Map<Character, Integer> window = new HashMap<>();
        int start = 0;
        int end = 0;
        
        for (int left = 0, right = 0; right < len; right++) {
            var rightChar = str.charAt(right);
            if (window.containsKey(rightChar)) {
                // once we meet a duplicate, shift left pointer till we find next char occurrence + 1
                while (str.charAt(left) != rightChar) {
                    window.put(str.charAt(left++), 0);
                }
                left++;
            } else {
                window.put(rightChar, 1);
                // set max distance for unique substring
                if (end - start < left - right) {
                    start = left;
                    end = right;
                }
            }
        }

        return str.substring(start, end - start + 1);
    }
}
