package medium

/**
  * Auther: lzy
  * Description:
  * 实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作。
  *
  * 示例:
  *
  * Trie trie = new Trie();
  *
  * trie.insert("apple");
  * trie.search("apple");   // 返回 true
  * trie.search("app");     // 返回 false
  * trie.startsWith("app"); // 返回 true
  * trie.insert("app");
  * trie.search("app");     // 返回 true
  * Date Created by： 9:06 on 2018/12/25
  * Modified By：
  */

object No208_ImplementTrie {
    def main(args: Array[String]): Unit = {
        val c = 'c'
        println(c - 'a')
    }
}

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

class No208_ImplementTrie {
//        var obj = new No208_ImplementTrie()
//        obj.insert(word)
//        var param_2 = obj.search(word)
//        var param_3 = obj.startsWith(prefix)
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

class Other {

    /**
      * 前缀树的节点,因为要判断startWith和search,需要一个isWord来确定当前节点是否为一个已插入的单词
      * 一个TrieNode数组保存所有子节点,默认为空
      */
    class TrieNode {
        var isWord = false //默认都不是单词节点,因为一个单词下来只有最后一个字符对应的节点才是单词节点
        val next = new Array[TrieNode](26)
    }

    /** Initialize your data structure here. */
    val root = new TrieNode //根节点

    /** Inserts a word into the trie. */
    def insert(word: String) {
        def insert(w: String, index: Int, cur: TrieNode): Unit = if (index == w.length) cur.isWord = true else {
            val nextIndex = w.charAt(index) - 'a'
            if (cur.next(nextIndex) == null) cur.next(nextIndex) = new TrieNode //如果对应前缀还没有建立子节点就建立一个
            insert(w, index + 1, cur.next(nextIndex))
        }

        insert(word, 0, root)
    }

    /*
     * search和startWith其实很多重复的逻辑,都需要对输入的参数字符串依次找节点,
     * 不同在于,serach需要找到对应的所有节点,而且最后一个节点必须是单词节点;
     * 而startsWith只要求找到所有节点,最后一个节点是否单词节点并不关心
     * 因此可以提取一个find函数来负责递归查找
     */
    def find(key: String): TrieNode = {
        def find(key: String, index: Int, cur: TrieNode): TrieNode =
            if (index == key.length || cur == null) cur else find(key, index + 1, cur.next(key.charAt(index) - 'a'))

        find(key, 0, root)
    }

    /** Returns if the word is in the trie. */
    def search(word: String): Boolean = {
        val result = find(word)
        result != null && result.isWord
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    def startsWith(prefix: String): Boolean = {
        find(prefix) != null
    }
}

/**
  * Your Trie object will be instantiated and called as such:
  * var obj = new Trie()
  * obj.insert(word)
  * var param_2 = obj.search(word)
  * var param_3 = obj.startsWith(prefix)
  */


}
