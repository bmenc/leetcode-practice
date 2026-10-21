/*
    LeetCode 2514 - Hard - Count Anagrams

    You are given a string s containing one or more words. Every consecutive pair of words is separated by a single space ' '.

    A string t is an anagram of string s if the ith word of t is a permutation of the ith word of s.

    For example, "acb dfe" is an anagram of "abc def", but "def cab" and "adc bef" are not.
    Return the number of distinct anagrams of s. Since the answer may be very large, return it modulo 109 + 7.

    Example 1:

        Input: s = "too hot"
        Output: 18
        Explanation: Some of the anagrams of the given string are "too hot", "oot hot", "oto toh", "too toh", and "too oht".

    Example 2:

        Input: s = "aa"
        Output: 1
        Explanation: There is only one anagram possible for the given string.
    

    Constraints:

        1 <= s.length <= 105
        s consists of lowercase English letters and spaces ' '.
        There is single space between consecutive words.
*/

import java.util.HashMap;
import java.util.Map;

public class CountAnagrams {
    private static final int MOD = 1_000_000_007;

    public static int countAnagrams(String s) {
        String[] words = s.split(" ");
        long totalAnagrams = 1;

        for (String word : words) {
            long wordWays = getWordAnagramWays(word);
            totalAnagrams = (totalAnagrams * wordWays) % MOD;
        }

        return (int) totalAnagrams;
    }

    private static long getWordAnagramWays(String word) {
        int n = word.length();
        long numerator = factorial(n);
        long denominator = 1;

        Map<Character, Integer> counts = new HashMap();
        for (char c : word.toCharArray()) {
            counts.put(c, counts.getOrDefault(c, 0) + 1);
        }

        for (int count : counts.values()) {
            denominator = (denominator * factorial(count)) % MOD;
        }

        long inverseDenominator = power(denominator, MOD - 2);
        return (numerator * inverseDenominator) % MOD;
    }

    private static long factorial(int n) {
        long res = 1;
        for (int i = 2; i <= n; i++) {
            res = (res * i) % MOD;
        }
        return res;
    }

    private static long power(long base, int exp) {
        long res = 1;
        base %= MOD;
        while (exp > 0) {
            if ((exp & 1) == 1)
                res = (res * base) % MOD;
            base = (base * base) % MOD;
            exp >>= 1;
        }
        return res;
    }

    public static void main(String args[]) {
        System.out.println(countAnagrams("too hot"));
        System.out.println(countAnagrams("aa"));
    }
}
