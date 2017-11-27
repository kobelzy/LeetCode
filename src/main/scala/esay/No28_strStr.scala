package esay

/**
  * Created by taihe on 2017/11/27.
  */
object No28_strStr {
  def strStr(haystack: String, needle: String): Int = {
  var flag= -1
    if(haystack.isEmpty&&needle.isEmpty) return 0
    else if(haystack.isEmpty||needle.isEmpty) return flag
    for(i<-0 to haystack.size-needle.length){
    if(haystack(i)==needle(0)){
      var j=1
      while(j<needle.length&&needle(j)==haystack(j+i)){
        j+=1
      }
      if(j==needle.length) flag=j
      return flag
    }
    }
    flag
  }

  def main(args: Array[String]): Unit = {
println(strStr("",""))
  }
}
