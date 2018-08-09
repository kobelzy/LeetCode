import pandas as pd
import numpy as np
from util import feat_nunique,feat_count

basePath='E:/dataset/JData_UserShop'
# sku_info = pd.read_csv(r'../data/jdata_sku_basic_info.csv')
# user_action = pd.read_csv(r'../data/jdata_user_action.csv')
user_info = pd.read_csv(basePath+'/jdata_user_basic_info_test.csv')
# user_comment = pd.read_csv(r'../data/jdata_user_comment_score.csv')
user_order = pd.read_csv(basePath+'/jdata_user_order_test.csv')
# result=feat_nunique(user_info,user_order,['user_id'],'o_id','index')
# print(result)
#
# result2=feat_count(user_info,user_order,['user_id'],'o_id','index')
# print(result2)
# df_nunique = pd.DataFrame(user_order.groupby('user_id')['o_id'].nunique()).reset_index()
# print(df_nunique)
# df_count = pd.DataFrame(user_order.groupby('user_id').count()).reset_index()
# print(df_count)

# print(user_order.sort_values('o_date'))
# print(user_order.sort_values('o_date').drop_duplicates(["user_id"], keep="first"))
print(user_order)
# print(user_info.shape[0])
s2=np.round(user_order.loc[1,'o_id'])
print(s2)