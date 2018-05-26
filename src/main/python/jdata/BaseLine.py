# 在此感谢 Herbert95 的 baseline代码, 本代码部分修改于Herbert95的baseline代码
###   https://github.com/Herbert95/JDATA_

#################### 这是一份娱乐版baseline 代码写得非常蠢, 大家随便看看就好 ######################
######################### 线上分数 ################
############### S1 = 0.4726 | S2 = 0.2133 ################
######################## S = 0.3158 #####################

import pandas as pd
import numpy as np
import datetime
import copy
import xgboost as xgb
from sklearn.model_selection import train_test_split
from tqdm import tqdm
import warnings

import sys

warnings.filterwarnings('ignore')
base_path = 'E:/dataset/JData_UserShop'
sku = pd.read_csv(base_path + '/jdata_sku_basic_info.csv', )
action = pd.read_csv(base_path + '/jdata_user_action.csv', parse_dates=['a_date'])
# 用户基本信息
basic_info = pd.read_csv(base_path + '/jdata_user_basic_info.csv')
comment_score = pd.read_csv(base_path + '/jdata_user_comment_score.csv', parse_dates=['comment_create_tm'])
order = pd.read_csv(base_path + '/jdata_user_order.csv', parse_dates=['o_date'])

order = pd.merge(order, sku, on='sku_id', how='left')
order = pd.merge(order, basic_info, on='user_id', how='left')
action = pd.merge(action, sku, on='sku_id', how='left')
order['o_month'] = order['o_date'].apply(lambda x: x.month)
action['a_month'] = action['a_date'].apply(lambda x: x.month)


# 构造用户订单画像表, 刻画每个用户一年内的订单数, 每个月的订单数
def make_user_order_table():
    train = pd.DataFrame()

    for month in [5, 6, 7, 8, 9, 10, 11, 12, 1, 2, 3, 4]:
        mon_data = order[order['o_month'] == month].sort_values(by=['o_date', 'user_id']).drop_duplicates()
        train = pd.concat([train, mon_data])
    # train_data = order[order['month'] == train_month][['user_id', 'o_date', 'cate']].sort_values(by=['user_id', 'o_date']).drop_duplicates()

    train = train.drop(train[train[['o_id']].duplicated()].index, axis=0)
    train = pd.merge(train, basic_info, on='user_id', how='left')

    ###################### 生成初步数据 #####################
    # train_data.to_csv('./data/train_dul.csv', index=False)
    ###################### 生成初步数据 #####################

    train['o_month'] = train['o_date'].apply(lambda x: x.month)
    train['o_day'] = train['o_date'].apply(lambda x: x.day)

    train_101 = train[train['cate'] == 101]
    train_30 = train[train['cate'] == 30]
    train = pd.concat([train_101, train_30])

    ##################### 生成用户年订单数 ####################
    users_pd = pd.DataFrame()
    g_users = train.groupby(['user_id'])
    all_users = g_users.groups.keys()
    users_pd['user_id'] = all_users
    users_pd['year_user_orders'] = g_users.agg(['count']).values[:, :1].flatten().tolist()
    ##################### 生成用户年订单数 ####################

    ##################### 生成用户月订单数 ####################
    g = train.groupby(['o_month'])

    user_months = {}
    for x, group in g:
        print(x)
        user_months[x] = group.groupby('user_id').agg(['count']).values[:, :1].flatten().tolist()
        print(len(group.groupby('user_id').agg(['count']).values[:, :1].flatten().tolist()))
        print(len(group.groupby('user_id').groups.keys()))
        mon = 'mon_%s_user_orders' % str(x)
        tmp_pd = pd.DataFrame()
        tmp_pd[mon] = group.groupby('user_id').agg(['count']).values[:, :1].flatten().tolist()
        tmp_pd['user_id'] = group.groupby('user_id').groups.keys()
        user_months[x] = tmp_pd
    ##################### 生成用户月订单数 ####################

    # 融合进主订单表里
    train = pd.merge(train, users_pd, on='user_id', how='left')
    for index, m in enumerate(user_months):
        train = pd.merge(train, user_months[m], on='user_id', how='left')

    train = train.replace({np.nan: 0.0})

    train.to_csv(base_path+'/processed_data/user_order_table.csv', index=False)
    print(train.head())


if __name__ == '__main__':
    make_user_order_table()

    new_order = pd.read_csv(base_path+'/processed_data/user_order_table.csv', parse_dates=['o_date'])
    new_order = new_order.sort_values(by=['user_id'])

    new_order = new_order.drop(new_order[new_order[['user_id']].duplicated()].index, axis=0)
    new_order['mon_1234_user_orders'] = new_order['mon_4_user_orders'] + new_order['mon_3_user_orders'] + new_order[
        'mon_2_user_orders'] + new_order['mon_1_user_orders']
    # order = order[order['mon_1234_user_orders'] != 0]

    new_order = new_order.sort_values(
        by=['year_user_orders', 'mon_4_user_orders', 'mon_3_user_orders', 'mon_2_user_orders', 'mon_1_user_orders'],
        ascending=False)
    print(new_order.head())
    print(new_order['year_user_orders'][:50000])
    print(new_order['mon_4_user_orders'][:50000])
    print(new_order['mon_3_user_orders'][:50000])
    print(new_order['mon_2_user_orders'][:50000])
    print(new_order['mon_1_user_orders'][:50000])
    print(np.sum((new_order['mon_4_user_orders'][:50000] + new_order['mon_3_user_orders'][:50000] + new_order[
                                                                                                        'mon_2_user_orders'][
                                                                                                    :50000] + new_order[
                                                                                                                  'mon_1_user_orders'][
                                                                                                              :50000]) == 0))
    # 用前15天随机出时间
    times = [datetime.datetime(2017, 5, (i % 15) + 1) for i in range(50000)]

    test = pd.DataFrame()
    # 根据年购买数量排序后截取前50000个user
    test['user_id'] = new_order['user_id'][:50000]
    test['pred_date'] = times

    test.to_csv('./test.csv', index=False)
    print(test)
