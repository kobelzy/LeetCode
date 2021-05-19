package bitOperation;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 1442. 形成两个异或相等数组的三元组数目
 * 给你一个整数数组 arr 。
 * 现需要从数组中取三个下标 i、j 和 k ，其中 (0 <= i < j <= k < arr.length) 。
 * a 和 b 定义如下：
 * a = arr[i] ^ arr[i + 1] ^ ... ^ arr[j - 1]
 * b = arr[j] ^ arr[j + 1] ^ ... ^ arr[k]
 * 注意：^ 表示 按位异或 操作。
 * 请返回能够令 a == b 成立的三元组 (i, j , k) 的数目。
 * 示例 1：
 * 输入：arr = [2,3,1,6,7]
 * 输出：4
 * 解释：满足题意的三元组分别是 (0,1,2), (0,2,2), (2,3,4) 以及 (2,4,4)
 * 示例 2：
 * 输入：arr = [1,1,1,1,1]
 * 输出：10
 * 示例 3：
 * 输入：arr = [2,3]
 * 输出：0
 * 示例 4：
 * 输入：arr = [1,3,5,7,9]
 * 输出：3
 * 示例 5：
 * 输入：arr = [7,11,12,9,5,2,7,17,22]
 * 输出：8
 * 提示：
 *
 * 1 <= arr.length <= 300
 * 1 <= arr[i] <= 10^8
 * @author: Patrick Star
 * @time: 2021/5/18 21:38
 */
public class No1442_CountTriplets {
    public static void main(String[] args) {
        int[] nums={2,3,1,6,7};
        No1442_CountTriplets t=new No1442_CountTriplets();
        System.out.println(t.countTriplets2(nums));
    }
    public int countTriplets(int[] arr) {
        int len = arr.length;
        int[] sum = new int[len + 1];
        for (int i = 1; i <= len; i++) sum[i] = sum[i - 1] ^ arr[i - 1];
        int ans = 0;
        for (int i = 1; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                for (int k = j; k < len; k++) {
                    int a = sum[j - 1] ^ sum[i - 1];
                    int b = sum[k] ^ sum[j - 1];
                    if (a == b) ans++;
                }
            }
        }
        return ans;
    }

    public int countTriplets2(int[] arr) {
        int len = arr.length;
        int xor = 0, ans = 0;
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i <= len; i++) {
            if (i >= 1) xor ^= arr[i - 1];
            List<Integer> list = map.getOrDefault(xor, new ArrayList<Integer>());
            for (int idx : list) {
                int index = idx + 1;
                ans += i - index;
            }
            list.add(i);
            map.put(xor, list);
        }
        return ans;
    }
}
