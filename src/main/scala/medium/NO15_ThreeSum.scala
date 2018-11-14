package medium

object NO15_ThreeSum {

    def main(args: Array[String]): Unit = {
        //    val nums = Array(-2, 0, 2, 2)
//        val nums = Array(0, -4, -1, -4, -2, -3, 2)
val nums = Array(-1, 0, 1, 2, -1, -4)
//    val nums = Array(-1, 0, 1, 2, -1, -4)
        println(threeSum(nums))
    }

    def threeSum(nums: Array[Int]): List[List[Int]] = {

        val result = scala.collection.mutable.ListBuffer[List[Int]]()
        val sortNums = nums.sorted
      val len=sortNums.length
        for (i <- 0 to len - 2 if sortNums(i)<=0) {
                if (i == 0 || sortNums(i) != sortNums(i - 1)) {

                var head = i + 1
                var last = len - 1

                /**
                  * 进行除了第一个元素外其他元素的循环，从两头向中间循环
                  */
                while (head < last) {
                    val s = sortNums(i) + sortNums(head) + sortNums(last)
                    //如果三个数的和小于0，那么说明head元素比较小
                    if (s < 0) head += 1
                    //如果s大于0，说明结尾元素比较大，需要减小，取其前边的元素
                    else if (s > 0) last -= 1
                    else {
                        //否则结果刚好为0，
                        result += List(sortNums(i), sortNums(head), sortNums(last))
                        //继续做魂环，如果下一个头元素和当前相等，那么跳过下一个，
                        while (head < last && sortNums(head) == sortNums(head + 1)) head += 1
                        //如果下一个尾元素和当前相等，则跳过下一个
                        while (head < last && sortNums(last) == sortNums(last - 1)) last -= 1
                        head += 1
                        last -= 1
                    }

                }
            }
        }
        result.toList
    }
}
