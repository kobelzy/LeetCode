package easy

/**
  * Created by taihe on 2017/12/4.
  */
object No67_AddBinary {
  def addBinary(a: String, b: String): String = {
val sum=a.toLong+b.toLong
    val str=scala.collection.mutable.Buffer[Int]()
    val sumArr:Array[Int]=sum.toString.toCharArray.map(_.toInt)
    for(i<-Range(sumArr.size-1 ,-1 ,-1 )){
if(sumArr(i)>1) str+=1

    }
    "s"
  }

  def main(args: Array[String]): Unit = {
    addBinary("111","11")
  }

  def aggreate(sumArr:Array[Int],i:Int,buffer:scala.collection.mutable.Buffer[Int])={
    if(sumArr.length<=i+1) {
      if (sumArr(i) > 1) buffer += 1


    }


  }
}
