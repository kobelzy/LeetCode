package arithmetic

object QuickSort {
  def main(args: Array[String]): Unit = {
    val list=List(5,3,9,2,7,6,7,1,4,8,10)
//    println(quickSort(list))
//    println(quickSort(list.toArray).mkString(","))
    val list2=List(1)
//    println(quickSort(list2.toArray).mkString(","))
    println(quickSort(list2).mkString(","))

  }
  def quickSort(list:List[Int]):List[Int]={
    list match {
      case Nil => Nil
//      case head::Nil=>head::Nil
      case head::tail=>{
        val (left,right)=tail.partition(_<head)
        quickSort(left) ::: head :: quickSort(right)
      }
    }
  }

  def quickSort(list:Array[Int]):Array[Int]={
    list match {
      case Array() => Array()
      case arr=>
        val (left,right)=arr.tail.partition(_ < arr.head)
        (quickSort(left) :+ arr.head) ++ quickSort(right)
    }
  }
}
