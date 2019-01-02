package hard

/**
  * Auther: lzy
  * Description:
  * Date Created by： 9:07 on 2018/12/28
  * Modified By：
  */

class Trie {
    class TrieNode() {
        def this(c: Char) {
            this()
            val node = new TrieNode()
            node.value = c
        }

        var value: Char = _;
        var isWord: Boolean = _
        val childern: Array[TrieNode] = new Array[TrieNode](26)
    }
    /** Initialize your data structure here. */

    var root: TrieNode = new TrieNode(' ')

    /** Inserts a word into the trie. */
    def insert(word: String) {
        var ws = root
        for (i <- word.indices) {
            val c: Char = word.charAt(i)
            if (ws.childern(c - 'a') == null) {
                ws.childern(c - 'a') = new TrieNode(c)
            }
            ws = ws.childern(c - 'a')
        }
        ws.isWord = true
    }

    /** Returns if the word is in the trie. */
    def search(word: String): Boolean = {
        var ws = root
        for (i <- word.indices) {
            val c = word.charAt(i)
            if (ws.childern(c - 'a') == null) return false
            ws = ws.childern(c - 'a')
        }
        ws.isWord
    }


    def startsWith(prefix: String): Boolean = {
        var ws = root
        for (i <- prefix.indices) {
            val c = prefix.charAt(i)
            if (ws.childern(c - 'a') == null) return false
            ws = ws.childern(c - 'a')
        }
        true
    }
}
