import matplotlib.pyplot as plt
import pandas as pd
import numpy as np

# import numpy.random.randm as randn
#
# data = pd.read_csv('E:\交通数据集\卡口识别.csv')
# data.shape
fig = plt.figure()
ax1 = fig.add_subplot(2, 2, 1)
ax2 = fig.add_subplot(2, 2, 2)
# plt.subplots_adjust(0, 0)
# ax1.plot(randn(1000).cumsum(), 'k')
ax2.hist([1.5, 3.5, -2, 1.5],color='k', alpha=0.3)
x = np.linspace(-np.pi, np.pi, 100)
ax1.plot(x, np.sin(x),'--')

x2 = np.arange(1,10)
y = x2
ax3 = fig.add_subplot(223)
# ax3.scatter([x2], [y],  facecolon = 'o')
plt.scatter(x2, y, marker='o')
print('通过')
plt.show()
