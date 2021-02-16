package array;

/**
 * 面试题 01.07. 旋转矩阵
 * 给你一幅由 N × N 矩阵表示的图像，其中每个像素的大小为 4 字节。请你设计一种算法，将图像旋转 90 度。
 * 不占用额外内存空间能否做到？
 * 示例 1:
 * 给定 matrix =
 * [
 *   [1,2,3],
 *   [4,5,6],
 *   [7,8,9]
 * ],
 * 原地旋转输入矩阵，使其变为:
 * [
 *   [7,4,1],
 *   [8,5,2],
 *   [9,6,3]
 * ]
 * 示例 2:
 * 给定 matrix =
 * [
 *   [ 5, 1, 9,11],
 *   [ 2, 4, 8,10],
 *   [13, 3, 6, 7],
 *   [15,14,12,16]
 * ],
 * 原地旋转输入矩阵，使其变为:
 * [
 *   [15,13, 2, 5],
 *   [14, 3, 4, 1],
 *   [12, 6, 8, 9],
 *   [16, 7,10,11]
 * ]
 * 注意：本题与主站 48 题相同：https://leetcode-cn.com/problems/rotate-image/
 */
public class LCCI0107_RotateMatrix {

    public void rotate(int[][] matrix) {
    int rows=matrix.length;
    int cols=matrix[0].length;
    //上下翻转
        for (int i = 0; i < rows>>1; i++) {
            for (int j = 0; j < cols; j++) {
                int tmp=matrix[i][j];
                matrix[i][j]=matrix[rows-1-i][j];
                matrix[rows-1-i][j]=tmp;
            }
        }
        //主对角线旋转
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < i; j++) { //注意这里按照i做循环
                int tmp=matrix[i][j];
                matrix[i][j]=matrix[j][i];
                matrix[j][i]=tmp;
            }
        }
    }
}
