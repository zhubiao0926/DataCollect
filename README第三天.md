## DBStore入库模块
**1.新建Oracle数据库**
循环建表

```java
--使用PL/SQL删除表
BEGIN
  FOR i IN 1..31 LOOP
    EXECUTE IMMEDIATE
      'DROP TABLE e_detail_'||TO_CHAR(i);
  END LOOP;
END;
/

--使用PL/SQL建表
BEGIN
  FOR i IN 1..31 LOOP
    EXECUTE IMMEDIATE
      'CREATE TABLE e_detail_'||TO_CHAR(i)||
      '(
       name varchar2(20),
		srcId varchar2(5),
		dstId varchar2(5),
		sersorAddress varchar2(7),
		count number(2),
		cmd  varchar2(5),
		status number(2),
		data number(9,4),
		gather_date date
      )';
  END LOOP;
  
END;
/
```
**2.新建ConnectionFactory工厂类**
使用JDBC连接Oracle数据库。

**3.创建DBStore接口和实现类**
先获取连接，遍历传过来的集合得到Environment对象，判断日期，PreparedStatement执行SQL语句，使用批处理技术，插入数据库。
==注意：Timestamp对象的getDate()方法返回day of month
		Timestamp对象的getDay()返回0-6（对应的星期天到星期六）==
```java
ps.addBatch();
public void dealBatch() {
		if (ps != null) {
			try {
				ps.executeBatch();
				ps.clearBatch();
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
```

	测试结果
![测试结果](https://img-blog.csdnimg.cn/20200617144837875.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NDQ2ODI5NA==,size_16,color_FFFFFF,t_70)
