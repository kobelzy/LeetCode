package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 498. 对角线遍历
 * 给定一个含有 M x N 个元素的矩阵（M 行，N 列），请以对角线遍历的顺序返回这个矩阵中的所有元素，对角线遍历如下图所示。
 * 示例:
 * 输入:
 * [
 * [ 1, 2, 3 ],
 * [ 4, 5, 6 ],
 * [ 7, 8, 9 ]
 * ]
 * 输出:  [1,2,4,7,5,3,6,8,9]
 * 说明:
 * 给定矩阵中的元素总数不会超过 100000 。
 */
public class No498_DiagonalTraverse {
    public static void main(String[] args) {
        int[][] data = new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}};
        //1,2,4,7,5,3,6,8,9]
        No498_DiagonalTraverse t = new No498_DiagonalTraverse();
        int[] res = t.findDiagonalOrder(data);
        System.out.println(Arrays.toString(res));
    }

    /**
     * 贼鸡儿慢。。。。打败了4%的对手
     * @param matrix
     * @return
     */
    public int[] findDiagonalOrder(int[][] matrix) {
        if(matrix.length==0) return new int[0];
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[] res = new int[rows * cols];
        int index = 0;
        boolean isReverse = true;
        for (int i = 0; i < cols + rows - 1; i++) {
            List<Integer> list = new ArrayList<>();
            for (int col = i; col >= 0; col--) {
                int row = i - col;
                if (col >= cols || row >= rows) continue;
                list.add(matrix[row][col]);
            }
            if (isReverse) Collections.reverse(list);

            isReverse = !isReverse;
            for (Integer val : list) res[index++] = val;

        }
        return res;
    }

    public int[] findDiagonalOrder2(int[][] matrix) {
        if(matrix.length==0) return new int[0];
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[] res = new int[rows * cols];
        int index = 0;
        boolean isReverse = true;
        for (int i = 0; i < cols + rows - 1; i++) {
            if(isReverse){
                for (int col = 0; col <= i; col++) {
                    int row = i - col;
                    if (col >= cols || row >= rows) continue;
                    res[index++]=matrix[row][col];
                }
            }else{
                for (int col = i; col >= 0; col--) {
                    int row = i - col;
                    if (col >= cols || row >= rows) continue;
                    res[index++]=matrix[row][col];
                }
            }
            isReverse = !isReverse;
        }
        return res;
    }
}
