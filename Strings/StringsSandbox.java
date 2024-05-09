package Strings;

public class StringsSandbox {

    // charAt(int index) - get char at index
    // equalsIgnoreCase(String anotherString) - compare strings ignore case
    // indexOf(String str) - return the index of the first occurrence of substring
    // length() - string length
    // replace(CharSequence target, CharSequence replacement) - replace occurrence of a specified sequence
    // substring(int begin, int end) - return substring between indexes
    // contains(CharSequence sequence) - check if the string contains specified substring
    // startsWith(String prefix) - check if string starts with prefix
    // endsWith(String suffix) - check if  string ends with suffix
    // isEmpty() - checks if string is empty
    // join(CharSequence delimiter, CharSequence... elements) - join elements with delimiter
    // subSequence(int begin, int end) - returns a new char sequence that is a subsequence
    // hashCode() - return the hash code of the string, essential for efficient storage and retrieval in hash DS
    // concat(String str) - appends the specified string to the end of the string, same as + operator
    

    public static void main(String[] args) {
        
        
        String[] words = { "Java", "JavaScript", "Python", "Haskell" };
        String joinedString = String.join(" ", words);

        System.out.println(joinedString);

        var subSequence = "This is a random test text".subSequence(10, 16);
        System.out.println(subSequence);

        
    }
}
