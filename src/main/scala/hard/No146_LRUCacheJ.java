package hard;

import java.util.HashMap;

/**
 * Auther: Lzy
 * Description:
 * Date Created by： 10:34 on 2019/1/30
 * Modified By：
 */

public class No146_LRUCacheJ {
    public static void main(String[] args) {
        No146_LRUCacheJ cache=new No146_LRUCacheJ(2);
        System.out.println(cache.get(100));
        cache.set(100,200);
        System.out.println(cache.get(100));
        System.out.println();
    }
        private HashMap<Integer, CacheNode> map;
    private int capacity;
    // head.next和tail.next指向链表头尾，包起来防止null
    private CacheNode head = new CacheNode(-1, -1);
    private CacheNode tail = new CacheNode(-1, -1);

    // 缓存节点
    private class CacheNode {
        int key, value;
        CacheNode pre, next;
        CacheNode(int key, int value) {
            this.key = key;
            this.value = value;
            this.pre = null;
            this.next = null;
        }
    }

    public No146_LRUCacheJ(int capacity) {
        this.map = new HashMap<>();
        this.capacity = capacity;
    }

    // 将已有节点或新建节点移动到链表尾部
    private void moveToTail(CacheNode target, boolean isNew) {
        // 尾部节点显然不需要移动
        if (target != tail.next) {
            if (!isNew) {
                // 修改旧节点的双向链表指针
                target.pre.next = target.next;
                target.next.pre = target.pre;
            }
            // 添加节点到链表尾部
            tail.next.next = target;
            target.pre = tail.next;
            tail.next = target;
        }
    }

    // 命中节点添加到链表尾部，未命中返回-1
    public int get(int key) {
        if (map.containsKey(key)) {
            CacheNode target = map.get(key);
            // 将已有节点移动到链表尾部
            moveToTail(target, false);
            // 此时链表尾部tail.next = target，更新next指向null，防止出现环
            tail.next.next = null;
            return target.value;
        }
        return -1;
    }

    /**
     * 1. 节点命中，修改节点并移动到链表尾部tail.next
     * 2. 节点未命中，
     *    2.1 cache已满，删除链表头部head.next
     *    2.2 cache未满，新建节点并添加到链表尾部tail.next
     */
    public void set(int key, int value) {
        // cache中存在节点
        if (map.containsKey(key)) {
            CacheNode target = map.get(key);
            target.value = value;
            map.put(key, target);
            // 将访问过的已有节点移动到链表尾部
            moveToTail(target, false);
        } else if(map.size() < capacity) {    // cache未满，添加节点
            CacheNode newNode = new CacheNode(key, value);
            map.put(key, newNode);
            if (head.next == null) {
                head.next = newNode;
                newNode.pre = head;
                tail.next = newNode;
            } else {
                // 将新建节点移动到链表尾部
                moveToTail(newNode, true);
            }
        } else {    // cache已满，淘汰链表链表头部节点，新节点加入到链表尾部
            CacheNode newNode = new CacheNode(key, value);
            map.remove(head.next.key);
            map.put(key, newNode);
            // cache中只有一个元素
            if (head.next == tail.next) {
                head.next = newNode;
                tail.next = newNode;
            } else {    // cache中不止一个元素，删除头部
                head.next.next.pre = head; // 更新新头部.pre = head
                head.next = head.next.next;// 更新新头部
                // 将新建节点移动到链表尾部
                moveToTail(newNode, true);
            }
        }
    }

}
