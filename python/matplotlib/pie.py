import numpy as np
import matplotlib.pyplot as plt
import pandas as pd
import matplotlib

matplotlib.rcParams['font.sans-serif'] = ['SimHei']

label_list = ["出过", "保送", "本校", "保送外", "考研"]
size = [12, 7, 5, 7, 2]
explode = [0.2, 0, 0, 0, 0]

patches, texts,mautotexts = plt.pie(size, explode=explode, labels=label_list,
                                   labeldistance=1.1, autopct="%1.1f%%", shadow=True,
                                   startangle=0, pctdistance=0.6)
plt.show()
