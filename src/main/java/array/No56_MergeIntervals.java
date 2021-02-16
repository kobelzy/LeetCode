package array;

import java.util.*;

/**
 * 56. 合并区间
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。
 * 示例 1：
 * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 输出：[[1,6],[8,10],[15,18]]
 * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * 示例 2：
 * 输入：intervals = [[1,4],[4,5]]
 * 输出：[[1,5]]
 * 解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。
 * 提示：
 * 1 <= intervals.length <= 104
 * intervals[i].length == 2
 * 0 <= starti <= endi <= 104
 */
public class No56_MergeIntervals {
    public static void main(String[] args) {
        int[][] a=new int[][]{{2,3},
                {4,5},{6,7},{8,9},{1,10}};
        No56_MergeIntervals t=new No56_MergeIntervals();
        int[][] merge = t.merge(a);
        for (int[] ints : merge) {
            System.out.println(Arrays.toString(ints));

        }
    }
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (o1, o2) -> o1[0]-o2[0]);
        List<int[]> res = new ArrayList<>();
        for (int i = 0; i < intervals.length; i++) {
            int start = intervals[i][0];
            if (start == -1) continue;
            int end = intervals[i][1];
            int j = i + 1;
            while (j < intervals.length) {
                int tmpStart = intervals[j][0];
                int tmpEnd = intervals[j][1];
                if ((start >= tmpStart && start <= tmpEnd) ||
                        (end >= tmpStart && end <= tmpEnd) ||
                        (tmpStart >= start && tmpStart <= end) ||
                        (tmpEnd >= start && tmpEnd <= end)) {
                    start = Math.min(start, tmpStart);
                    end = Math.max(end, tmpEnd);
                    intervals[j][0] = -1;
                } else break;
                j++;
            }
            res.add(new int[]{start,end});
        }
        return res.toArray(new int[res.size()][]);
    }

    /**
     * 官方答案 垃圾太慢了。。。
     * @param intervals
     * @return
     */
        public int[][] merge2(int[][] intervals) {
            if (intervals.length == 0) {
                return new int[0][2];
            }
            Arrays.sort(intervals, new Comparator<int[]>() {
                public int compare(int[] interval1, int[] interval2) {
                    return interval1[0] - interval2[0];
                }
            });
            List<int[]> merged = new ArrayList<int[]>();
            for (int i = 0; i < intervals.length; ++i) {
                int L = intervals[i][0], R = intervals[i][1];
                if (merged.size() == 0 || merged.get(merged.size() - 1)[1] < L) {
                    merged.add(new int[]{L, R});
                } else {
                    merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], R);
                }
            }
            return merged.toArray(new int[merged.size()][]);
        }


    /**
     * 另一个答案，和字节写的一样速度
     * @param intervals
     * @return
     */
    public int[][] merge3(int[][] intervals) {
        // 先按照区间起始位置排序
        Arrays.sort(intervals, (v1, v2) -> v1[0] - v2[0]);
        // 遍历区间
        int[][] res = new int[intervals.length][2];
        int idx = -1;
        for (int[] interval: intervals) {
            // 如果结果数组是空的，或者当前区间的起始位置 > 结果数组中最后区间的终止位置，
            // 则不合并，直接将当前区间加入结果数组。
            if (idx == -1 || interval[0] > res[idx][1]) {
                res[++idx] = interval;
            } else {
                // 反之将当前区间合并至结果数组的最后区间
                res[idx][1] = Math.max(res[idx][1], interval[1]);
            }
        }
        return Arrays.copyOf(res, idx + 1);
    }

}
