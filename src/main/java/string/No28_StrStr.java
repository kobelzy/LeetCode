package string;

/**
 * 28. 实现 strStr()
 * 实现 strStr() 函数。
 * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
 * 示例 1:
 * 输入: haystack = "hello", needle = "ll"
 * 输出: 2
 * 示例 2:
 *
 * 输入: haystack = "aaaaa", needle = "bba"
 * 输出: -1
 * 说明:
 *
 * 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
 *
 * 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。
 */
public class No28_StrStr {
    /**
     * 双指针
     *
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr(String haystack, String needle) {
        int hayIndex = 0;
        char needleHead = needle.charAt(0);
        while (hayIndex < haystack.length()) {
            if (haystack.charAt(hayIndex) == needleHead) {
                int needleIndex = 0;
                int i=hayIndex;
                while (i < haystack.length() && i < needle.length()) {
                    if(needleIndex==needle.length()-1) return hayIndex;
                    else if(haystack.charAt(i)!=needle.charAt(needleIndex)){
                        break;
                    }
                    i++;
                    needleIndex++;
                }
                hayIndex=0;
            }
            hayIndex++;
        }
        return -1;
    }

    /**
     * KMP算法 todo
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr2(String haystack, String needle) {
    return -1;
    }
    }
