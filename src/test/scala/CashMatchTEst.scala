/**
  * Created by Administrator on 2017/12/2.
  */
object CashMatchTEst {
def test1(list:List[Any])= list match {
  case s::rest =>s+" "+rest
  case Nil=>""
}
  def test2()={

    val list=List(2)
    list match {
      case List(2,_*) =>println(true)
      case 1::rest=>println(true)
      case _=>println(false)
    }

  }

  def test3(s:Any): Unit ={

    s match {
      case s:Array[Int] if s.isEmpty =>
      case Array(5,_*) =>println(s)
      case a:List[String] =>println(true)
      case map:Map[Int,Int]=>println("int2int")
      case map:Map[Int,String]=>println("int2string")
      case _=>println(false)
    }





  }

  def main(args: Array[String]): Unit = {
    test2()
    val list:List[Int]=1::2::3::Nil
    val list2=List(1,2,3)
    println(test1(list))
    val map=Map[Int,String](1->"2")
    val str="string"
    val arr=List[Int](1,2,3)
    test3(Array(5,4))
  }

}
