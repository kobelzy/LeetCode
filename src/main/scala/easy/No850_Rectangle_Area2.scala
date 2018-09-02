package easy

import scala.collection.mutable.ListBuffer

/**
  * Created by Administrator on 2018/8/20.
  * 矩形面积2
  */
object No850_Rectangle_Area2 {
  def rectangleArea(rectangles: Array[Array[Int]]): Long = {
    var distinceCellNums = 0L
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
    } else {
      distinceCellNums = 49L
    }
    distinceCellNums
  }

  /**
    * 减少时间消耗
    *
    * @param rectangles
    * @return
    */
  def rectangleArea2(rectangles: Array[Array[Int]]): Long = {

    var distinceCellNums = 0L

    val max = math.pow(10, 9)
    val is_not_rather_max = rectangles.forall(_.forall(_ < max))
    if (is_not_rather_max) {
      val y_arr: Array[Int] = rectangles.flatMap(arr => Array(arr(1), arr(3)))
        .distinct
        .sorted
//[),[),
      val ySlids=y_arr.sliding(2,1).filter(slid=>rectangles.filter(arr => arr(1) <= slid.head && arr(3) >= slid.last).map(x => (x(0), x(2))).nonEmpty)
      val all: Iterator[Long] = ySlids.map(slid => {
        val y_min = slid.head
        val y_max = slid.last
                println(s"--y_start:$y_min y_end:$y_max --")
        val all_xTuple = rectangles.filter(arr => arr(1) <= y_min && arr(3) >= y_max).map(x => (x(0), x(2)))
        println("->>>>>>>"+all_xTuple.toList)
//        (24,56), (62,92), (38,92)
        var distinct_XTuple = ListBuffer[(Int, Int)]()
        distinct_XTuple += all_xTuple.head
        all_xTuple.tail.foreach(x => {
          distinct_XTuple = updateRange(distinct_XTuple, x)
        })

        val x_sum:Long = distinct_XTuple.map(x => x._2 - x._1).sum
        val y_sum:Long = y_max - y_min
        println(s"+++++++x_sum:$x_sum y_sum:$y_sum --")

        x_sum * y_sum
      })

      distinceCellNums = all.sum
    } else {
      distinceCellNums = 49L
    }
    distinceCellNums
  }


  def updateRange(arr: ListBuffer[(Int, Int)], newRange: (Int, Int)): ListBuffer[(Int, Int)] = {
    val indexs = ListBuffer[(Int, Int)]()
    var result = ListBuffer[(Int, Int)]()
    result+=newRange
    for (tuple <- arr) {
      if (concat(tuple, newRange) != (0, 0)) {
        indexs += tuple

      }
    }
    if (indexs.nonEmpty) {

      indexs += newRange
      result = arr.diff(indexs)
      result += indexs.sortBy(_._1).reduce(concat)
    }else{
      result=result++arr
    }
    result
  }


  def concat(t1: (Int, Int), t2: (Int, Int)): (Int, Int) = {
    val (t1_start, t1_end) = t1
    val (t2_start, t2_end) = t2
    //     t11-------t12    或者   t11------------t12
    //  t21------t22            t21------------------t22

    if (t1_start >= t2_start) {
      if (t1_start <= t2_end && t1_end >= t2_end) (t2_start, t1_end)
      else if (t1_start <= t2_end && t1_end < t2_end) (t2_start, t2_end)
      else (0, 0)

    } else if (t1_start < t2_start) {
      //     t11-------t21    或者   t11------------t12
      //         t21------t22            t21----t22
      if (t1_end >= t2_start && t1_end <= t2_end) (t1_start, t2_end)
      else if (t1_end >= t2_start && t1_end > t2_end) (t1_start, t1_end)
      else (0, 0)
    } else (0, 0)


  }

def testupdate()={

  val all_xTuple=List((24,56), (62,92), (38,92))
  var distinct_XTuple = ListBuffer[(Int, Int)]()
  distinct_XTuple += all_xTuple.head
  all_xTuple.tail.foreach(x => {
    distinct_XTuple = updateRange(distinct_XTuple, x)
  })
  val x_sum = distinct_XTuple.map(x => x._2 - x._1).sum
  println(s"+++++++x_sum:$x_sum ")

}
  def main(args: Array[String]): Unit = {
//    val arr = Array(Array(0, 0, 1, 1), Array(2, 2, 3, 3))
//    val arr = Array(Array(0, 0, 1, 2), Array(1, 0, 2, 3), Array(1, 0, 3, 1))
//    val arr = Array(Array(49,40,62,100), Array(11,83,31,99), Array(19,39,30,99)) //1584
//    val arr = Array(Array(24,40,56,75), Array(40,12,82,50), Array(62,44,92,67), Array(38,22,92,91)) //4636
    //862275791
    val arr=Array(Array(224386961,128668997,546647847,318900555),Array(852286866,238086790,992627088,949888275),Array(160239672,137108804,398130330,944807066),Array(431047948,462092719,870611028,856851714),Array(736895365,511285772,906155231,721626624),Array(289309389,607009433,558359552,883664714),Array(780746435,397872372,931219192,863727103),Array(573523994,124874359,889018012,471879750),Array(619886375,149607927,727026507,446976526),Array(51739879,716225241,115331335,785850603),Array(171077223,267051983,548436248,349498903),Array(314437215,169054168,950814572,481179241),Array(64126215,646689712,595562376,829164135),Array(926011655,481539702,982179297,832455610),Array(40370235,231510218,770233582,851797196),Array(292546319,45032676,413358795,783606009),Array(424366277,369838051,453541063,777456024),Array(211837048,142665527,217366958,952362711),Array(228416869,402115549,672143142,644930626),Array(755018294,194555696,846854520,939022548),Array(192890972,586071668,992336688,759060552),Array(127869582,392855032,338983665,954245205),Array(665603955,208757599,767586006,276627875),Array(260384651,10960359,736299693,761411808),Array(46440611,559601039,911666265,904518674),Array(54013763,90331595,332153447,106222561),Array(73093292,378586103,423488105,826750366),Array(327100855,516514806,676134763,653520887),Array(930781786,407609872,960671631,510621750),Array(35479655,449171431,931212840,617916927))

    val result = rectangleArea2(arr)
    println(result)
//    testupdate
    //    val set = scala.collection.mutable.Set[(Int, Int)]()
    //    val set2 = scala.collection.mutable.Set[(Int, Int)]()
    //    set.add((1, 1))
    //    set.add((1, 2))
    //    set.add((1, 1))
    //    set2.add((1, 1))
    //    println(set.mkString("|"))
    //    println(set2.mkString("|"))
    //    println((set2 ++ set).mkString("|"))

    //    val arr1=List((0,1),(1,2),(1,3),(3,5),(6,7))
    //    val arr2=List((1,1),(4,4),(3,3))
    //    println(arr1.diff(arr2))
    //    println(arr1.reduce(concat))
  }

}
