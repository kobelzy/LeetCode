package esay

/**
  * Created by Administrator on 2017/11/23.
  */
object No20_IsValid {
//'(', ')', '{', '}', '[',']'

  def isValid(s: String): Boolean = {
    var flag = true
    val symbols = Map[Char, Char]('(' -> ')', '{' -> '}', '[' -> ']')
    val stringBySymbol: String = for (char <- s if char == '(' || char == ')' || char == '{' || char == '}' || char == '[' || char == ']') yield char
    val list = scala.collection.mutable.ListBuffer[Char]()
    for (symbol <- stringBySymbol) {
      if (symbols.keySet.contains(symbol)) {
        list += symbol
      } else if (symbols.values.toSet.contains(symbol)) {
        if (list.nonEmpty && symbols(list.last) == symbol) {
          list.remove(list.size - 1)
//          list-= list.last //如果有相同的符号，那么会从第一个开始删，而不是最后一个，会发生错误、
        } else {
          flag = false
        }
      }
    }
    if (list.nonEmpty) flag = false
    flag
  }

  def main(args: Array[String]): Unit = {
    println(isValid("[([]])"))
  }
}
