def No9_isPalindrome(x):
    """
    :type x: int
    :rtype: bool
    """
    if x < 0 or (x != 0 and x % 10 == 0):
        return false
    rev = 0
    while x > rev:
        rev = rev * 10 + x % 10
        x /= 10
    return x == rev or x == rev / 10
    # ret=[(len(x)-i) for i in range(len(x)+1)]
    # statuses=[[False]*len(x) for i in range(len(x))]
    # for i in range(len(x)-1,-1,-1):
    #     for j in range(i,len(x)):
    #         if(x[i]==x[j]) and (((j-i)<=1) or statuses[i+1][j-1]):
    #             statuses[i][j]=True
    #             ret[i]=min(ret[i],ret[j+1]+1)
    # return ret[0]-1


if __name__ == '__main__':
    print(No9_isPalindrome(123454321))
