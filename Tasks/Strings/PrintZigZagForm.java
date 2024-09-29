package Tasks.Strings;

import java.util.*;

/**
 * Given a string and a number of lines k, print a string in zigzag form.
 * In zigzag charcters are printed out diagonally, from top left to bottom
 * right until reaching Kth line, then back up to top right, and so on.
 * 
 * Example:
 * str = "thisisazigzag"
 * k = 4
 * 
 * print:
 * 1. t      a     g
 * 2.  h   s  z   a
 * 3.   i i    i z
 * 4.    s      g
 * 
 * go one line at a time, figure out what that line should be, print it, space complexity O(n), time complexity O(k * n)
 */
public class PrintZigZagForm {
    public int getSpaces(int row, boolean desc, int k) {
        int maxSpaces = (k - 1) * 2 - 1;
        int spaces = 0;

        if (desc) {
            spaces = maxSpaces - row * 2;
        } else {
            spaces = maxSpaces - (k - 1 - row) * 2;
        }

        return spaces;
    }

    public boolean isDescending(int index, int k) {
        return index % (2 * (k - 1)) < k - 1;
    }

    public void zigzag(String sentence, int k) {
        int len = sentence.length();

        for (int row = 0; row < k; row++) {
            int i = row;

            char[] lineCharArray = new char[len];
            Arrays.fill(lineCharArray, ' ');
            
            while (i < len) {
                lineCharArray[i] = sentence.charAt(i);
                var desc = isDescending(i, k);
                var spaces = getSpaces(row, desc, k);
                i += spaces + 1;
            }

            System.out.println(new String(lineCharArray));
        }
    }

    public List<String> zigzagConversion(String str, int k) {
        var len = str.length();
        String[] sb = new String[k + 1];
        Arrays.fill(sb, new String());

        var i = 0; // poiner to fill the col
        while (i < len) {
            // vertically down
            for (int idx = 0; idx < k && i < len; idx++) {
                var down = str.charAt(i++);
                sb[idx] = sb[idx].concat(String.valueOf(down));
            }

            // obliquely up
            for (int idx = k - 2; idx >= 1 && i < len; idx--) {
                var up = str.charAt(i++);
                sb[idx] = sb[idx].concat(String.valueOf(up));
            }
        }

        return Arrays.asList(sb);
    }

    public static void main(String[] args) {
        var s = new PrintZigZagForm();
        s.zigzag("thisisazigzag", 4);

        // str = "PAYPALISHIRING"
        // k = 3
        var result = s.zigzagConversion("ABCDEFGHIJKL", 4);
        // 1. A     G
        // 2. B   F H   L
        // 3. C E   I K
        // 4. D     J
        for (var str : result) {
            System.out.println(str);
        }
    }
}
