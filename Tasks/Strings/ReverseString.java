package Tasks.Strings;

public class ReverseString {
    public static String reverseString(String s) {
        int len = s.length();

        if (len <= 1) {
            return s;
        }

        int mid = len / 2;
        String leftStr = s.substring(0, mid);
        String rightStr = s.substring(mid, len);

        return reverseString(rightStr) + reverseString(leftStr);
    }

    public static void main(String[] args) {
        var result = ReverseString.reverseString("this is a test string");
        System.out.println(result);
    }
}
