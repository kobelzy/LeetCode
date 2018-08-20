package easy

/**
  * Created by Administrator on 2018/8/20.
  * 矩形面积2
  */
object No850_Rectangle_Area2 {
  def rectangleArea(rectangles: Array[Array[Int]]): Int = {

    rectangles.foreach(arr=>{
      for(i<-Range(0 , arr.length-1 ,2)){

      }
    })
    val all_cells:Array[(Int, Int)]=rectangles.flatMap(arr=>{
      //如果写道flatMap，最后会生成一个List，每一次内部循环生成的Set会被转化为list，然后进行拼接，这样就会导致无法使用set的特性进行全局去重

      val cell_arr=scala.collection.mutable.Set[(Int, Int)]()

      val Array(x1,y1,x2,y2)=arr
      val x_len=math.abs(x1-x2)
      val y_len=math.abs(y1-y2)
      val cell_size=x_len*y_len

      for(x<-x1 until x2;  y<-y1 until y2){
        cell_arr.add(x -> y)
      }
      cell_arr
    })
      println(all_cells.mkString("|"))
      all_cells.distinct.length
  }

  def main(args: Array[String]): Unit = {
    val arr=Array(Array(0,0,2,2),Array(1,0,2,3),Array(1,0,3,1))
    val result=rectangleArea(arr)
    println(result)

    val set=scala.collection.mutable.Set[(Int,Int)]()
    val set2=scala.collection.mutable.Set[(Int,Int)]()
    set.add((1,1))
    set.add((1,2))
    set.add((1,1))
    set2.add((1,1))
    println(set.mkString("|"))
    println(set2.mkString("|"))
    println((set2++set).mkString("|"))
  }

}
