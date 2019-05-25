package medium

import scala.collection.mutable.ListBuffer

/**
  * 6. Z 字形变换
  * 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
  *
  * 比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
  *
  * L   C   I   R
  * E T O E S I I G
  * E   D   H   N
  * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。
  *
  * 请你实现这个将字符串进行指定行数变换的函数：
  *
  * string convert(string s, int numRows);
  * 示例 1:
  *
  * 输入: s = "LEETCODEISHIRING", numRows = 3
  * 输出: "LCIRETOESIIGEDHN"
  * 示例 2:
  *
  * 输入: s = "LEETCODEISHIRING", numRows = 4
  * 输出: "LDREOEIIECIHNTSG"
  * 解释:
  *
  * L     D     R
  * E   O E   I I
  * E C   I H   N
  * T     S     G
  */
object No6_ZigZagConversion {
  def main(args: Array[String]): Unit = {
    println(convert("LEETCODEISHIRING", 3))
//val list=ListBuffer(1,2,3,4,5,6)
//    list.insert(2,10)
//    println(list)
  }
  def convert(s: String, numRows: Int): String = {
    /**
      * 0   4   8   x%4==0
      * 1 3 5 7 9   偶数
      * 2   6   10  (x+2) %4==0
      */
/*    val ceil=new StringBuilder()
    val mid=new StringBuilder()
    val floor=new StringBuilder()
    for(i<-s.indices){
    if(i%4==0) ceil.append(s(i))
    else if((i & 1)==1) mid.append(s(i))
    else floor.append(s(i))
    }
    ( ceil++mid ++floor).toString()*/


    /**
      * n行
      * 斜线 n-2行
      * 一个周期 n+n-2=2n-2
      * ceil =i%(2n-2)
      * 第二行：每次隔（2n-2-2,以及1）
      * 第三行：（2n-2-4,以及2）
      *
      */
    /**
      * 按照矩阵放数据，遇到三角则跳过，
      * 3行：1个=1
      * 4行：3个=2+1
      * 5行；6个=3+2+1
      *
      * 三角长度和高度都是n-2
      * 横坐标的周期是n-2+1
      * 横坐标+1
      */
  val cycle=2*numRows-2
    var index=0
    val list=new ListBuffer[Char]()
    for(i<-s.indices){
      val remainder=(i+1)%cycle
      if(i<numRows){
        list+=s(i)
        index+=1
      }
      //长度为2n-2
      else if(remainder<=numRows &&remainder>0){
        list.insert(index,s(i))
        if(remainder ==1 || remainder==cycle-1) index+=1
        else         index+=(1+(i+1)/cycle)
      }else {
        index-=1
        list.insert(index,s(i))
      }
      if(remainder==0) index-=1
    }

    list.toString()
  }
}
