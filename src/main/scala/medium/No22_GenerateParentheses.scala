package medium

import scala.collection.mutable.ListBuffer

/**
  * Auther: lzy
  * Description:
  * 给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。
  *
  * 例如，给出 n = 3，生成结果为：
  *
  * [
  * "((()))",
  * "(()())",
  * "(())()",
  * "()(())",
  * "()()()"
  * ]
  * Date Created by： 9:12 on 2018/12/11
  * Modified By：
  *
  * 解决思路：
  * 生成对应的括号对，那么左括号和右括号的数量是一样的，都是n
  *
  *
  *
  */

object No22_GenerateParentheses {
    def main(args: Array[String]): Unit = {
        println(generateParenthesis(3).mkString("\n"))
    }

    def generateParenthesis(n: Int): List[String] = {
        val result = ListBuffer[String]()
        generateOneByOne("", result, n, n)
         result.toList
    }

    def generateOneByOne(sublist: String, result: ListBuffer[String], left: Int, right: Int): Unit = {
        if (left == 0 && right == 0) result += (sublist)
        else {
            if (left > 0) generateOneByOne(sublist + "(", result, left - 1, right)
            if (right > left) generateOneByOne(sublist + ")", result, left, right - 1)
        }
    }

    /** *
      * 功能实现:
      * 回溯+剪枝
      * Author: Lzy
      * Date: 2018/12/12 9:27
      * Param: [n]
      * Return: scala.collection.immutable.List<java.lang.String>
      */
    def generateParenthesis2(n: Int): List[String] = {
        val result = ListBuffer[String]()
        generateOneByOne("", result, n, n)
        return result.toList
    }
}
