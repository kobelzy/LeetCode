package design;

import java.util.HashMap;

public class LRUCache {
    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));
        cache.put(3, 3);
        System.out.println(cache.get(2));
        cache.put(4, 4);
        System.out.println(cache.get(1));
        System.out.println(cache.get(3));
        System.out.println(cache.get(4));

    }

    private HashMap<Integer, CacheNode> map;
    private int capacity;
    // head.next和tail.next指向链表头尾，包起来防止null
    private CacheNode head = new CacheNode(null, null);
    private CacheNode tail = new CacheNode(null, null);

    static class CacheNode {
        private Integer key;
        private Integer value;
        CacheNode pre, next;

        CacheNode(Integer key, Integer value) {
            this.key = key;
            this.value = value;
        }
    }

    public LRUCache(int capacity) {
        this.map = new HashMap<>();
        this.capacity = capacity;
        head.next = tail;
        tail.pre = head;
    }

    public void put(Integer key, Integer value) {
        if (map.containsKey(key)) {
            CacheNode node = map.get(key);
            node.value = value;
            moveToHead(node);
        } else {
            CacheNode node = new CacheNode(key, value);
            if (map.size() >= capacity) {
                CacheNode last = removeTail();
                map.remove(last.key);
            }

            addToHead(node);
            map.put(key, node);
        }
    }

    public Integer get(Integer key) {
        System.out.println(map);
        CacheNode node = map.get(key);
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
