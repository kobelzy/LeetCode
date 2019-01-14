package medium;

/**
 * 给定一个由 '1'（陆地）和 '0'（水）组成的的二维网格，计算岛屿的数量。一个岛被水包围，并且它是通过水平方向或垂直方向上相邻的陆地连接而成的。你可以假设网格的四个边均被水包围。
 * 示例 1:
 * 输入:
 * 11110
 * 11010
 * 11000
 * 00000
 * 输出: 1
 *
 * 示例 2:
 * 输入:
 * 11000
 * 11000
 * 00100
 * 00011
 * 输出: 3
 *
 * @program: LeetCode
 * @description:
 * @author: Lzy
 * @create: 2019-01-09 09:20
 **/
public class No200_NumberofIslands {
    int[] dx={-1,1,0,0};
    int[] dy={0,0,-1,1};
    int maxX=0;
    int maxY=0;
    char[][] grid=null;
    public int numIslands(char[][] grid) {


return -1;
    }

    public boolean isValid(int x,int y){
        if(x<0 || x>=maxX || y<0 || y>=maxY) return false;
//        if(grid[x][y]=='0' ||)
        return true;
    }
}
