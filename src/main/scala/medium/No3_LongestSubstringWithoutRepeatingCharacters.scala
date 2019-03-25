package medium

/**
  * 3. 无重复字符的最长子串
  * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
  *
  * 示例 1:
  *
  * 输入: "abcabcbb"
  * 输出: 3
  * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
  * 示例 2:
  *
  * 输入: "bbbbb"
  * 输出: 1
  * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
  * 示例 3:
  *
  * 输入: "pwwkew"
  * 输出: 3
  * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
  * 请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
  */

object No3_LongestSubstringWithoutRepeatingCharacters {
  def main(args: Array[String]): Unit = {
    val set=scala.collection.mutable.Set[Char]()
    set.add('a')
    println(set)
    set.remove('a')
    println(set)
  }
  def lengthOfLongestSubstring(s: String): Int = {

    val len=s.length
    val set=scala.collection.mutable.Set[Char]()
    var maxLen=0
    var i=0
    var j=0
    while(i<len && j<len){
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
