/*
    LeetCode 541 - Easy - Reverse String II

    Given a string s and an integer k, reverse the first k characters for every 2k characters counting from the start of the string.

    If there are fewer than k characters left, reverse all of them. If there are less than 2k but greater than or equal to k characters, then reverse the first k characters and leave the other as original.

    Example 1:

        Input: s = "abcdefg", k = 2
        Output: "bacdfeg"

    Example 2:

        Input: s = "abcd", k = 2
        Output: "bacd"
    

    Constraints:

        1 <= s.length <= 104
        s consists of only lowercase English letters.
        1 <= k <= 104
*/

public class ReverseStringII {

    public static String reveseStr(String s, int k) {

        char[] chars = s.toCharArray();
        int n = chars.length;

        for (int i = 0; i < n; i += 2 * k) {
            int left = i;
            int right = Math.min(i + k - 1, n - 1);
            char temp = chars[left];

            while (left < right) {
                temp = chars[left];
                chars[left] = chars[right];
                chars[right] = temp;
                left ++;
                right --;
            }
        }

        return new String(chars);
    }

    public static void main(String[] args) {
        // String s = "bac";
        // String s = "bacdfeg";
        // String s = "bacd";
        String s = "cba";
        int k = 2;

        String res = reveseStr(s, k);
        System.out.println(res);
    }
}
