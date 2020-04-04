class Solution:
    MAX = 2 ** 31 - 1
    MIN = -2 ** 31

    #          空格    数字     +、-     其他
    # start     start process  signed  start
    # signed    end   process  end      end
    # process   end   process  end      end
    def __init__(self):
        self.state = 'start'
        self.sign = 1
        self.ans = 0
        self.table = {
            'start': ['start', 'signed', 'in_number', 'end'],
            'signed': ['end', 'end', 'in_number', 'end'],
            'in_number': ['end', 'end', 'in_number', 'end'],
            'end': ['end', 'end', 'end', 'end']
        }

    def get_col(self, c):
        if c.isspace():
            return 0
        elif c == '+' or c == '-':
            return 1
        elif c.isdigit():
            return 2
        else:
            return 3

    def get_state(self, c):
        self.state = self.table[self.state][self.get_col(c)]
        if self.state == 'in_number':
            self.ans = self.ans * 10 + int(c)
            self.ans = min(self.ans, self.MAX) if self.sign == 1 else min(self.ans, -self.MIN)
        elif self.state == 'signed':
            self.sign = 1 if c == '+' else -1

    def myAtoi(self, str: str) -> int:
        for c in str:
            self.get_state(c)
        return self.sign * self.ans


if __name__ == '__main__':
    solution = Solution()
    print(solution.myAtoi("words and 987"))
