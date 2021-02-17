package string;

/**
 * 14. 最长公共前缀
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 *
 * 如果不存在公共前缀，返回空字符串 ""。
 *
 * 示例 1:
 *
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 * 示例 2:
 *
 * 输入: ["dog","racecar","car"]
 * 输出: ""
 * 解释: 输入不存在公共前缀。
 * 说明:
 *
 * 所有输入只包含小写字母 a-z 。
 */
public class No14_LongestCommonPrefix {
    /**
     * 暴力
     * @param strs
     * @return
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        String head = strs[0];
        for (int i = 0; i < head.length(); i++) {
            char a = head.charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if (i < strs[j].length()) {
                    if (strs[j].charAt(i) != a) return head.substring(0, i);
                } else return head.substring(0, i);
            }
        }
        return strs[0];
    }

    /**
     * 二分算法
     *
     * @param strs
     * @return
     */
    public String longestCommonPrefix3(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        int minLen = Integer.MAX_VALUE;
        for (String str : strs) minLen = Math.min(str.length(), minLen);
        if(minLen==0) return "";

        int low = 0;
        int high = minLen;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (isCommonPrefix(strs, mid)) low = mid + 1;
            else high = mid-1;
        }
        return strs[0].substring(0,low + ((high - low) >> 1));
    }

    private boolean isCommonPrefix(String[] strs, int len) {
        String prefix = strs[0].substring(0, len);
        for (int i = 0; i < strs.length; i++)
            if (!strs[i].startsWith(prefix)) return false;
        return true;
    }


    /**
     * 分治算法
     *
     * @param strs
     * @return
     */
    public String longestCommonPrefix2(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        else return longestCommonPrefix2(strs, 0, strs.length - 1);
    }

    public String longestCommonPrefix2(String[] strs, int start, int end) {
        if (start == end) return strs[start];
        else {
            //注意这里，位运算的符号等级非常低
            int mid = start + ((end - start) >> 1);
            String left = longestCommonPrefix2(strs, start, mid);
            String right = longestCommonPrefix2(strs, mid + 1, end);
            return longestCommonPrefixFromTwo(left, right);
        }
    }

    public String longestCommonPrefixFromTwo(String left, String right) {
        for (int i = 0; i < left.length(); i++) {
            if (i >= right.length() || left.charAt(i) != right.charAt(i)) {
                return left.substring(0, i);
            }
        }
        return left;
    }




    public static void main(String[] args) {
        No14_LongestCommonPrefix test = new No14_LongestCommonPrefix();
        //

        /**
         * abcdefgh,c
         * 0,8,4 abcd,efgh
         * 0,3,1 a,bc
         * 2,4,3 c,d
         *
         * 应该取3才对，
         * abcdef,c
         * 0,6,3 abc,def
         * 0,2,1,
         */
//        String[] strs = {"flower", "flow", "flight"};
        String[] strs = {"abczefgh", "abcdabcd", "abcdwefd"};
//        String[] strs = {"caa", "", "a", "acb"};
        System.out.println(test.longestCommonPrefix3(strs));
        int start = 2;
        int end = 3;
    }
}
