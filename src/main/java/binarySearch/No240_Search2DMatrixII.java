package binarySearch;

/**
 * 240. 搜索二维矩阵 II
 * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：
 * 每行的元素从左到右升序排列。
 * 每列的元素从上到下升序排列。
 * 示例 1：
 * 输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 5
 * 输出：true
 * 示例 2：
 * 输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 20
 * 输出：false
 * 提示：
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= n, m <= 300
 * -109 <= matix[i][j] <= 109
 * 每行的所有元素从左到右升序排列
 * 每列的所有元素从上到下升序排列
 * -109 <= target <= 109
 *
 * @description:
 * @author: Patrick Star
 * @time: 2021/5/6 21:34
 */
public class No240_Search2DMatrixII {
    public static void main(String[] args) {
//        int[] nums = {2, 4, 7, 11, 15};
        int[] nums = {1, 2, 3, 10, 18};
        No240_Search2DMatrixII t = new No240_Search2DMatrixII();
        System.out.println(t.binarySearch(nums, 5));
        System.out.println(t.searchLastElement(nums, 19));
        System.out.println(t.searchFirstElement(nums, 19));


        int[][] matrix = {
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}};

        System.out.println(t.searchMatrix2(matrix, 5));
        int[][] matrix2 = {
                {2, 5},
                {2, 8},
                {7, 9},
                {7, 11},
                {9, 11}};
        System.out.println(t.searchMatrix2(matrix2, 7));

    }

    /**
     * 标准二分解法
     *
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) return false;

        int shorterDim = Math.min(matrix.length, matrix[0].length);
        for (int i = 0; i < shorterDim; i++) {
            boolean verticalFound = binarySearch(matrix, target, i, true);
            boolean horizontalFound = binarySearch(matrix, target, i, false);
            if (verticalFound || horizontalFound) return true;
        }
        return false;
    }

    private boolean binarySearch(int[][] nums, int target, int start, boolean vertical) {
        int left = start;
        int right = vertical ? nums[0].length - 1 : nums.length - 1;

        while (right >= left) {
            int mid = left + ((right - left) >> 1);
            int cur=vertical?nums[start][mid]:nums[mid][start];
            if (cur < target) left = mid + 1;
            else if (cur > target) right = mid - 1;
            else return true;
        }
        return false;
    }

    /**
     * 二分查找解法，无效，逻辑有问题
     *
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix2(int[][] matrix, int target) {
        int shorterDim = Math.min(matrix.length, matrix[0].length);

        //第一，找到所在行，n<=x && x>n+1
        int[] diagonals = new int[shorterDim];
        for (int i = 0; i < shorterDim; i++) diagonals[i] = matrix[i][i];
        int end = searchFirstElement(diagonals, target);
        int start = searchLastElement(diagonals, target);
        System.out.println(start + "," + end);
        if (start == -1) return false;
        if (start == end) return true;
        if (end == -1) end = matrix.length - 1;
        for (int i = start; i <= end; i++) {
            if (binarySearch(matrix[i], target) != -1) return true;
        }

//        if (targetRow == -1) return false;
//        int targetCol = binarySearch(matrix[targetRow], target);
//        return targetCol != -1;
        return false;
    }

    /**
     * 搜索第一个大于等于该target的下标
     * 1，2，4，5 求3，返回4位置
     * 最后一个小于等于target的
     *
     * @param nums
     * @param target
     * @return
     */
    private int searchFirstElement(int[] nums, int target) {
        int len = nums.length, left = 0, right = len - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] >= target) {
                if (mid == 0 || nums[mid - 1] < target) return mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 1，2，4，5 求3，返回2位置
     * 最后一个小于等于target的
     *
     * @param nums
     * @param target
     * @return
     */
    private int searchLastElement(int[] nums, int target) {
        int len = nums.length, left = 0, right = len - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] <= target) {
                if (mid == len - 1 || nums[mid + 1] > target) return mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    private int binarySearch(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] == target) return mid;
            else if (nums[mid] > target) right = mid - 1;
            else left = mid + 1;
        }
        return -1;
    }
}
