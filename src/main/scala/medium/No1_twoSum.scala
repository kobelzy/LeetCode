package medium

import scala.collection.mutable.ArrayBuffer

/**
  * Auther: lzy
  * Description:
  * Date Created by： 15:30 on 2018/7/10
  * Modified By：
  */

class No1_twoSum {

}
object No1_twoSum {
    def twoSum(nums: Array[Int], target: Int): Array[Int] = {
        val result=ArrayBuffer[Int]()
            for(i<-nums.indices){
                val other=target-nums(i)
                if(nums.contains(other)&&nums.indexOf(other)!=i){
                    result+=(i)+=(nums.indexOf(other))
                }
            }

        result.slice(0,2).toArray
    }

    def main(args: Array[String]): Unit = {
        val arr=Array(2,7,11,15)
        val result=twoSum(arr,9)
        println(result.mkString(","))
    }
}
