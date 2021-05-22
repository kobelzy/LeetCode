package queue;

import java.util.*;

/**
 * 127. 单词接龙
 * 字典 wordList 中从单词 beginWord 和 endWord 的 转换序列 是一个按下述规格形成的序列：
 *
 * 序列中第一个单词是 beginWord 。
 * 序列中最后一个单词是 endWord 。
 * 每次转换只能改变一个字母。
 * 转换过程中的中间单词必须是字典 wordList 中的单词。
 * 给你两个单词 beginWord 和 endWord 和一个字典 wordList ，找到从 beginWord 到 endWord 的 最短转换序列 中的 单词数目 。如果不存在这样的转换序列，返回 0。
 * 示例 1：
 * 输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
 * 输出：5
 * 解释：一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog", 返回它的长度 5。
 * 示例 2：
 * 输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
 * 输出：0
 * 解释：endWord "cog" 不在字典中，所以无法进行转换。
 * 提示：
 * 1 <= beginWord.length <= 10
 * endWord.length == beginWord.length
 * 1 <= wordList.length <= 5000
 * wordList[i].length == beginWord.length
 * beginWord、endWord 和 wordList[i] 由小写英文字母组成
 * beginWord != endWord
 * wordList 中的所有字符串 互不相同
 * @author: Patrick Star
 * @time: 2021/5/17 21:56
 */
public class No127_WordLadder {
    public static void main(String[] args) {
        No127_WordLadder t = new No127_WordLadder();
        List<String> list = new ArrayList<>(Arrays.asList("hot", "dot", "dog", "lot", "log", "cog"));
        List<String> list2 = new ArrayList<>(Arrays.asList("hot", "cog", "dog", "tot", "hog", "hop", "pot", "dot"));
        System.out.println(t.ladderLength2("hit", "cog", list));
        System.out.println(t.ladderLength2("hot", "dog", list)); //3
    }

    /**
     * 广度优先，单向遍历
     *
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> set = new HashSet<>(wordList);
        set.remove(beginWord);
        if (!set.contains(endWord)) return 0;
        Set<String> visited = new HashSet<>();
        int len = beginWord.length();
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        int step = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            step++;
            for (int i = 0; i < size; i++) {
                String cur = queue.poll();
                char[] chars = cur.toCharArray();
                for (int j = 0; j < len; j++) {
                    char originChar = chars[j];
                    for (char k = 'a'; k <= 'z'; k++) {
                        if (k == originChar) continue;
                        chars[j] = k;
                        String nextWord = String.valueOf(chars);
                        if (set.contains(nextWord)) {
                            if (nextWord.equals(endWord)) return step;
                            if (!visited.contains(nextWord)) {
                                queue.offer(nextWord);
                                visited.add(nextWord);
                            }
                        }
                        chars[j] = originChar;
                    }
                }

            }
        }
        return 0;
    }


    /**
     * 广度优先，双向遍历
     * 执行用时：16 ms, 在所有 Java 提交中击败了94.57%的用户
     * 内存消耗：39.4 MB, 在所有 Java 提交中击败了69.63%的用户
     *
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public int ladderLength2(String beginWord, String endWord, List<String> wordList) {
        Set<String> set = new HashSet<>(wordList);
        if (!set.contains(endWord)) return 0;
        int wordLen = beginWord.length();
        Set<String> visited = new HashSet<>();


        Set<String> beginVisited = new HashSet<>();
        beginVisited.add(beginWord);
        Set<String> endVisited = new HashSet<>();
        endVisited.add(endWord);

        int step = 1;
        while (!beginVisited.isEmpty() && !endVisited.isEmpty()) {
            step++;
            if (beginVisited.size() > endVisited.size()) {
                Set<String> temp = beginVisited;
                beginVisited = endVisited;
                endVisited = temp;
            }

            Set<String> nextLevelVisited = new HashSet<>();
            for (String word : beginVisited) {
                char[] charArray = word.toCharArray();
                for (int i = 0; i < wordLen; i++) {
                    char originChar = charArray[i];
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (originChar == c) continue;
                        charArray[i] = c;
                        String newWord = String.valueOf(charArray);
                        if (set.contains(newWord)) {
                            //注意这里的是用另外一个set来判断是否包含
                            if (endVisited.contains(newWord)) return step;
                            if (!visited.contains(newWord)) {
                                nextLevelVisited.add(newWord);
                                visited.add(newWord);
                            }
                        }
                        charArray[i] = originChar;
                    }
                }
            }

            beginVisited = nextLevelVisited;
        }
        return 0;
    }
}
