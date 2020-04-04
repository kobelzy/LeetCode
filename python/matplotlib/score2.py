import matplotlib.pyplot as plt
import numpy as np
import csv

Semester1 = []
Semester2 = []

with open("score.csv", 'r') as csvfile:
    f_csv = csv.reader(csvfile)
    for row in f_csv:
        Semester1.append(int(row[0]))
        Semester2.append(int(row[1]))

len = len(Semester1)
x = np.arange(1, len + 1)

fig, ax = plt.subplots()
ax.set_xticks(x)
plt.bar(x, Semester1, 0.3, alpha=0.5, color='b')
plt.bar(x + 0.3, Semester2, 0.3, alpha=0.5, color='r')

plt.legend('current', 'last', loc='upper left')

for a, b in zip(x, Semester1):
    plt.text(a, b + 0.2, '%d' % b, ha='center', va='bottom', fontsize=10)
for a, b in zip(x, Semester2):
    plt.text(a + 0.3, b + 0.2, '%d' % b, ha='center', va='bottom', fontsize=10)
plt.show()
