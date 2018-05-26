# -*- coding: utf-8 -*-
import pandas as pd
import numpy as np
from sklearn.ensemble import RandomForestRegressor,GradientBoostingRegressor
from sklearn.metrics import f1_score
from sklearn.model_selection import train_test_split
from sklearn.model_selection import KFold
from sklearn.model_selection import StratifiedKFold
import lightgbm as lgb


class SBBTree():
    """Stacking,Bootstap,Bagging----SBBTree"""
    """ author：Cookly """

    def __init__(self, stacking_num, bagging_num, bagging_test_size, num_boost_round, early_stopping_rounds):
        """
        Initializes the SBBTree.
        Args:
          params : RandomForestRegressor params.
          stacking_num : k_flod stacking.
          bagging_num : bootstrap num.
          bagging_test_size : bootstrap sample rate.
          num_boost_round : boost num.
          early_stopping_rounds : early_stopping_rounds.
        """
        # self.params = params
        self.stacking_num = stacking_num
        self.bagging_num = bagging_num
        self.bagging_test_size = bagging_test_size
        self.num_boost_round = num_boost_round
        self.early_stopping_rounds = early_stopping_rounds

        self.model = RandomForestRegressor
        self.stacking_model = []
        self.bagging_model = []

    def fit(self, X, y):
        """ fit model. """
        if self.stacking_num > 1:
            layer_train = np.zeros((X.shape[0], 2))
            self.SK = StratifiedKFold(n_splits=self.stacking_num, shuffle=True, random_state=1)
            for k, (train_index, test_index) in enumerate(self.SK.split(X, y)):
                X_train = X[train_index]
                y_train = y[train_index]
                X_test = X[test_index]
                y_test = y[test_index]

                # lgb_train = lgb.Dataset(X_train, y_train)
                # lgb_eval = lgb.Dataset(X_test, y_test, reference=lgb_train)
                model = RandomForestRegressor(n_estimators=1000,
                              criterion='mse',
                              max_depth=6,
                              max_features=0.8,
                              min_samples_leaf=8,
                              n_jobs=12,
                              random_state=777)

                gbm = model.fit(X_train,y_train)

                self.stacking_model.append(gbm)

                pred_y = gbm.predict(X_test,)
                layer_train[test_index, 1] = pred_y

            X = np.hstack((X, layer_train[:, 1].reshape((-1, 1))))
        else:
            pass
        for bn in range(self.bagging_num):
            X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=self.bagging_test_size, random_state=bn)

            lgb_train = lgb.Dataset(X_train, y_train)
            lgb_eval = lgb.Dataset(X_test, y_test, reference=lgb_train)

            gbm = lgb.train(self.params,
                            lgb_train,
                            num_boost_round=10000,
                            valid_sets=lgb_eval,
                            early_stopping_rounds=200)

            self.bagging_model.append(gbm)

    def predict(self, X_pred):
        """ predict test data. """
        if self.stacking_num > 1:
            test_pred = np.zeros((X_pred.shape[0], self.stacking_num))
            for sn, gbm in enumerate(self.stacking_model):
                pred = gbm.predict(X_pred, num_iteration=gbm.best_iteration)
                test_pred[:, sn] = pred
            X_pred = np.hstack((X_pred, test_pred.mean(axis=1).reshape((-1, 1))))
        else:
            pass
        for bn, gbm in enumerate(self.bagging_model):
            pred = gbm.predict(X_pred, num_iteration=gbm.best_iteration)
            if bn == 0:
                pred_out = pred
            else:
                pred_out += pred
        return pred_out / self.bagging_num



