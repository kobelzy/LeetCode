import numpy as np
import pandas as pd
a=[[1,2,3],[4,5,6],[7,8,9]]
# b=np.stack(a,axis=0)
# print(b)
# d=b.reshape(1,9)
# print(d)
# print(d.reshape(9,-1))
# e=[['a'],['b'],['c']]
# print(np.hstack((a,e)).reshape(-1,1))
df=pd.DataFrame(a)
print(df)
df2=pd.DataFrame(a)
df3=pd.concat([df,df2]).value
print(df3)
