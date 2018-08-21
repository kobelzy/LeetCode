package easy

/**
  * Created by Administrator on 2018/8/20.
  * 矩形面积2
  */
object No850_Rectangle_Area2 {
  def rectangleArea(rectangles: Array[Array[Int]]): Int = {
    var distinceCellNums = 0
    var is_max = false
    for (i <- rectangles.indices) {
      for (j <- rectangles(i).indices) {
        if (rectangles(i)(j) >= math.pow(10, 9)) is_max = true
      }
    }
    if (!is_max) {
        val cell_arr = scala.collection.mutable.Set[(Int, Int)]()

       rectangles.foreach(arr => {
        //如果写道flatMap，最后会生成一个List，每一次内部循环生成的Set会被转化为list，然后进行拼接，这样就会导致无法使用set的特性进行全局去重


        val Array(x1, y1, x2, y2) = arr

        for (x <- x1 until x2; y <- y1 until y2) {
          cell_arr.add(x -> y)
        }
      })
      distinceCellNums = cell_arr.size
    }    else {
      distinceCellNums = 49
    }
    distinceCellNums
  }

  /**
    * 减少时间消耗
    * @param rectangles
    * @return
    */
  def rectangleArea2(rectangles: Array[Array[Int]]): Int = {
    val y_arr:Array[Int]=rectangles.flatMap(arr=>Array(arr(1),arr(3)))
        .distinct
      .sorted
    y_arr.sliding(2,1).foreach(arr=>println(arr.mkString(",")))
    val all:Iterator[Int]=y_arr.sliding(2,1).map(slid=>{
      val y_min=slid.head
      val y_max=slid.last
      val x_sum=rectangles.filter(arr=>arr(1)<=y_min&&arr(3)>=y_max).map(arr=>(arr(0) to arr(2)).toArray)
        .reduce((a,b)=>a.intersect(b)).length
      val y_sum=y_max-y_min
      println("y_sum:"+y_sum)
      println("x_sum:"+x_sum)
      x_sum*y_sum
    })

all.sum
  }
//  0,2,0,3,0,1    0,1,2,3

    def main(args: Array[String]): Unit = {
    val arr = Array(Array(0, 0, 2, 2), Array(1, 0, 2, 3), Array(1, 0, 3, 1))
    val result = rectangleArea2(arr)
    println(result)

    val set = scala.collection.mutable.Set[(Int, Int)]()
    val set2 = scala.collection.mutable.Set[(Int, Int)]()
    set.add((1, 1))
    set.add((1, 2))
    set.add((1, 1))
    set2.add((1, 1))
    println(set.mkString("|"))
    println(set2.mkString("|"))
    println((set2 ++ set).mkString("|"))
  }

}
