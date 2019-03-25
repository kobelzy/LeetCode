package medium

object No3_LongestSubstringWithoutRepeatingCharacters {
  def main(args: Array[String]): Unit = {
    val set=scala.collection.mutable.Set[Char]()
    set.add('a')
    println(set)
    set.remove('a')
    println(set)
  }
  def lengthOfLongestSubstring(s: String): Int = {

    val n=s.length
    val set=scala.collection.mutable.Set[Char]()
    var maxLen=0
    var i=0
    var j=0
    while(i<n && j<n){
      if(!set.contains(s.charAt(j))){
        set.add(s.charAt(j))
        j+=1
        maxLen=math.max(maxLen,j-i)
      }else{
        set.remove(s.charAt(i))
        i+=1
      }

    }
    maxLen
  }
}
