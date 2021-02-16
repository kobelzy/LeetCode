package array;

import java.util.BitSet;
import java.util.HashSet;
import java.util.Set;

/**
 * 面试题 01.08. 零矩阵
 * 编写一种算法，若M × N矩阵中某个元素为0，则将其所在的行与列清零。
 * 示例 1：
 * 输入：
 * [
 * [1,1,1],
 * [1,0,1],
 * [1,1,1]
 * ]
 * 输出：
 * [
 * [1,0,1],
 * [0,0,0],
 * [1,0,1]
 * ]
 * 示例 2：
 * <p>
 * 输入：
 * [
 * [0,1,2,0],
 * [3,4,5,2],
 * [1,3,1,5]
 * ]
 * 输出：
 * [
 * [0,0,0,0],
 * [0,4,5,0],
 * [0,3,1,0]
 * ]
 */
public class LCCI0108_ZeroMatrix {
    public static void main(String[] args) {
        BitSet rowBS=new BitSet(10);
        rowBS.set(5);
        System.out.println(rowBS.get(5));
        System.out.println(rowBS.get(6));
        System.out.println(rowBS);
    int[][] data=new int[][]{
            {1,1,1},
            {1,0,1},
            {1,1,1}};
    LCCI0108_ZeroMatrix t=new LCCI0108_ZeroMatrix();
    t.setZeroes(data);
    }
    public void setZeroes(int[][] matrix) {
        int rows=matrix.length;
        int cols=matrix[0].length;
        BitSet rowBS=new BitSet(rows);
        BitSet colBS=new BitSet(cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if(matrix[i][j]==0){
                    rowBS.set(i);
                    colBS.set(j);
                }
            }
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if(rowBS.get(i)|| colBS.get(j))matrix[i][j]=0;
            }
        }
    }

        /**
         * HashSet保存0记录
         * @param matrix
         */
    public void setZeroes2(int[][] matrix) {
        int rows=matrix.length;
        int cols=matrix[0].length;
        Set<Integer> zerosRowSet=new HashSet<>();
        Set<Integer> zerosColSet=new HashSet<>();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if(matrix[i][j]==0){
                    zerosRowSet.add(i);
                    zerosColSet.add(j);
                }
            }
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if(zerosRowSet.contains(i) || zerosColSet.contains(j))matrix[i][j]=0;
            }
        }


    }
}
