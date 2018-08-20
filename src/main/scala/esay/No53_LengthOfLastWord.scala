package esay

/**
  * Created by Administrator on 2017/12/2.
  */
object No53_LengthOfLastWord {
  def lengthOfLastWord(s: String): Int = {
    if(s.isEmpty) return 0
       s.split(' ') match {
        case Array() =>0
        case strs:Array[String]=>strs.last.length

      }
  }

  def main(args: Array[String]): Unit = {
    println(lengthOfLastWord("1 2"))
  }
}
