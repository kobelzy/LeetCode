object Set2ListTest {
  def main(args: Array[String]): Unit = {
    val list=List(Array("1","2","3"),Array("1","2","3"))
    val set=list.toSet
    set.foreach(a=>println(a.mkString(",")))
  }
}
