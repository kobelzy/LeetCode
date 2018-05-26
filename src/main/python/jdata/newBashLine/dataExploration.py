from collections import Counter
import numpy as np
np.set_printoptions(suppress=True)

FILE_jdata_sku_basic_info='../data/jdata_sku_basic_info.csv'
FILE_jdata_user_action='../data/jdata_user_action.csv'
FILE_jdata_user_basic_info='../data/jdata_user_basic_info.csv'
FILE_jdata_user_comment_score='../data/jdata_user_comment_score.csv'
FILE_jdata_user_order='../data/jdata_user_order.csv'

#1、FILE_jdata_sku_basic_info analysis
# df_sku_info = pd.read_csv(FILE_jdata_sku_basic_info)
#print(df_sku_info['price'].describe())
#count = Counter(df_sku_info['cate'])
# count2 = Counter(df_sku_info['para_3'])
# print(df_sku_info['para_1'].describe())
# print(count2)

#2、FILE_jdata_user_basic_info analysis
# df_user_info = pd.read_csv(FILE_jdata_user_basic_info)
# print(df_user_info.describe())
# count = Counter(df_user_info['age'])
# print(count)
# count = Counter(df_user_info['user_lv_cd'])
# print(count)

#3、FILE_jdata_user_action analysis
# df_user_action = pd.read_csv(FILE_jdata_user_action)
# print(df_user_action.describe())
# count = Counter(df_user_action['a_type'])
# print(count)
# print(df_user_action['a_date'].max())

#4、FILE_jdata_user_order analysis
# df_user_order = pd.read_csv(FILE_jdata_user_order)
# print(df_user_order)

#5、FILE_jdata_user_comment_score analysis
# df_user_comment = pd.read_csv(FILE_jdata_user_comment_score)
# print(df_user_comment)