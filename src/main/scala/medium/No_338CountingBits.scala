package medium

/**
  * Auther: lzy
  * Description:
  * 给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，计算其二进制数中的 1 的数目并将它们作为数组返回。
  *
  * 示例 1:
  *
  * 输入: 2
  * 输出: [0,1,1]
  * 示例 2:
  *
  * 输入: 5
  * 输出: [0,1,1,2,1,2]
  * 进阶:
  *
  * 给出时间复杂度为O(n*sizeof(integer))的解答非常容易。但你可以在线性时间O(n)内用一趟扫描做到吗？
  * 要求算法的空间复杂度为O(n)。
  * 你能进一步完善解法吗？要求在C++或任何其他语言中不使用任何内置函数（如 C++ 中的 _builtin_popcount）来执行此操作。
  *
  * Date Created by： 8:57 on 2019/1/8
  * Modified By：
  **/

object No_338CountingBits {
    def countBits(num: Int): Array[Int] = {
//        val arr=Array.fill(num+1)(0)
        val arr=new Array[Int](num+1)
        for(i<- 1 to num){
            //脑洞比较大
            arr(i)+= arr(i&(i-1))+1
        }
        arr
    }

//    public int[] countBits(int num) {
//        if(num==0){
//            return new int[]{0};
//        }
//        int maxLevel= (int)(Math.log(num)/Math.log(2));
//        int[] dp=new int[num+1];
//        dp[0]=0;
//        for(int i=0;i<=maxLevel;i++){
//            int begin =(int) Math.pow(2, i);
//            int end=Math.min(num,(int) Math.pow(2, i+1)-1);
//            for(int j=begin;j<=end;j++){
//                dp[j]=dp[j-begin]+1;
//            }
//        }
//        return dp;
//    }
}
