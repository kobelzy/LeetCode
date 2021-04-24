package queue;


import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 79. 单词搜索
 * 剑指 Offer 12. 矩阵中的路径
 * 给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 * 示例 1：
 * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
 * 输出：true
 * 示例 2：
 * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
 * 输出：true
 * 示例 3：
 * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
 * 输出：false
 * 提示：
 * m == board.length
 * n = board[i].length
 * 1 <= m, n <= 6
 * 1 <= word.length <= 15
 * board 和 word 仅由大小写英文字母组成
 * 进阶：你可以使用搜索剪枝的技术来优化解决方案，使其在 board 更大的情况下可以更快解决问题？
 */
public class No79_WordSearch {
    public static void main(String[] args) {
        char[][] chars = {//3,4
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'E', 'S'},
                {'A', 'D', 'E', 'E'}};
        No79_WordSearch t = new No79_WordSearch();
//        System.out.println(t.exist(chars, "ABCESEEEFS"));
        System.out.println(t.exist(chars, "ABCD"));
//        System.out.println(chars[0][0]);
    }

    public boolean exist(char[][] board, String word) {
        int rowNum = board.length, colNum = board[0].length;
        if (word.length() == 0 || word.length() > rowNum * colNum) return false;
        Set<Integer> visited = new HashSet<>();
        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < colNum; j++) {
                if (board[i][j] == word.charAt(0)) {
                    visited.clear();
                    int visitIndex = i * colNum + j;
                    visited.add(visitIndex);
                    if (check(board, word, visited, rowNum, colNum, i, j, 1)) return true;
                }
            }
        }
        return false;
    }

    private boolean check(char[][] board, String word, Set<Integer> visited, int rowNum, int colNum, int curRow, int curCol, int index) {
        if (index == word.length() ) return true; //注意这里index是要超过最大数组索引的，因为index=word.length时候，index的值还没有做判断
        if (curRow - 1 >= 0 && board[curRow - 1][curCol] == word.charAt(index)) {
            int visitedIndex = (curRow - 1) * colNum + curCol;
            if (!visited.contains(visitedIndex)) {
                visited.add(visitedIndex);
                if (check(board, word, visited, rowNum, colNum, curRow - 1, curCol, index + 1)) return true;
                visited.remove(visitedIndex);
            }
        }
        if (curRow + 1 < rowNum && board[curRow + 1][curCol] == word.charAt(index)) {
            int visitedIndex = (curRow + 1) * colNum + curCol;
            if (!visited.contains(visitedIndex)) {
                visited.add(visitedIndex);
                if (check(board, word, visited, rowNum, colNum, curRow + 1, curCol, index + 1)) return true;
                visited.remove(visitedIndex);
            }
        }

        if (curCol - 1 >= 0 && board[curRow][curCol - 1] == word.charAt(index)) {
            int visitedIndex = curRow * colNum + curCol - 1;
            if (!visited.contains(visitedIndex)) {
                visited.add(visitedIndex);
                if (check(board, word, visited, rowNum, colNum, curRow, curCol - 1, index + 1)) return true;
                visited.remove(visitedIndex);
            }
        }
        if (curCol + 1 < colNum && board[curRow][curCol + 1] == word.charAt(index)) {
            int visitedIndex = curRow * colNum + curCol + 1;
            if (!visited.contains(visitedIndex)) {
                visited.add(visitedIndex);
                if (check(board, word, visited, rowNum, colNum, curRow, curCol + 1, index + 1)) return true;
                visited.remove(visitedIndex);
            }
        }

        return false;
    }

    /**
     * BFS解法，无法解决该问题。
     * 由于用过的单次不能被重复使用，需要引入Set或修改已经访问过的位置
     * 由于无法对超过一个层级的父亲做恢复，所以需要对每个节点都创建一个visited，使用对应的Queue来保存
     * 但是会超时，遇到下列情况会造成巨量扩展：
     * [["A","A","A","A","A","A"],["A","A","A","A","A","A"],["A","A","A","A","A","A"],["A","A","A","A","A","A"],["A","A","A","A","A","A"],["A","A","A","A","A","A"]]
     * "AAAAAAAAAAAAAAB"
     *
     * @param board
     * @param word
     * @return
     */
    public boolean exist2(char[][] board, String word) {
        int rowNum = board.length, colNum = board[0].length;
        if (word.length() == 0 || word.length() > rowNum * colNum) return false;
        Queue<Integer> queue = new LinkedList<>();
//        Set<Integer> visited = new HashSet<>();
        Queue<Set<Integer>> visit = new LinkedList<>();
        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < colNum; j++) {
                if (board[i][j] == word.charAt(0)) {
                    queue.offer(i * colNum + j);
                    Set<Integer> visitedSet = new HashSet<>();
                    visitedSet.add(i * colNum + j);
                    visit.offer(visitedSet);
                    int index = 0;
                    while (!queue.isEmpty()) {
                        System.out.println(queue);
                        int size = queue.size();
                        if (index == word.length() - 1) return true;
                        index++;
                        if (index >= word.length()) return false;
                        for (int k = 0; k < size; k++) {
                            int curIndex = queue.poll();
                            int curRow = curIndex / colNum;
                            int curCol = curIndex % colNum;
                            System.out.println(curRow + "," + curCol);
                            Set<Integer> visitedInnerSet = visit.poll();
                            extracted(board, word, colNum, queue, visit, index, curRow - 1, curCol, visitedInnerSet);
                            extracted(board, word, colNum, queue, visit, index, curRow + 1, curCol, visitedInnerSet);
                            extracted(board, word, colNum, queue, visit, index, curRow, curCol - 1, visitedInnerSet);
                            extracted(board, word, colNum, queue, visit, index, curRow, curCol + 1, visitedInnerSet);
                        }
                    }
                }
            }
        }

        return false;
    }

    private void extracted(char[][] board, String word, int colNum, Queue<Integer> queue, Queue<Set<Integer>> visit, int index, int curRow, int curCol, Set<Integer> visitedInnerSet) {
        if (curRow >= 0 && curRow < board.length &&
                curCol >= 0 && curCol < board[0].length &&
                board[curRow][curCol] == word.charAt(index)) {
            int newIndex = curRow * colNum + curCol;

            if (!visitedInnerSet.contains(newIndex)) {
                queue.add(newIndex);
                Set<Integer> newVisitedSet = new HashSet<>(visitedInnerSet);
                newVisitedSet.add(newIndex);
                visit.offer(newVisitedSet);
            }
        }
    }
}
