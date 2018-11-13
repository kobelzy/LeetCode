package easy

/**
  * Auther: lzy
  * Description:
  * Date Created by： 9:32 on 2018/11/9
  * Modified By：
  */

object No242_isAnagram {
    def isAnagram(s: String, t: String): Boolean = {
        val sMap=scala.collection.mutable.Map[Char,Int]()
        val tMap=scala.collection.mutable.Map[Char,Int]()
        s.foreach(c=>sMap(c)=sMap.getOrElseUpdate(c,1)+1)
        t.foreach(c=>tMap(c)=tMap.getOrElseUpdate(c,1)+1)
        println(sMap)
        println(tMap)
        sMap.equals(tMap)
    }

    def main(args: Array[String]): Unit = {
//        val sMap=scala.collection.mutable.Map[Char,Int]()
//        sMap.getOrElseUpdate('a',1)
//        sMap.getOrElseUpdate('b',2)
//        sMap.getOrElseUpdate('a',2)
//        println(sMap)
        val s="dgqztusjuu"
        val t="dqugjzutsu"
        println(isAnagram(s,t))
    }

}
