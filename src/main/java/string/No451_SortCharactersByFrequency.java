package string;

import scala.math.Ordering;

import java.util.HashMap;
import java.util.Map;

/**
 * 451. 根据字符出现频率排序
 * 给定一个字符串，请将字符串里的字符按照出现的频率降序排列。
 * 示例 1:
 * 输入:
 * "tree"
 * 输出:
 * "eert"
 * 解释:
 * 'e'出现两次，'r'和't'都只出现一次。
 * 因此'e'必须出现在'r'和't'之前。此外，"eetr"也是一个有效的答案。
 * 示例 2:
 * 输入:
 * "cccaaa"
 * 输出:
 * "cccaaa"
 * 解释:
 * 'c'和'a'都出现三次。此外，"aaaccc"也是有效的答案。
 * 注意"cacaca"是不正确的，因为相同的字母必须放在一起。
 * 示例 3:
 * 输入:
 * "Aabb"
 * 输出:
 * "bbAa"
 * 解释:
 * 此外，"bbaA"也是一个有效的答案，但"Aabb"是不正确的。
 * 注意'A'和'a'被认为是两种不同的字符。
 * @author: Patrick Star
 * @time: 2021/7/3 12:49
 */
public class No451_SortCharactersByFrequency {
    public static void main(String[] args) {
        No451_SortCharactersByFrequency t = new No451_SortCharactersByFrequency();
        String s = "acacac";
        System.out.println(t.frequencySort(s));
        char[] chars = s.toCharArray();
        t.swap(chars, 0, 1);
        System.out.println(new String(chars));
    }


    public String frequencySort(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int len = s.length(), maxFreq = 0;
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            int frequency = map.getOrDefault(c, 0) + 1;
            map.put(c, frequency);
            maxFreq = Math.max(maxFreq, frequency);
        }
        StringBuilder[] buckets = new StringBuilder[maxFreq + 1];
        for (int i = 0; i <= maxFreq; i++) {
            buckets[i] = new StringBuilder();
        }
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            char c = entry.getKey();
            int frequency = entry.getValue();
            buckets[frequency].append(c);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = maxFreq; i > 0; i--) {
            StringBuilder bucket = buckets[i];
            for (int j = 0; j < bucket.length(); j++) {
                char curChar=bucket.charAt(j);
                for (int k = 0; k < i; k++) {
                    sb.append(curChar);
                }
            }
        }
        return sb.toString();
    }

    public String frequencySort2(String s) {
        char[] chars = s.toCharArray();
        quickSort(chars, 0, chars.length - 1);
        return new String(chars);
    }

    private void quickSort(char[] chars, int start, int end) {
        if (start >= end) return;
        int i = start + 1, j = end;
        while (i <= j) {
            if (chars[i] > chars[start]) {
                swap(chars, i, j);
                j--;
            } else i++;
        }
        i--;
        swap(chars, start, i);
        quickSort(chars, start, i - 1);
        quickSort(chars, i + 1, end);
    }

    private void swap(char[] chars, int i, int j) {
        char tmp = chars[i];
        chars[i] = chars[j];
        chars[j] = tmp;
    }
}
