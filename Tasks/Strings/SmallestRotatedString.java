package Tasks.Strings;

import java.util.Arrays;

/**
 * You are given a string of length N and an integer K. The string can be manipulated by taking one of the
 * first K letters and moving it to the end of the string.
 * 
 * Determine lexicographically smallest string that can be created after an unlimited number of moves.
 * 
 * Example:
 * 
 * str = "daily"
 * k = 1
 * result = "ailyd"
 * 
 * string | transformation, k = 1
 * -------+---------------------
 * xxabxx | move all x to end, one at a time
 * abxxxx | move b to end
 * axxxxb | move a to end
 * xxxxba | move x to end, one at a time, until reaching initial position
 * xxbaxx | swapped
 * 
 */
public class SmallestRotatedString {
    public String bubbleSwap(String str, int i, int j) {
        var len = str.length();
        // rotate so that i is at the beginning
        while (i > 0) {
            str = str.substring(1) + str.substring(0, 1);
            i--;
        }

        // move the first two letter to the end in reversed order
        str = str.substring(0, 1) + str.substring(2) + str.substring(1, 2);
        str = str.substring(1) + str.substring(0, 1);

        // rotate back to the initial position
        while (j + 1 < len) {
            str = str.substring(1) + str.substring(0, 1);
            j += 1;
        }

        return str;
    }

    public static String sortString(String inputString) {
        char tempArray[] = inputString.toCharArray();
        Arrays.sort(tempArray);
        return new String(tempArray);
    }

    public String getBestWord(String str, int k) {
        var len = str.length();

        if (k == 1) {
            var best = str;

            for (int i = 1; i < len; i++) {
                if (best.compareTo(str.substring(i) + str.substring(0, i)) > 0 ) {
                    best = str.substring(i) + str.substring(0, i);
                }
            }

            return best;
        }

        return sortString(str);
    }

    public static void main(String[] args) {
        var s = new SmallestRotatedString();
        var result = s.getBestWord("daily", 1);

        System.out.println(result);
    }
}
