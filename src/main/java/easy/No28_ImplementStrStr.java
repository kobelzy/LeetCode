package easy;

import java.util.Arrays;

/**
 * 28. 实现 strStr()
 * 实现 strStr() 函数。
 *
 * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
 *
 * 示例 1:
 *
 * 输入: haystack = "hello", needle = "ll"
 * 输出: 2
 * 示例 2:
 *
 * 输入: haystack = "aaaaa", needle = "bba"
 * 输出: -1
 * 说明:
 *
 * 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
 *
 * 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。
 */
public class No28_ImplementStrStr {
    public static void main(String[] args) {
        No28_ImplementStrStr test=new No28_ImplementStrStr();

        System.out.println(test.strStr("hello","ll"));
        System.out.println(test.strStr2("aaab","b"));
    }

    /**
     * 双指针判断
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr(String haystack, String needle) {
        int haystackLen=haystack.length();
        int needleLen=needle.length();
        if(needleLen==0) return 0;
        if(haystackLen<needleLen) return -1;

        char needleHead=needle.charAt(0);
        for(int i=0;i<haystackLen-needleLen+1;i++){
            if(haystack.charAt(i)==needleHead){
                for(int j=0;j<needleLen;j++){
                    if(haystack.charAt(i+j)==needle.charAt(j)){
                        if(j==needleLen-1) return i;
                    }else break;
                }
            }
        }
        return -1;
    }

    /**
     * Rabin Karp 复杂度为常数
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr2(String haystack, String needle) {
        int needleLen=needle.length();
        if(needleLen==0) return 0;
        int[] next=getNexts(needle.toCharArray());
        int j=0;
        for(int i=0;i<haystack.length();++i){
            while(j>0 && haystack.charAt(i)!=needle.charAt(j)){
                j=next[j-1]+1;
            }
            if(haystack.charAt(i)==needle.charAt(j)) ++j;
            if(j==needleLen) return i-needleLen+1;
        }
        return -1;
    }

    private static int[] getNexts(char[] b){
        int len=b.length;
        int[] next=new int[len];
        next[0]=-1;
        int k=-1;
        for(int i=1;i<len;++i){
            while(k!=-1 && b[k+1] !=b[i]){
                k=next[k];
            }
            if(b[k+1]==b[i]) ++k;
            next[i]=k;
        }
        return next;
    }
    }
