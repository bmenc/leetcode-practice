/*
    LeetCode 3485 - Hard - Longest Common Prefix of K Strings After Removal

    You are given an array of strings words and an integer k.

    For each index i in the range [0, words.length - 1], find the length of the longest common prefix among any k strings (selected at distinct indices) from the remaining array after removing the ith element.

    Return an array answer, where answer[i] is the answer for ith element. If removing the ith element leaves the array with fewer than k strings, answer[i] is 0.


    Example 1:

        Input: words = ["jump","run","run","jump","run"], k = 2

        Output: [3,4,4,3,4]

        Explanation:

        Removing index 0 ("jump"):
        words becomes: ["run", "run", "jump", "run"]. "run" occurs 3 times. Choosing any two gives the longest common prefix "run" (length 3).
        Removing index 1 ("run"):
        words becomes: ["jump", "run", "jump", "run"]. "jump" occurs twice. Choosing these two gives the longest common prefix "jump" (length 4).
        Removing index 2 ("run"):
        words becomes: ["jump", "run", "jump", "run"]. "jump" occurs twice. Choosing these two gives the longest common prefix "jump" (length 4).
        Removing index 3 ("jump"):
        words becomes: ["jump", "run", "run", "run"]. "run" occurs 3 times. Choosing any two gives the longest common prefix "run" (length 3).
        Removing index 4 ("run"):
        words becomes: ["jump", "run", "run", "jump"]. "jump" occurs twice. Choosing these two gives the longest common prefix "jump" (length 4).
    
    Example 2:

        Input: words = ["dog","racer","car"], k = 2

        Output: [0,0,0]

        Explanation:

        Removing any index results in an answer of 0.
        

    Constraints:

        1 <= k <= words.length <= 105
        1 <= words[i].length <= 104
        words[i] consists of lowercase English letters.
        The sum of words[i].length is smaller than or equal 105.
*/

import java.util.Arrays;

public class LongestCommonPrefixOfKStringsAfterRemoval {

    public static int[] longestCommonPrefix(String[] words, int k) {
        int n = words.length;
        int[] answer = new int[n];

        if (n - 1 < k) {
            return answer;
        }

        class WordItem implements Comparable<WordItem> {
            String str;
            int originalIndex;

            WordItem(String str, int originalIndex) {
                this.str = str;
                this.originalIndex = originalIndex;
            }

            @Override
            public int compareTo(WordItem other) {
                return this.str.compareTo(other.str);
            }
        }

        WordItem[] items = new WordItem[n];
        for (int i = 0; i < n; i++) {
            items[i] = new WordItem(words[i], i);
        }

        Arrays.sort(items);

        int[] lcp = new int[n - 1];
        for (int i = 0; i < n - 1; i++) {
            lcp[i] = getLCP(items[i].str, items[i + 1].str);
        }

        for (int i = 0; i < n; i++) {
            int maxLcp = 0;

            WordItem[] remaining = new WordItem[n - 1];
            int idx = 0;
            for (int j = 0; j < n; j++) {
                if (items[j].originalIndex != i) {
                    remaining[idx++] = items[j];
                }
            }

            for (int j = 0; j <= remaining.length - k; j++) {
                int currentLcp = getLCP(remaining[j].str, remaining[j + k - 1].str);
                maxLcp = Math.max(maxLcp, currentLcp);
            }

            answer[i] = maxLcp;
        }

        return answer;
    }

    private static int getLCP(String s1, String s2) {
        int minLen = Math.min(s1.length(), s2.length());
        for (int i = 0; i < minLen; i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                return i;
            }
        }
        return minLen;
    }

    public static void main(String args[]) {

        String[] words1 = { "jump", "run", "run", "jump", "run" };
        int k1 = 2;
        System.out.println(Arrays.toString(longestCommonPrefix(words1, k1)));
    }
}
