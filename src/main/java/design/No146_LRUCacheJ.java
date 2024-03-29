package design;

import java.util.HashMap;

/**
 * Auther: Lzy
 * Description:
 * Date Created by： 10:34 on 2019/1/30
 * Modified By：
 */

public class No146_LRUCacheJ<K, V> {
    public static void main(String[] args) {
        No146_LRUCacheJ<Integer, Integer> cache = new No146_LRUCacheJ(2);
        System.out.println(cache.get(100));
        cache.put(100, 200);
        System.out.println(cache.get(100));
        System.out.println(cache.map);
        System.out.println(cache.get(100));
        System.out.println(cache.get(100));
    }

    private HashMap<K, CacheNode<K, V>> map;
    private int capacity;
    // head.next和tail.next指向链表头尾，包起来防止null
    private CacheNode head = new CacheNode(null, null);
    private CacheNode tail = new CacheNode(null, null);

    static class CacheNode<K, V> {
        private K key;
        private V value;
        CacheNode pre, next;

        CacheNode(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public No146_LRUCacheJ(int capacity) {
        this.map = new HashMap<>();
        this.capacity = capacity;
        head.next = tail;
        tail.pre = head;
    }

    public void put(K key, V value) {
        if (map.containsKey(key)) {
            CacheNode<K, V> node = map.get(key);
            node.value = value;
            moveToHead(node);
        } else {
            if (map.size() >= capacity) {
                CacheNode last = removeTail();
                map.remove(last.key);
            }
            CacheNode node = new CacheNode(key, value);
            addToHead(node);
            map.put(key, node);
        }
    }

    public V get(K key) {
        CacheNode<K, V> node = map.get(key);
        if (node == null) return null;
        moveToHead(node);
        return node.value;
    }

    public int size() {
        return map.size();
    }

    private void addToHead(CacheNode target) {

        target.pre = head;
        target.next = head.next;

        head.next.pre = target;
        head.next = target;
    }

    private void moveToHead(CacheNode target) {
        removeNode(target);
        addToHead(target);
    }

    private void removeNode(CacheNode target) {
        target.pre.next = target.next;
        target.next.pre = target.pre;
        target.pre = null;
        target.next = null;

    }

    private CacheNode removeTail() {
        CacheNode prev = tail.pre;
        removeNode(prev);
        return prev;
    }

}
