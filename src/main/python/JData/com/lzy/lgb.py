# -*- coding: utf-8 -*-
"""
Created on Tue May 01 15:16:59 2018

@author: Administrator
"""
import pandas as pd
import numpy as np
from model import fit_predict
from datetime import datetime, timedelta

weight = []
for i in range(1, 50001):
    weight.append(1 / (1 + np.log(i)))


def score(df):
    df = df.sort_values('o_num').reset_index(drop='index')
    df['label_1'] = [1 if x > 0 else 0 for x in df['label_1']]
    df = df.loc[:49999, :]
    df['weight'] = weight

    s1 = sum(df['label_1'] * df['weight']) / 4674.239

    s2 = 0.0
    df = df[df['label_1'] == 1].reset_index(drop='index')
    for i in range(df.shape[0]):
        s2 += 10.0 / ((np.round(df.loc[i, 'label_2']) - df.loc[i, 'pred_date']) ** 2 + 10)

    s2 = s2 / df.shape[0]

    print
    's1 score is %s,s2 score is %s,S is %s' % (s1, s2, 0.4 * s1 + 0.6 * s2)


def get_train():
    train = pd.read_csv(r'../input/vali_train.csv')
    test = pd.read_csv(r'../input/vali_test.csv')

    drop_column = ['user_id', 'label_1', 'label_2']

    result = test[['user_id']]  # 要提交的结果

    # 训练S1
    X = train.drop(drop_column, axis=1)
    X_pred = test.drop(drop_column, axis=1)
    y = train['label_1']

    result['o_num'], feat_imp_s1 = fit_predict(X, y, X_pred)

    # 训练S2
    y = train['label_2']

    result['pred_date'], feat_imp_s2 = fit_predict(X, y, X_pred)

    # 输出结果
    result[['label_1', 'label_2']] = test[['label_1', 'label_2']]

    score(result)
    return feat_imp_s1, feat_imp_s2


def get_result():
    train = pd.read_csv(r'../input/test_train.csv')
    test = pd.read_csv(r'../input/test_test.csv')

    drop_column = ['user_id', 'label_1', 'label_2']

    sub = test[['user_id']]  # 要提交的结果

    # 训练S1
    X = train.drop(drop_column, axis=1)
    X_pred = test.drop(drop_column, axis=1)
    y = train['label_1']

    sub['o_num'], s1 = fit_predict(X, y, X_pred)

    # 训练S2
    X = train.drop(drop_column, axis=1)
    X_pred = test.drop(drop_column, axis=1)
    y = train['label_2']

    sub['pred_date'], s2 = fit_predict(X, y, X_pred)

    # 输出结果
    sub = sub.sort_values(by='o_num', ascending=False)[['user_id', 'pred_date']].reset_index(drop='index')
    sub['pred_date'] = sub['pred_date'].map(lambda day: datetime(2017, 5, 1) + timedelta(days=int(day + 0.49 - 1)))
    sub.loc[:49999, :].to_csv(r'../result/submission.csv', index=None, encoding='utf-8')

    return s1, s2


if __name__ == '__main__':
    # 加载训练集和测试集
    feat_imp_s1, feat_imp_s2 = get_train()
    s1,s2 = get_result()