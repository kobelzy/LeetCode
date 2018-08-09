#encoding=utf8
import pandas as pd
import numpy as np
from datetime import date, timedelta
from sklearn.metrics import roc_auc_score,mean_squared_error
from com_util import *

#选取一个月内有交互的user_sku记录做特征
#后一个月的成交信息作为打标签的依据
#"""
path='E:/dataset/JData_UserShop/'
base_path = 'E:/dataset/JData_UserShop'


sku_info=pd.read_csv(path+"jdata_sku_basic_info.csv")
user_info=pd.read_csv(path+"jdata_user_basic_info.csv")
user_action=pd.read_csv(path+"jdata_user_action.csv")
user_order=pd.read_csv(path+"jdata_user_order.csv")
user_comment=pd.read_csv(path+"jdata_user_comment_score.csv")
#"""
sample_windows=30  #选取30天的时间长度做特征
label_windows=30  #选取30天的时间长度做标签
step=31           #滑窗步长
slip_times=1      #滑窗次数
train_target_day=date(2017, 3, 1)    #训练集起始日期
valid_target_day=date(2017, 4, 1)    #验证集起始日期
test_target_day=date(2017, 5, 1)    #测试机起始日期

def create_sample(target_day,slip_times,sample_type="train"):
    slip_day=timedelta(days=step*slip_times)
    #特征集开始日期
    sample_start_day=target_day-timedelta(days=sample_windows)-slip_day
    #特征集结束日期
    sample_end_day=target_day-timedelta(days=1)-slip_day
    #标签集开始日期
    label_start_day=target_day-slip_day
    #标签集结束日期
    label_end_day=target_day+timedelta(days=label_windows)-slip_day
    #标记特征每天的日期
    sample_date=[str(d)[:10] for d in pd.date_range(sample_start_day,sample_end_day)]
    #标记标签每天的日期
    label_date=[str(d)[:10] for d in pd.date_range(label_start_day,label_end_day)]

    sample_date=pd.DataFrame({"a_date":sample_date})
    label_date=pd.DataFrame({"o_date":label_date})

    sample=user_action.merge(sample_date,on="a_date",how="inner")
    sample=sample.merge(user_info,on="user_id",how="inner")
    sample=sample.merge(sku_info,on="sku_id",how="inner")

    #构造特征
    #每天有action的用户数量
    sample=feat_count(sample,sample,["user_id"],"a_date")
    #每个用户action的总数
    sample=feat_sum(sample,sample,["user_id"],"a_num")
    #每个用户做的最多的action
    sample=feat_max(sample,sample,["user_id"],"a_num")
    #每个用户浏览或者关注的不同物品的个数
    sample=feat_nunique(sample,sample,["user_id"],"sku_id")
    #每个用户在计算周期内有action的天数
    sample=feat_nunique(sample,sample,["user_id"],"a_date")
    #每个用户浏览或者关注的最大数
    sample=feat_max(sample,sample,["user_id"],"a_type")
    #每个用户浏览或关注的物品的种类数
    sample=feat_nunique(sample,sample,["user_id"],"cate")
    #每个用户基于每个种类浏览或者关注的物品数
    sample=feat_nunique(sample,sample,["user_id","cate"],"sku_id")
    #每个用户基于每个种类浏览或者关注的平均价格
    sample=feat_mean(sample,sample,["user_id","cate"],"price")
    #行为日期至训练或验证等起始日期的间隔天数
    sample["day_gap"] = (pd.to_datetime(target_day) - pd.to_datetime(sample["a_date"])).dt.days
    #每个用户action至起始日期的最小时间间隔
    sample = feat_min(sample, sample, ["user_id"], "day_gap")
    #每个用户action至起始日期的平均时间间隔
    sample = feat_mean(sample, sample, ["user_id"], "day_gap")

    #去重，取最后一次浏览
    sample=sample.sort_values("a_date").drop_duplicates(["user_id"],keep="last")

    #去重，取第一次购买
    label=user_order.merge(label_date,on="o_date",how="inner")[["user_id","o_date"]].copy()
    label = label.sort_values("o_date").drop_duplicates(["user_id"], keep="first")

    if sample_type=="train":
        sample=sample.merge(label,on=["user_id"],how="left").fillna("")
        #当天有下单则为1，否则为0
        sample["label_1"]=sample["o_date"].apply(lambda x:1 if x else 0)
        #下单日与最后一次浏览或者关注相隔的天数
        sample["label_2"]=(pd.to_datetime(sample["o_date"])-pd.to_datetime(sample["a_date"])).dt.days
        sample["label_2"]=sample["label_2"].fillna(100)
        del sample["o_date"]
    else:
        sample["label_1"]=-1
        sample["label_2"]=-1
    return sample


train_sample_list=[]
for i in range(slip_times):
    sample=create_sample(train_target_day,i,"train")
    train_sample_list.append(sample)
train=pd.concat(train_sample_list).reset_index(drop=True)
print(train.shape)
valid = create_sample(valid_target_day, 0, "train").reset_index(drop=True)
test = create_sample(test_target_day, 0, "test").reset_index(drop=True)
print(test.shape)

drop_features=["a_date","label_1","label_2"]

#####################################################预测提交部分###################################################
import lightgbm as lgb
num_round=2000
early_stopping_rounds=100

params_1 = {
          'boosting_type': 'gbdt',
          'objective': 'binary',
          'metric': 'auc',
          'min_child_weight': 1.5,
          'num_leaves': 2**5,
          'lambda_l2': 10,
          'subsample': 0.7,
          'colsample_bytree': 0.5,
          'colsample_bylevel': 0.5,
          'learning_rate': 0.1,
          'scale_pos_weight': 20,
          'seed': 2018,
          'nthread': 4,
          'silent': True,
          }

params_2 = {
          'boosting_type': 'gbdt',
          'objective': 'regression_l2',
          'metric': 'mse',
          'min_child_weight': 1.5,
          'num_leaves': 2**5,
          'lambda_l2': 10,
          'subsample': 0.7,
          'colsample_bytree': 0.5,
          'colsample_bylevel': 0.5,
          'learning_rate': 0.1,
          'scale_pos_weight': 20,
          'seed': 2018,
          'nthread': 4,
          'silent': True,
          }


train_x=train.drop(drop_features,axis=1).values
valid_x=valid.drop(drop_features,axis=1).values
test_x=test.drop(drop_features,axis=1).values

train_y_1=train["label_1"]
valid_y_1=valid["label_1"]

#lgb


#S1
train_matrix=lgb.Dataset(train_x,label=train_y_1)
valid_matrix=lgb.Dataset(valid_x,label=valid_y_1)
model = lgb.train(params_1, train_matrix, num_round, valid_sets=valid_matrix,
                  early_stopping_rounds=early_stopping_rounds
                  )
test["prob"] = model.predict(test_x, num_iteration=model.best_iteration).reshape((test_x.shape[0], 1))


#由于S2是关于下单日期预测，因此负样品无意义，只选取购买的训练
train=train[train.label_1==1]
valid=valid[valid.label_1==1]
train_x=train.drop(drop_features,axis=1).values
valid_x=valid.drop(drop_features,axis=1).values
test_x=test.drop(drop_features,axis=1).values

train_y_2=train["label_2"]
valid_y_2=valid["label_2"]

#S2
train_matrix=lgb.Dataset(train_x,label=train_y_2)
valid_matrix=lgb.Dataset(valid_x,label=valid_y_2)
model = lgb.train(params_2, train_matrix, num_round, valid_sets=valid_matrix,
                  early_stopping_rounds=early_stopping_rounds
                  )
test["day_gap"]=model.predict(test_x, num_iteration=model.best_iteration).reshape((test_x.shape[0], 1))

#处理提交
def pred_date(x,y):
    a=int(x[:4])
    b=int(x[5:7])
    c=int(x[8:10])
    return str(date(a,b,c)+timedelta(round(y,0)))

test["pred_date"]=list(map(lambda x,y:pred_date(x,y),test["a_date"],test["day_gap"]))
test=test[["user_id","prob","pred_date"]].copy()
test=user_info[["user_id"]].merge(test,on="user_id",how="left").fillna(0)
test=test.sort_values("prob",ascending=False).drop_duplicates("user_id",keep="first")
test[:50000][["user_id","pred_date"]].to_csv("sub.csv",index=None)
