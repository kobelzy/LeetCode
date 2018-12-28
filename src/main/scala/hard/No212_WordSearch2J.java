package hard;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 给定一个二维网格 board 和一个字典中的单词列表 words，找出所有同时在二维网格和字典中出现的单词。
 * <p>
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母在一个单词中不允许被重复使用。
 * <p>
 * 示例:
 * <p>
 * 输入:
 * words = ["oath","pea","eat","rain"] and board =
 * [
 * ['o','a','a','n'],
 * ['e','t','a','e'],
 * ['i','h','k','r'],
 * ['i','f','l','v']
 * ]
 * <p>
 * 输出: ["eat","oath"]
 * 说明:
 * 你可以假设所有输入都由小写字母 a-z 组成。
 * <p>
 * 提示:
 * <p>
 * 你需要优化回溯算法以通过更大数据量的测试。你能否早点停止回溯？
 * 如果当前单词不存在于所有单词的前缀中，则可以立即停止回溯。什么样的数据结构可以有效地执行这样的操作？散列表是否可行？为什么？ 前缀树如何？如果你想学习如何实现一个基本的前缀树，请先查看这个问题： 实现Trie（前缀树）。
 *
 * @program: LeetCode
 * @description:
 * @author: Lzy
 * @create: 2018-12-27 09:27
 **/
public class No212_WordSearch2J {
    public static void main(String[] args) {
       String[] words = new String[]{"oath","pea","eat","rain"};
       String[][] board=new String[][]{
               {'o','a','a','n'},
  {'e','t','a','e'},
  {'i','h','k','r'},
  {'i','f','l','v'}
}


    }

        Set<String> res = new HashSet<String>();

    public List<String> findWords(char[][] board, String[] words) {
        Trie trie = new Trie();
        //目标单词放到字典树当中
        for (String word : words) {
            trie.insert(word);
        }
        //矩阵 长度
        int m = board.length;
        //矩阵宽度
        int n = board[0].length;
        //节点是否已经被访问过
        boolean[][] visited=new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs(board, visited, "", i, j, trie);
            }
        }
        return new ArrayList<String>(res);

    }

    public void dfs(char[][] board, boolean[][] visited, String str, int x, int y, Trie trie) {
        //递归终止条件
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length) return;
        //该节点被访问过，则返回
        if (visited[x][y]) return;
        str += board[x][y];
        //如果已经拼接的字符串和字典树匹配不上，则直接返回
        if (!trie.startsWith(str)) return;
        //如果已经拼接的字符串是字典树中的一个完整单词，则将该单词放如结果集合当中
        if (trie.search(str)) res.add(str);
        //该节点设置为已经访问过，该单词继续进行该点的访问时候会被跳过
        visited[x][y] = true;
        //对该点的上下左右分别进行继续判断
        dfs(board, visited, str, x - 1, y, trie);
        dfs(board, visited, str, x + 1, y, trie);
        dfs(board, visited, str, x, y - 1, trie);
        dfs(board, visited, str, x, y + 1, trie);
        //回溯
        visited[x][y] = false;

    }

}