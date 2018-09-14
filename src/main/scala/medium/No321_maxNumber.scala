package medium

/**
  * Auther: lzy
  * Description:
  * Date Created by： 16:09 on 2018/7/10
  * Modified By：
  */

object No321_maxNumber {
    def maxNumber(nums1: Array[Int], nums2: Array[Int], k: Int): Array[Int] = {
        for(i<-1 to k-1){
            nums1.sliding(i,1).foreach(arr1=>{
                val selectNumsFromNums2=k-i
                nums2.sliding(selectNumsFromNums2,1).foreach(arr2=>{


                })
            })

        }



        Array(9)
    }

    /**
      * 拼接为Int，
      * @param arr1
      * @param index
      * @param num
      * @return
      */
    def mkMaxInt(arr1:Array[Int],index:Int,num:Int) ={
        ((arr1.slice(0,index):+num) ++  arr1.slice(index,arr1.length))
                .mkString("").toInt
    }

    def mkMaxIntByArrs(arr1:Array[Int],arr2:Array[Int])={
        for(index<-arr2.indices){



        }
    }

    def innerIter(arr:Array[Int])={

    }

    def main(args: Array[String]): Unit = {
        val arr=Array(1,2,3,3,4)
     val result=mkMaxInt(arr,0,10)
        println(result)
//        1102334

        println(arr.sliding(2,1).map(_.mkString(",")).mkString("|"))
    }
}
