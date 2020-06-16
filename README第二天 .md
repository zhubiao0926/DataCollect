## 日志模块
log4j的应用和学习。匿名代码块实现（父类静态代码块-->>子类静态代码块-->>父类匿名代码块-->>父类构造器-->>子类匿名代码块-->>子类构造器
其中静态代码块在初始化时会被调用，但是匿名代码块与构造器只有在创建对象时才会调用。）
## client端
使用socket通信，ObjectOutputOutStream发送coll对象
## server端
使用socket通信，ObjectInputStream接受，多线程的两种实现方法。继承思想和内部类思想。

## 测试结果
![客户端](https://img-blog.csdnimg.cn/20200616212215951.png)![日志文件](https://img-blog.csdnimg.cn/20200616212238471.png)![服务器端](https://img-blog.csdnimg.cn/20200616212150217.png)
CSDN社区博客：https://blog.csdn.net/weixin_44468294/article/details/106795647
