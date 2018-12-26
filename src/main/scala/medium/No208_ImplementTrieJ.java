package medium;/**
 * Auther: lzy
 * Description:
 * Date Created by： 9:12 on 2018/12/25
 * Modified By：
 */

/**
 * @program: LeetCode
 * @description:
 * @author: Lzy
 * @create: 2018-12-25 09:12
 **/
public class No208_ImplementTrieJ {
    public static void main(String[] args) {
        String word = "word";
        No208_ImplementTrieJ obj = new No208_ImplementTrieJ();
        obj.insert(word);
        boolean param_2 = obj.search(word);
        boolean param_3 = obj.startsWith("wora");

        System.out.println(param_2);
        System.out.println(param_3);
    }

    class TrieNode {
        /**
         * 当前节点的值
         */
        public char val;
        /**
         * 当前节点是否为单词
         */
        public boolean isWord;
        public TrieNode[] children = new TrieNode[26];

        public TrieNode() {
        }

        TrieNode(char c) {
            TrieNode node = new TrieNode();
            node.val = c;
        }
    }

    private TrieNode root;

    /**
     * Initialize your data structure here.
     */
    public No208_ImplementTrieJ() {
        root = new TrieNode(' ');
    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        TrieNode ws = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            //c-'a' 返回字符c距离a的距离，如果是b距离为1
            if (ws.children[c - 'a'] == null) {
                ws.children[c - 'a'] = new TrieNode(c);
            }
            ws = ws.children[c - 'a'];
        }
        ws.isWord = true;
    }

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        TrieNode ws = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (ws.children[c - 'a'] == null) return false;
            ws = ws.children[c - 'a'];
        }
        return ws.isWord;
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    /**
     * create by: lzy
     * create time: 2018/12/25 10:22
     *
     * @return
     */
    public boolean startsWith(String prefix) {
        TrieNode ws = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (ws.children[c - 'a'] == null) return false;
            ws = ws.children[c - 'a'];
        }
        return true;
    }


}
