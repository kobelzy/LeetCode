# -*- coding: utf-8 -*-
# author：Cookly
import pandas as pd
import numpy as np
from datetime import datetime
import lightgbm as lgb





class DataLoader(object):
    def __init__(self,
                 FILE_jdata_sku_basic_info,
                 FILE_jdata_user_action,
                 FILE_jdata_user_basic_info,
                 FILE_jdata_user_comment_score,
                 FILE_jdata_user_order
                 ):
        self.FILE_jdata_sku_basic_info = FILE_jdata_sku_basic_info
        self.FILE_jdata_user_action = FILE_jdata_user_action
        self.FILE_jdata_user_basic_info = FILE_jdata_user_basic_info
        self.FILE_jdata_user_comment_score = FILE_jdata_user_comment_score
        self.FILE_jdata_user_order = FILE_jdata_user_order

        self.df_sku_info = pd.read_csv(self.FILE_jdata_sku_basic_info)
        self.df_user_action = pd.read_csv(self.FILE_jdata_user_action)
        self.df_user_info = pd.read_csv(self.FILE_jdata_user_basic_info)
        self.df_user_comment = pd.read_csv(self.FILE_jdata_user_comment_score)
        self.df_user_order = pd.read_csv(self.FILE_jdata_user_order)

        # change date2datetime
        self.df_user_action['a_date'] = pd.to_datetime(self.df_user_action['a_date'])
        self.df_user_order['o_date'] = pd.to_datetime(self.df_user_order['o_date'])
        self.df_user_comment['comment_create_tm'] = pd.to_datetime(self.df_user_comment['comment_create_tm'])

        # sort by datetime
        self.df_user_action = self.df_user_action.sort_values(['user_id', 'a_date'])
        self.df_user_order = self.df_user_order.sort_values(['user_id', 'o_date'])
        self.df_user_comment = self.df_user_comment.sort_values(['user_id', 'comment_create_tm'])

        # year month day
        self.df_user_order['year'] = self.df_user_order['o_date'].dt.year
        self.df_user_order['month'] = self.df_user_order['o_date'].dt.month
        self.df_user_order['day'] = self.df_user_order['o_date'].dt.day

        self.df_user_action['year'] = self.df_user_action['a_date'].dt.year
        self.df_user_action['month'] = self.df_user_action['a_date'].dt.month
        self.df_user_action['day'] = self.df_user_action['a_date'].dt.day


class Features(object):
    def __init__(self,
                 DataLoader,
                 PredMonthBegin,
                 PredMonthEnd,
                 FeatureMonthList,
                 MakeLabel=True
                 ):
        self.DataLoader = DataLoader
        self.PredMonthBegin = PredMonthBegin
        self.PredMonthEnd = PredMonthEnd
        self.FeatureMonthList = FeatureMonthList
        self.MakeLabel = MakeLabel

        # label columns
        self.LabelColumns = ['Label_30_101_BuyNum', 'Label_30_101_FirstTime']
        self.IDColumns = ['user_id']

        # merge feature table
        # Order Comment User Sku
        # print(self.DataLoader.df_user_order.head())
        # print(self.DataLoader.df_user_info.head())
        self.df_Order_Comment_User_Sku = self.DataLoader.df_user_order. \
            merge(self.DataLoader.df_user_comment, on=['user_id', 'o_id'], how='left'). \
            merge(self.DataLoader.df_user_info, on='user_id', how='left'). \
            merge(self.DataLoader.df_sku_info, on='sku_id', how='left')
        # Action User Sku
        self.df_Action_User_Sku = self.DataLoader.df_user_action. \
            merge(self.DataLoader.df_user_info, on='user_id', how='left'). \
            merge(self.DataLoader.df_sku_info, on='sku_id', how='left')

        # Make Label
        self.data_BuyOrNot_FirstTim = self.MakeLabel_()

        # MakeFeature_Order_Comment_
        for FeatureMonthBegin, FeatureMonthEnd, month in FeatureMonthList:
            self.MakeFeature_Order_Comment_(
                FeatureMonthBegin=FeatureMonthBegin,
                FeatureMonthEnd=FeatureMonthEnd,
                BetweenFlag='OM' + str(month) + '_'
            )

        # MakeFeature_Action_
        for FeatureMonthBegin, FeatureMonthEnd, month in FeatureMonthList:
            self.MakeFeature_Action_(
                FeatureMonthBegin=FeatureMonthBegin,
                FeatureMonthEnd=FeatureMonthEnd,
                BetweenFlag='AM' + str(month) + '_'
            )

        self.TrainColumns = [col for col in self.data_BuyOrNot_FirstTime.columns if
                             col not in self.IDColumns + self.LabelColumns]

    def MakeLabel_(self):
        self.data_BuyOrNot_FirstTime = self.DataLoader.df_user_info

        if self.MakeLabel:
            df_user_order_sku = self.DataLoader.df_user_order.merge(self.DataLoader.df_sku_info, on='sku_id',
                                                                    how='left')

            label_temp_ = df_user_order_sku[(df_user_order_sku['o_date'] >= self.PredMonthBegin) & \
                                            (df_user_order_sku['o_date'] <= self.PredMonthEnd)]

            label_temp_30_101 = label_temp_[(label_temp_['cate'] == 30) | (label_temp_['cate'] == 101)]

            # 统计用户当月下单数 回归建模
            BuyOrNotLabel_30_101 = label_temp_30_101.groupby(['user_id'])['o_id']. \
                nunique(). \
                reset_index(). \
                rename(columns={'user_id': 'user_id', 'o_id': 'Label_30_101_BuyNum'})

            # 用户首次下单时间 回归建模
            # keep first 获得首次下单时间 - 月初时间 = 下单在当月第几天购买
            FirstTimeLabel_30_101 = label_temp_30_101. \
                drop_duplicates('user_id', keep='first')[['user_id', 'o_date']]. \
                rename(columns={'user_id': 'user_id', 'o_date': 'Label_30_101_FirstTime'})
            FirstTimeLabel_30_101['Label_30_101_FirstTime'] = (
            FirstTimeLabel_30_101['Label_30_101_FirstTime'] - self.PredMonthBegin).dt.days

            # FirstTimeLabel_30_101 = label_temp_30_101.groupby(['user_id'])['day'].\
            #										min().\
            #										reset_index().\
            #										rename(columns={'user_id':'user_id','day':'Label_30_101_FirstTime'})

            # merge label
            self.data_BuyOrNot_FirstTime = self.data_BuyOrNot_FirstTime.merge(BuyOrNotLabel_30_101, on='user_id',
                                                                              how='left'). \
                merge(FirstTimeLabel_30_101, on='user_id', how='left')
            # fillna 0
            self.data_BuyOrNot_FirstTime.fillna(0, inplace=True)
        else:
            self.data_BuyOrNot_FirstTime['Label_30_101_BuyNum'] = -1
            self.data_BuyOrNot_FirstTime['Label_30_101_FirstTime'] = -1

        return self.data_BuyOrNot_FirstTime

    def MakeFeature_Order_Comment_(self,
                                   FeatureMonthBegin,
                                   FeatureMonthEnd,
                                   BetweenFlag
                                   ):
        # Order Comment User Sku
        '''
        self.df_Order_Comment_User_Sku
        user_id sku_id o_id o_date o_area o_sku_num comment_create_tm score_level age sex user_lv_cd price cate para_1 para_2 para_3
        '''
        features_temp_Order_ = self.df_Order_Comment_User_Sku[
            (self.df_Order_Comment_User_Sku['o_date'] >= FeatureMonthBegin) & \
            (self.df_Order_Comment_User_Sku['o_date'] <= FeatureMonthEnd)]

        # make features
        #################################################################################################################
        # o_id cate 30 101 订单数
        features_temp_ = \
        features_temp_Order_[(features_temp_Order_['cate'] == 30) | (features_temp_Order_['cate'] == 101)]. \
            groupby(['user_id'])['o_id']. \
            nunique(). \
            reset_index(). \
            rename(columns={'user_id': 'user_id', 'o_id': BetweenFlag + 'o_id_cate_30_101_cnt'})
        self.data_BuyOrNot_FirstTime = self.data_BuyOrNot_FirstTime.merge(features_temp_, on=['user_id'], how='left')

        # o_id cate 30 订单数
        features_temp_ = features_temp_Order_[(features_temp_Order_['cate'] == 30)]. \
            groupby(['user_id'])['o_id']. \
            nunique(). \
            reset_index(). \
            rename(columns={'user_id': 'user_id', 'o_id': BetweenFlag + 'o_id_cate_30_cnt'})
        self.data_BuyOrNot_FirstTime = self.data_BuyOrNot_FirstTime.merge(features_temp_, on=['user_id'], how='left')

        # o_id cate 101 订单数
        features_temp_ = features_temp_Order_[(features_temp_Order_['cate'] == 101)]. \
            groupby(['user_id'])['o_id']. \
            nunique(). \
            reset_index(). \
            rename(columns={'user_id': 'user_id', 'o_id': BetweenFlag + 'o_id_cate_101_cnt'})
        self.data_BuyOrNot_FirstTime = self.data_BuyOrNot_FirstTime.merge(features_temp_, on=['user_id'], how='left')

        # o_id cate 非 30 101 订单数
        features_temp_ = \
        features_temp_Order_[(features_temp_Order_['cate'] != 30) & (features_temp_Order_['cate'] != 101)]. \
            groupby(['user_id'])['o_id']. \
            nunique(). \
            reset_index(). \
            rename(columns={'user_id': 'user_id', 'o_id': BetweenFlag + 'o_id_cate_other_cnt'})
        self.data_BuyOrNot_FirstTime = self.data_BuyOrNot_FirstTime.merge(features_temp_, on=['user_id'], how='left')

        #################################################################################################################
        # sku_id cate 30 101 购买商品次数
        features_temp_ = \
        features_temp_Order_[(features_temp_Order_['cate'] == 30) | (features_temp_Order_['cate'] == 101)]. \
            groupby(['user_id'])['sku_id']. \
            count(). \
            reset_index(). \
            rename(columns={'user_id': 'user_id', 'sku_id': BetweenFlag + 'sku_id_cate_30_101_cnt'})
        self.data_BuyOrNot_FirstTime = self.data_BuyOrNot_FirstTime.merge(features_temp_, on=['user_id'], how='left')

        # sku_id cate 30 购买商品次数
        features_temp_ = features_temp_Order_[(features_temp_Order_['cate'] == 30)]. \
            groupby(['user_id'])['sku_id']. \
            count(). \
            reset_index(). \
            rename(columns={'user_id': 'user_id', 'sku_id': BetweenFlag + 'sku_id_cate_30_cnt'})
        self.data_BuyOrNot_FirstTime = self.data_BuyOrNot_FirstTime.merge(features_temp_, on=['user_id'], how='left')

        # sku_id cate 101 购买商品次数
        features_temp_ = features_temp_Order_[(features_temp_Order_['cate'] == 101)]. \
            groupby(['user_id'])['sku_id']. \
            count(). \
            reset_index(). \
            rename(columns={'user_id': 'user_id', 'sku_id': BetweenFlag + 'sku_id_cate_101_cnt'})
        self.data_BuyOrNot_FirstTime = self.data_BuyOrNot_FirstTime.merge(features_temp_, on=['user_id'], how='left')

        # sku_id cate 非 30 101 购买商品次数
        features_temp_ = \
        features_temp_Order_[(features_temp_Order_['cate'] != 30) & (features_temp_Order_['cate'] != 101)]. \
            groupby(['user_id'])['sku_id']. \
            count(). \
            reset_index(). \
            rename(columns={'user_id': 'user_id', 'sku_id': BetweenFlag + 'sku_id_cate_other_cnt'})
        self.data_BuyOrNot_FirstTime = self.data_BuyOrNot_FirstTime.merge(features_temp_, on=['user_id'], how='left')

        #################################################################################################################
        # o_date cate 30 101 购买天数
        features_temp_ = \
        features_temp_Order_[(features_temp_Order_['cate'] == 30) | (features_temp_Order_['cate'] == 101)]. \
            groupby(['user_id'])['o_date']. \
            nunique(). \
            reset_index(). \
            rename(columns={'user_id': 'user_id', 'o_date': BetweenFlag + 'o_date_cate_30_101_nuique'})
        self.data_BuyOrNot_FirstTime = self.data_BuyOrNot_FirstTime.merge(features_temp_, on=['user_id'], how='left')

        #################################################################################################################
        # o_sku_num cate 30 101 用户购买件数
        features_temp_ = \
        features_temp_Order_[(features_temp_Order_['cate'] == 30) | (features_temp_Order_['cate'] == 101)]. \
            groupby(['user_id'])['o_sku_num']. \
            sum(). \
            reset_index(). \
            rename(columns={'user_id': 'user_id', 'o_sku_num': BetweenFlag + 'o_sku_num_cate_30_101_sum'})
        self.data_BuyOrNot_FirstTime = self.data_BuyOrNot_FirstTime.merge(features_temp_, on=['user_id'], how='left')

        '''
        继续添加
        '''

        # 时间特征 购买时间间隔
        #################################################################################################################
        # 第一次购买时间
        features_temp_ = \
        features_temp_Order_[(features_temp_Order_['cate'] == 30) | (features_temp_Order_['cate'] == 101)]. \
            groupby(['user_id'])['day']. \
            min(). \
            reset_index(). \
            rename(columns={'user_id': 'user_id', 'day': BetweenFlag + 'day_cate_30_101_firstday'})
        self.data_BuyOrNot_FirstTime = self.data_BuyOrNot_FirstTime.merge(features_temp_, on=['user_id'], how='left')

        features_temp_ = features_temp_Order_[(features_temp_Order_['cate'] == 30)]. \
            groupby(['user_id'])['day']. \
            min(). \
            reset_index(). \
            rename(columns={'user_id': 'user_id', 'day': BetweenFlag + 'day_cate_30_firstday'})
        self.data_BuyOrNot_FirstTime = self.data_BuyOrNot_FirstTime.merge(features_temp_, on=['user_id'], how='left')

        features_temp_ = features_temp_Order_[(features_temp_Order_['cate'] == 101)]. \
            groupby(['user_id'])['day']. \
            min(). \
            reset_index(). \
            rename(columns={'user_id': 'user_id', 'day': BetweenFlag + 'day_cate_101_firstday'})
        self.data_BuyOrNot_FirstTime = self.data_BuyOrNot_FirstTime.merge(features_temp_, on=['user_id'], how='left')
        #################################################################################################################
        # 最后一次购买时间
        features_temp_ = \
        features_temp_Order_[(features_temp_Order_['cate'] == 30) | (features_temp_Order_['cate'] == 101)]. \
            groupby(['user_id'])['day']. \
            max(). \
            reset_index(). \
            rename(columns={'user_id': 'user_id', 'day': BetweenFlag + 'day_cate_30_101_lastday'})
        self.data_BuyOrNot_FirstTime = self.data_BuyOrNot_FirstTime.merge(features_temp_, on=['user_id'], how='left')

        #################################################################################################################
        # 购买当月平均第几天
        features_temp_ = \
        features_temp_Order_[(features_temp_Order_['cate'] == 30) | (features_temp_Order_['cate'] == 101)]. \
            groupby(['user_id'])['day']. \
            mean(). \
            reset_index(). \
            rename(columns={'user_id': 'user_id', 'day': BetweenFlag + 'day_cate_30_101_meanday'})
        self.data_BuyOrNot_FirstTime = self.data_BuyOrNot_FirstTime.merge(features_temp_, on=['user_id'], how='left')

        # 购买月份数
        features_temp_ = \
        features_temp_Order_[(features_temp_Order_['cate'] == 30) | (features_temp_Order_['cate'] == 101)]. \
            groupby(['user_id'])['month']. \
            nunique(). \
            reset_index(). \
            rename(columns={'user_id': 'user_id', 'month': BetweenFlag + 'month_cate_30_101_monthnum'})
        self.data_BuyOrNot_FirstTime = self.data_BuyOrNot_FirstTime.merge(features_temp_, on=['user_id'], how='left')

        '''
        继续添加
        '''

    def MakeFeature_Action_(self,
                            FeatureMonthBegin,
                            FeatureMonthEnd,
                            BetweenFlag
                            ):
        # Action User Sku
        '''
        self.df_Action_User_Sku
        user_id sku_id a_date a_num a_type age sex user_lv_cd price cate para_1 para_2 para_3
        '''
        features_temp_Action_ = self.df_Action_User_Sku[(self.df_Action_User_Sku['a_date'] >= FeatureMonthBegin) & \
                                                        (self.df_Action_User_Sku['a_date'] <= FeatureMonthEnd)]
        # 用户浏览特征
        # sku_id cate 30 101 浏览数
        features_temp_ = \
        features_temp_Action_[(features_temp_Action_['cate'] == 30) | (features_temp_Action_['cate'] == 101)]. \
            groupby(['user_id'])['sku_id']. \
            nunique(). \
            reset_index(). \
            rename(columns={'user_id': 'user_id', 'sku_id': BetweenFlag + 'sku_id_cate_30_101_cnt'})
        self.data_BuyOrNot_FirstTime = self.data_BuyOrNot_FirstTime.merge(features_temp_, on=['user_id'], how='left')

        # a_date cate 30 101 天数
        features_temp_ = \
        features_temp_Action_[(features_temp_Action_['cate'] == 30) | (features_temp_Action_['cate'] == 101)]. \
            groupby(['user_id'])['a_date']. \
            nunique(). \
            reset_index(). \
            rename(columns={'user_id': 'user_id', 'a_date': BetweenFlag + 'a_date_cate_30_101_nuique'})
        self.data_BuyOrNot_FirstTime = self.data_BuyOrNot_FirstTime.merge(features_temp_, on=['user_id'], how='left')

        '''
        # 继续整加特征
        '''














