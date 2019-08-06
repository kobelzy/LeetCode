#Matplotlib的基本矿机

## 1 辅助性函数
### 1.1

    plt.style.use('classic') #设置图像的风格为经典风格
    
### 1.2 创建图像和坐标轴

    fig=plt.figure() # 创建图像
    ax=plt.axes() #创建坐标轴图像
    
    fig,ax=plt.subplots #同时创建图像和坐标轴    

### 1.3 坐标轴上下限

    ax.set_xlim(min,max)
    ax.set_ylim(min,max)
    
### 1.4 设置标题

    plt.title("title")
    #或者
    ax.set_title("title") 
    
### 1.5 设置坐标轴标签

    ax.set_xlable("label_name")
    ax.set_ylabel("label_name")

### 1.6 设置图例

    plt.legend(["y=100x+10","y=2^x"],loc="upper left") #左上角显示图例

### 1.7 添加文字

### 1.8 添加注释

    ax.annotation
    
### 1.9 隐藏边框

    ax.spines["top"].set_visible(False)# 隐藏上边框

###1.10 隐藏坐标轴

    ax.set_xticks([])

### 1.11 设置坐标轴刻度和刻度标签

    ax.set_xticks([1,2,3])
    ax.set_xticklabels("1","2","3")
    
### 1.12 创建多个图像

    ax1=fig.add_axes([left,bottom,width,height]) #四个参数用于设置坐标轴的位置和大小
    
    fig.ax=plt.subplots(num_of_rows,num_of_columns,sharx=True,sharey=True) #可用ax[0].plt来获取对应图像
### 1.13 支持中文
    
    #支持中文,使用细黑体的中文字符
    matplotlib.rcParams['font.sans-serif']=['SimHei']
    
### 1.14 保存图像
    
    fig.savefig("file_name.png") #必须放在plt.show()之前，否则保存的图像是空的
    #可以通过如下命令查看系统支持的文件格式
    fig.canvas.get_supported_filetypes()    