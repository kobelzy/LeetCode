import numpy as np
import matplotlib.pyplot as plt

import csv


def count_elements(scores):
    scorescount = {}
    for i in scores:
        scorescount[int(i)] = scorescount.get(int(i), 0) + 1
    return scorescount


def shows(counted):
    fig, ax = plt.subplots()
    plt.axis([50, 100, 0, 30])  # x,y 最大最小
    plt.bar(list(counted.keys()), counted.values())
    plt.show()


if __name__ == '__main__':

    scores = []
    with open("score.csv", 'r') as csvfile:
        f_csv = csv.reader(csvfile)
        for row in f_csv:
            scores.append(float(row[0]))

    print(scores)
    scorescount = count_elements(scores)
    print(scorescount)
    print(scorescount.keys())
    print(scorescount.values)
    shows(scorescount)
