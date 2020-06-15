# 项目第一天

#### 1.完成了数据采集模块。

###### 1.1 xml读取配置文件

```java
		String path = null;
		SAXReader sax = new SAXReader();
		Document document = sax.read(new File("src/main/java/config.xml"));
		 //获取根元素
        Element root = document.getRootElement();
        System.out.println("获取到的根元素" + root);
        //获取所有的子元素Love
        List<Element> elements = root.elements("Gather");
        //输入name元素的内容
        for (Element element : elements) {
           String elementTextTrim = element.elementTextTrim("path");
           path =elementTextTrim;
        }
       return path;
```

###### 1.2 创建包含如下属性的environment的bean对象

```java

//环境种类名称
	private String name;
	//发送端ID
	private String srcid;
	//树莓派系统ID
	private String desid;
	//试验箱模块ID（1-8）
	private String devid;
	//模块上传传感器地址
	private String sersorAddress;
	//传感器个数
	private int count;
	//发送指令标号3表示接受数据16表示发送数据
	private String cmd;
	//状态默认1表示成功
	private int status;
	//环境值
	private float data;
	//采集时间
	private Date gather_date;
```

###### 1.3 数据采集接口Gather数据采集接口，采集数据

 * 封装成Environment对象
 * 放进list集合中
 * 使用bufferReader逐行读取数据，分裂封装成Environment对象返回。



#### 测试结果

![A[Z`N9H)LLHBA[BTZ}$XDJ4](E:\github\DataCollect\A[Z`N9H)LLHBA[BTZ}$XDJ4.png)
CSDN博客：https://blog.csdn.net/weixin_44468294/article/details/106768700


