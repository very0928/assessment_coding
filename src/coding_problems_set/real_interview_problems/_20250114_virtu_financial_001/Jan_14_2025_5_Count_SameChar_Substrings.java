package coding_problems_set.real_interview_problems._20250114_virtu_financial_001;

/**
 * Given a string S return the number of ways
 * we can select a non-empty substring of S in which all the characters are same.
 *
 * - (len * (len + 1)) / 2 <-  arithmetic series sum - 等差数列求和
 * Like "zzzyz" return 8
 */
public class Jan_14_2025_5_Count_SameChar_Substrings {
    public static int countSameCharSubstrings(String s) {
        int count = 0;
        int n = s.length();

        // Traverse the string to find segments of identical characters
        int i = 0;
        while (i < n) {
            int j = i;
            // Find the length of the segment of identical characters starting at index i
            while (j < n && s.charAt(j) == s.charAt(i)) {
                j++;
            }
            int len = j - i;  // Length of the segment z z z zz zz zzz
            count += (len * (len + 1)) / 2;  // Add the number of substrings from this segment
            i = j;  // Move i to the next different character
        }

        return count;
    }

    public static void main(String[] args) {
        String s = "zzzyz";
        System.out.println(countSameCharSubstrings(s));  // Output: 8
    }
}

