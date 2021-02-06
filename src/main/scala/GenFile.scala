import java.io.{File, FileWriter}
import java.util

import scala.util.Random

object GenFile {
  def main(args: Array[String]): Unit = {
    val file=new File("D:\\WorkSpace\\Scala\\LeetCode\\src\\main\\resources\\data\\sid_sample.csv");
    val writer=new FileWriter(file)

    val maxLen = 59
    val str = "1234567890abcdefghigklmnopqrstuvwxyzABCDEFGHIGKLMNOPQRSTUVWXYZ-"
    var i = 0
    val map=new util.HashSet[String]();
    while (i < 200) {

//      val len = Random.nextInt(maxLen)+5
      val len = 16
      val uuid = Range(0, len).map(t => {
        str.charAt(Random.nextInt(str.length))
      }).mkString("")
      val sid=Math.abs(Random.nextLong())
      if(!map.contains(uuid)){
        map.add(uuid)
      val res=uuid + "\t" + sid
      writer.write(res+"\n")
      i +=1
      }
    }
    println("success write ,"+i)
    writer.close()
  }
}
