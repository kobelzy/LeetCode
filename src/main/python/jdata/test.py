import pandas as pd
from tqdm import tqdm

df = pd.DataFrame({'goods':['coke cola', 'eggplant','eggplant'], 'quantity':[12,3,3], 'price':[20,12,12]})
print(df)
# df[:2]['goods']='sss'
# print(df)

distinct=df.duplicated()
print(distinct)
distinct[0]=True
print(distinct)
train_month=3
# map=range({4: 30, 5: 31})
# print(map)
# for i in tqdm(range({4: 30, 5: 31}[train_month + 1])):
#     print(i)
#     pass

pbar = tqdm(["a", "b", "c", "d"])
for char in pbar:
    pbar.set_description("Processing %s" % char)

for i in tqdm(range(1000)):
     #do something
     pass