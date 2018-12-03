package easy

/**
  * Auther: lzy
  * Description:给定一个大小为 n 的数组，找到其中的众数。众数是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
  * 你可以假设数组是非空的，并且给定的数组总是存在众数。
  * 输入: [3,2,3]
  * 输出: 3
  * Date Created by： 9:13 on 2018/11/30
  * Modified By：
  */

object No169_MajorityElement {
/***
 * 方法1：函数式实现
 *980ms
 */
    def majorityElement(nums: Array[Int]): Int = {
    nums.map((_,1L)).groupBy(_._1).mapValues(_.size).maxBy(_._2)._1
    }
    /***
      * 方法2：Map实现
      *980ms
      */
    def majorityElement2(nums: Array[Int]): Int = {
        val map=scala.collection.mutable.Map[Int,Int]()
       for(n<-nums){
           map(n)=map.getOrElseUpdate(n,0)+1
       }
        map.maxBy(_._2)._1
    }

    /***
      * 方法3：排序实现
      *900ms
      */
    def majorityElement3(nums: Array[Int]): Int = {
        val sorted=nums.sorted
        sorted(nums.length/2)
    }

    /***
      * 方法3：这个不知道怎么形容，脑回路十分清奇
      * 不是最多的数据一定会被最多的数据给挤掉。。。。
      *800ms
      * 这个算法使用java实现，只需要4ms，打败99.96%。也是够了。。。。同时可见scala的运行效率之低，或者也可能是网站的scala执行器比较差
      */
    def majorityElement4(nums: Array[Int]): Int = {
        var cnt=0
        var cur =0
        for(n<-nums){
            if(cnt==0) {
                cnt=1;cur=n
            }else if(n==cur){
                cnt+=1
            }else cnt -=1
        }
        cur
    }
}
