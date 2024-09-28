package Tasks.Strings;

import java.util.Arrays;

/**
 * Levenshtein distance is a measure of the similarity between two strings,
 * which takes into account
 * the number of insertion, deletion and substitution operations needed to
 * transform one string into the other.
 * 
 * Operations in Levenshtein distance are:
 * - Insertion: Adding a character to string A.
 * - Deletion: Removing a character from string A.
 * - Replacement: Replacing a character in string A with another character.
 * 
 * 
 * Example:
 * 
 * Determine the min operations required to convert strA to strB.
 * 
 * strA = 'kitten'
 * strB = 'sitting'
 * 
 * kitten -> sitten (substitute 's' for 'k')
 * sitten -> sittin (substitute 'i' for ???)
 * sittin -> sitting (insert 'g' at the end)
 * 
 * The levenstein distance = 3 (operations)
 * 
 * Applications of Levenshtein distance:
 * - Autocorrect Algorithms: Text editors and messaging applications use the
 * Levenshtein distance in
 * their autocorrect features such as gboard, swift keyboard, etc.
 * - Data cleaning: It is widely used in the process of data cleaning and
 * normalization task to reduce
 * redundancy and identify similar records in the data mining process.
 * - Data clustering and classification: To identify similar records and cluster
 * them is clustering while
 * identifying similar records and providing them with class labels is
 * classification
 * 
 * Relationship with other edit distance metrics:
 * - Damerau-Levenshtein distance: It is similar to the Levenshtein distance,
 * but it just also allows
 * transpositions as an additional operation making it 4 operations.
 * - Hamming distance: It can only be applied to strings of equal length, it is
 * used measures the
 * number of positions at which the corresponding characters are different.
 * 
 * 
 */
public class LevensteinDistance {
    // > Levenshtein distance using a recursive approach
    //
    // Time complexity: O(3^(m+n))
    // Auxiliary complexity: O(m+n)
    public static int levenshteinRecursive(String str1, String str2, int m, int n) {
        if (m == 0) {
            return n;
        }

        if (n == 0) {
            return m;
        }

        if (str1.charAt(m - 1) == str2.charAt(n - 1)) {
            return levenshteinRecursive(str1, str2, m - 1, n - 1);
        }

        return 1 + Math.min(
                // insert
                levenshteinRecursive(str1, str2, m, n - 1),
                Math.min(
                        // remove
                        levenshteinRecursive(str1, str2, m - 1, n),
                        // replace
                        levenshteinRecursive(str1, str2, m - 1, n - 1)));
    }

    // > Levenshtein distance using Iterative with the full matrix approach
    //
    // The iterative technique with a full matrix uses a 2D matrix to hold the
    // intermediate results of
    // the Levenshtein distance calculation. It begins with empty strings and
    // iteratively fills the
    // matrix row by row. It computes the minimum cost of insertions, deletions, and
    // replacements based
    // on the characters of both strings.
    //
    // Time complexity: O(m*n)
    // Auxiliary complexity: O(m*n)
    public static int levenshteinFullMatrix(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();

        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }

        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }

        // Fill in the DP array using the recurrence relation
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    // Characters match, no operation needed
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // Characters don't match, consider the minimum of insert, remove, and replace
                    dp[i][j] = 1 + Math.min(
                            // Insert
                            dp[i][j - 1],
                            Math.min(
                                    // Remove
                                    dp[i - 1][j],
                                    // Replace
                                    dp[i - 1][j - 1]));
                }
            }
        }

        // Result is stored in the bottom-right cell of the DP array
        return dp[m][n];
    }


    // > Levenshtein distance using Iterative with two matrix rows approach
    //
    // By simply storing two rows of the matrix at a time, the iterative technique with two matrix 
    // rows reduces space complexity. It iterates through the strings row by row, storing the current 
    // and past calculations in two rows.
    //
    // Time complexity: O(m*n)
    // Auxiliary Space: O(n)
    public static int levenshteinTwoMatrixRows(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();
 
        // Initializing two arrays to store the current and previous row values
        int[] prevRow = new int[n + 1];
        int[] currRow = new int[n + 1];
 
        // Initializing the first row with increasing integers
        for (int j = 0; j <= n; j++) {
            prevRow[j] = j;
        }
 
        // Looping through each character of str1
        for (int i = 1; i <= m; i++) {
            // Initializing the first element of the current row with the row number
            currRow[0] = i;
 
            // Looping through each character of str2
            for (int j = 1; j <= n; j++) {
                // If characters are equal, no operation needed, take the diagonal value
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    currRow[j] = prevRow[j - 1];
                } else {
                    // If characters are not equal, find the minimum value of insert, delete, or replace
                    currRow[j] = 1 + Math.min(currRow[j - 1], Math.min(prevRow[j], prevRow[j - 1]));
                }
            }
 
            // Update prevRow with currRow values
            prevRow = Arrays.copyOf(currRow, currRow.length);
        }
 
        // Return the final Levenshtein distance stored at the bottom-right corner of the matrix
        return currRow[n];
    }
 

    public static void main(String[] args) {
        String str1 = "kitten";
        String str2 = "sitting";

        int distance = levenshteinRecursive(str1, str2, str1.length(), str2.length());
        System.out.println("Levenshtein Distance: " + distance);
    }
}
