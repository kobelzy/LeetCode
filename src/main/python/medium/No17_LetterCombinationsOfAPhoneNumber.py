"""
17. 电话号码的字母组合
给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。

给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
示例:

输入："23"
输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
说明:
尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。
"""
from typing import List
class Solution:
    #回溯方法
    def letterCombinations( digits: str) -> List[str]:
        map={}
        map['2']=['a','b','c']
        map['3']=['d','e','f']
        map['4']=['g','h','i']
        map['5']=['j','k','l']
        map['6']=['m','n','o']
        map['7']=['p','q','r','s']
        map['8']=['t','u','v']
        map['9']=['w','x','y','z']

        def backtrack(combination,next_digits):
            if len(next_digits)==0:
                output.append(combination)
            else:
                for letter in map[next_digits[0]]:
                    backtrack(combination+letter,next_digits[1:])
        output=[]
        if digits:
            backtrack('',digits)
        return output
    if __name__ == '__main__':
        print(letterCombinations('23'))