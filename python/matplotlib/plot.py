import numpy as np
import matplotlib.pyplot as plt

#线性图
fig,zx=plt.subplots()
x=np.linspace(-1,10,20)
y1=100*x +10
y2=2**x

plt.plot(x,y1,'r+',color='r',linewidth=1.0,linestyle='-',label='line1')
plt.plot(x,y2,'bo',color="b",linewidth=1.0,linestyle='--',label="line2")

plt.xlim(-2,11)

plt.show()