import numpy as np
import matplotlib.pyplot as plt
import pandas as pd

fig,ax=plt.subplots()

iris=pd.read_csv("iris.csv")
colors=['r','y','b']
Species=iris.Species.unique() #['setosa' 'versicolor' 'virginica'] 花的种类
print(Species)

plt.subplot(1,2,1)
for i in range(len(Species)):
    plt.scatter(iris.loc[iris.Species==Species[i],'Petal.Length'],
    iris.loc[iris.Species==Species[i],'Petal.Width'],
            s=35,c=colors[i],label=Species[i]
    )

plt.title('Length vs Width')
plt.xlabel('Petal.Length')
plt.ylabel('Petal.Width')
plt.grid(True,linestyle='--',alpha=0.8)
plt.legend(loc='lower right')


plt.subplot(1,2,2)
for i in range(len(Species)):
    plt.scatter(iris.loc[iris.Species==Species[i],'Petal.Width'],
    iris.loc[iris.Species==Species[i],'Petal.Length'],
            s=35,c=colors[i],label=Species[i]
    )

plt.title(' Width vs Length')
plt.ylabel('Petal.Length')
plt.xlabel('Petal.Width')
plt.grid(True,linestyle='--',alpha=0.8)
plt.legend(loc='lower right')
plt.show()