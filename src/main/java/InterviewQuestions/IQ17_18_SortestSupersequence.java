package InterviewQuestions;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 面试题 17.18. 最短超串
 * 假设你有两个数组，一个长一个短，短的元素均不相同。找到长数组中包含短数组所有的元素的最短子数组，其出现顺序无关紧要。
 * 返回最短子数组的左端点和右端点，如有多个满足条件的子数组，返回左端点最小的一个。若不存在，返回空数组。
 * 示例 1:
 * 输入:
 * big = [7,5,9,0,2,1,3,5,7,9,1,1,5,8,8,9,7]
 * small = [1,5,9]
 * 输出: [7,10]
 * 示例 2:
 * 输入:
 * big = [1,2,3]
 * small = [4]
 * 输出: []
 * 提示：
 * big.length <= 100000
 * 1 <= small.length <= 100000
 */
public class IQ17_18_SortestSupersequence {
    public static void main(String[] args) {
        IQ17_18_SortestSupersequence t = new IQ17_18_SortestSupersequence();
        int[] big = new int[]{7, 5, 9, 0, 2, 1, 5, 5, 7, 9, 1, 1, 5, 8, 8, 9, 7};
        int[] small = new int[]{1, 5,5, 9};
        System.out.println(Arrays.toString(t.shortestSeq(big, small)));
    }

    /**
     * 详细解释：
     * https://leetcode-cn.com/problems/shortest-supersequence-lcci/solution/xiang-xi-tu-jie-hua-dong-chuang-kou-chao-qi7g/
     *
     * @param big
     * @param small
     * @return
     */
    public int[] shortestSeq(int[] big, int[] small) {
        int len = big.length;
        int[] res = new int[]{-1, -1};
        Map<Integer, Integer> cache = new HashMap<>();
        int diff = small.length, minLen = len;
        for (int i = 0; i < small.length; i++) {
            cache.put(small[i], 1);
            //如果子串有重复值，必须记录实际个数，用下边的
            //cache.put(small[i], cache.getOrDefault(small[i],0)+1);
        }

        int left = 0, right = 0;
        for (right = 0; right < len; right++) {
            //如果该字符在small中存在，diff-1
            if (cache.containsKey(big[right])) {
                if (cache.get(big[right]) > 0) diff--;
                cache.put(big[right], cache.get(big[right]) - 1);
            }

            //如果diff=0，缩小左边界
            while (diff == 0) {
                if (right - left < minLen) {
                    minLen = right - left;
                    res[0] = left;
                    res[1] = right;
                }
                //如果该字符在small中存在，那么认为超出边界，将diff+1
                if (cache.containsKey(big[left])) {
                    cache.put(big[left], cache.get(big[left]) + 1);
                    if (cache.get(big[left]) > 0) diff++;
                }
                left++;
            }
        }
        return res[0] == -1 ? new int[0] : res;
    }


}
