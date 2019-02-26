package hard

case class Node(key: Int, var value: Int, var next: Node = null, var pre: Node = null)

class No146_LRUCache2(capacity: Int) {
  val map = scala.collection.mutable.Map[Int, Node]()
  var head = Node(-1, -1)
  var tail = Node(-1, -1)

  def moveToTail(target: Node, isNew: Boolean = false) = {
    if (target != tail.next) {
      if (!isNew) {
        target.pre.next = target.next
        target.next.pre = target.pre
      }
      //添加新节点到链表尾部
      tail.next.next = target
      target.pre = tail.next
      tail.next = target
    }
  }


  def get(key: Int) = {
    map.get(key) match {
      case Some(target) =>
        moveToTail(target)
        tail.next.next = null
        target.value
      case None => -1
    }
  }

  def put(key: Int, value: Int) = {
    map.get(key) match {
      case Some(target) => {
        target.value = value
        map(key) = target
        moveToTail(target)
      }
      case None if map.size < capacity =>
        val newNode = Node(key, value)
        map.put(key, newNode)
        if (head.next == null) {
          head.next = newNode
          newNode.pre = head
          tail.next = newNode
        } else {
          moveToTail(newNode, isNew = true)
        }
      case None =>
        val newNode = Node(key, value)
        map -= head.next.key
        // cache中只有一个元素
        if (head.next == tail.next) {
          head.next = newNode
          tail.next = newNode
        } else {
          head.next.next.pre = head
          head.next = head.next.next
          moveToTail(newNode, isNew = true)
        }


    }

  }
}
