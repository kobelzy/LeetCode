package hard


/**
  * 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制。它应该支持以下操作： 获取数据 get 和 写入数据 put 。
  *
  * 获取数据 get(key) - 如果密钥 (key) 存在于缓存中，则获取密钥的值（总是正数），否则返回 -1。
  * 写入数据 put(key, value) - 如果密钥不存在，则写入其数据值。当缓存容量达到上限时，它应该在写入新数据之前删除最近最少使用的数据值，从而为新的数据值留出空间。
  *
  * 进阶:
  *
  * 你是否可以在 O(1) 时间复杂度内完成这两种操作？
  *
  * 示例:
  *
  * LRUCache cache = new LRUCache( 2 /* 缓存容量 */ ))
  *
  * cache.put(1, 1))
  * cache.put(2, 2))
  * cache.get(1))       // 返回  1
  * cache.put(3, 3))    // 该操作会使得密钥 2 作废
  * cache.get(2))       // 返回 -1 (未找到)
  * cache.put(4, 4))    // 该操作会使得密钥 1 作废
  * cache.get(1))       // 返回 -1 (未找到)
  * cache.get(3))       // 返回  3
  * cache.get(4))       // 返回  4
  *
  * Auther: Lzy
  * Date Created by： 8:50 on 2019/1/25
  */

// TODO: 还有N多问题需要修改
object No146_LRUCache {


    def apply(capacity: Int): No146_LRUCache = new No146_LRUCache(capacity)

    def main(args: Array[String]): Unit = {
        val head = Node(-1, -1)
        val tail = Node(-1, -1)
        head.next = tail
        tail.pre=head
//        println(head)
//        println(tail)
        val cache = new No146_LRUCache(1 /* 缓存容量 */)

        println(cache.get(0))
        println("head:" + cache.head.key, cache.tail.key);

        println(cache.put(2, 1))
        println("head:" + cache.head.key, cache.tail.key);

        println(cache.get(2))
        println("head:" + cache.head.key, cache.tail.key);


        println(cache.put(1, 1))
        println("head:" + cache.head.key, cache.tail.key);

        println(cache.put(2, 2))
        println("head:" + cache.head.key, cache.tail.key);
        println(cache.hm.keys.toList)
        println(cache.get(1))       // 返回  1
        println("head:" + cache.head.key, cache.tail.key);
        println(cache.hm.keys.toList)
        println(cache.put(3, 3))    // 该操作会使得密钥 2 作废
        println("head:" + cache.head.key, cache.tail.key);
        println(cache.hm.keys.toList)
        println(cache.get(2))       // 返回 -1 (未找到)
        println("head:" + cache.head.key, cache.tail.key);
        println(cache.hm.keys.toList)
        println(cache.put(4, 4))    // 该操作会使得密钥 1 作废
        println("head:" + cache.head.key, cache.tail.key);
        println(cache.hm.keys.toList)
        println(cache.get(1))       // 返回 -1 (未找到)
        println(cache.get(3))       // 返回  3
        println(cache.get(4))       // 返回  4

    }
}

/*
维护了两种数据结构：
Map：用于数据查询与存储
双向List：用于维护node关系，经常查询的保持在头端，不常被查询的逐步剔除
 */
case class Node(key: Int, var value: Int, var next: Node=null, var pre:Node=null)

class No146_LRUCache(_capacity: Int) {
    var count = 2
    //互相引用会导致栈溢出，再打印会栈溢出
    var head = Node(-1, -1)
    var tail = Node(-1, -1, null, head)
    head.next = tail
    val hm = scala.collection.mutable.Map(head.key -> head, tail.key -> tail)
    if(_capacity==1) head=tail


    def get(key: Int): Int = {
        if (hm.contains(key)) {
            val node = hm(key)
            detachNode(node)
            insertToHead(node)
            node.value
        } else -1
    }

    def put(key: Int, value: Int) {
        hm.get(key) match {
            case Some(node) =>
                node.value = value
                detachNode(node)
                insertToHead(node)
            case None =>
                val newNode = Node(key, value)
                insertToHead(newNode)
                hm += key -> newNode
                this.count += 1
                if (this.count >= _capacity) removeNode()
        }
    }

    /** 将节点放到缓存 头部 */
    def insertToHead(node: Node) = {
        this.head.pre = node
        node.next = this.head
        node.pre = null
        this.head = node
    }

    /** 删除指定node */
    def detachNode(node: Node) = {
        if (node == this.head) {
            this.head = node.next
            node.next.pre = null
        } else if (node == this.tail) {
            this.tail = node.pre
            node.pre.next = null
        } else {
            node.pre.next = node.next
            node.next.pre = node.pre
        }

//        if (node.next.isDefined) {//如果节点的下一个节点不为空
//            //如果节点的pre不为空，将上一个节点直接和下一个节点相连
//            if (node.pre.isDefined) node.pre.get.next = node.next
//            //节点的next和pre相连
//            node.next.get.pre = node.pre
//        } else {
//            node.pre.get.next = None
//        }
    }

    /** 删除最后一个节点 */
    def removeNode() = {
        val tailKey = this.tail.key
        hm -= tailKey
        this.tail = this.tail.pre
        this.tail.next = null
        count -= 1
    }


    def print = {
        println("\nPRINT CACHE ------ ")
        println("count: " + count)
        println("From head:")
        var p = this.head

        while (p != null) {
            println("key: " + p.key + " value: " + p.value)
            p = p.next
        }
        println("From tail:")
        p = this.tail
        while (p != null) {
            println("key: " + p.key + " value: " + p.value)
            p = p.pre
        }
    }
}



