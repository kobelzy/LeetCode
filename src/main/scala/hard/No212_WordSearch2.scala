package hard

/**
  * Auther: lzy
  * Description:
  * Date Created by： 9:26 on 2018/12/27
  * Modified By：
  */

object No212_WordSearch2 {
    def main(args: Array[String]): Unit = {
        val words = Array[String]("oath", "pea", "eat", "rain")
        println(words.mkString("|"))

        val board = Array[Array[Char]](
            Array[Char]('o', 'a', 'a', 'n'),
            Array[Char]('e', 't', 'a', 'e'),
            Array[Char]('i', 'h', 'k', 'r'),
            Array[Char]('i', 'f', 'l', 'v')
        )
        val result=findWords(board,words)
        println(result)
    }


    var res = Set[String]()

    def findWords(board: Array[Array[Char]], words: Array[String]): List[String] = {
        val trie = new Trie()
        for (word <- words) trie.insert(word)
        val m = board.length
        val n = board(0).length

        val visited = Array.ofDim[Boolean](m, n)
        for (i <- 0 until m; j <- 0 until n) dfs(board, visited, "", i, j, trie)
        res.toList
    }

    def dfs(board: Array[Array[Char]], visited: Array[Array[Boolean]], str: String, x: Int, y: Int, trie: Trie): Unit = {
        if (x < 0 || x >= board.length || y < 0 || y >= board(0).length) return
        if (visited(x)(y)) return
        val newStr = str + board(x)(y)

        if (!trie.startsWith(newStr)) return

        if (trie.search(newStr)) res += newStr

        visited(x)(y) = true

        dfs(board, visited, newStr, x - 1, y, trie)
        dfs(board, visited, newStr, x + 1, y, trie)
        dfs(board, visited, newStr, x, y - 1, trie)
        dfs(board, visited, newStr, x, y + 1, trie)

        visited(x)(y) = false

    }
}
