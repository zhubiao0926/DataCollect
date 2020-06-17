package com.briup.server.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import com.briup.bean.Environment;
import com.briup.server.DBStore;
import com.briup.util.ConnectionFactory;
import com.briup.util.Log;
import com.briup.util.Xml;
import com.briup.util.impl.LogImpl;

public class DBStoreImpl implements DBStore {
	private Connection connection;
	private PreparedStatement ps;
	private int batchSize;
	Log log = new LogImpl();

	public void save(Collection<Environment> coll) throws Exception {

		connection = ConnectionFactory.getConnection();
		// 表示当前sql语句数
		int count = 0;
		/*
		 * 记录当前天数，默认为0
		 */
		int day = 0;
		batchSize = Integer.parseInt(Xml.xmlpath("DBStore", "batchSize"));
		for (Environment environment : coll) {
			/*
			 * 先判断要不要创建新的ps对象。 如果为null或者天数改变，则创建新的对象
			 */
			/*
			 * 注意：Timestamp对象的getDate()方法返回day of month
			 * Timestamp对象的getDay()返回0-6（对应的星期天到星期六）
			 */
			if (day != environment.getGather_date().getDate() && day != 0) {
				// 手动提交前一天的数据
				dealBatch();
				log.info("数据库入库的数据" + count);
				day = environment.getGather_date().getDate();
				log.info("数据库入库的天数:" + day);
				// 根据日期不同，产生不同的sql语句
				String sql = "insert into e_detail_" + day + " values(?,?,?,?,?,?,?,?,?)";
				ps = connection.prepareStatement(sql);
			} else if (ps == null) {
				day = environment.getGather_date().getDate();
				log.info("数据库入库的天数:" + day);
				String sql = "insert into e_detail_" + day + " values(?,?,?,?,?,?,?,?,?)";
				ps = connection.prepareStatement(sql);
			}
			ps.setString(1, environment.getName());
			ps.setString(2, environment.getSrcid());
			ps.setString(3, environment.getDesid());
			ps.setString(4, environment.getSersorAddress());
			ps.setInt(5, environment.getCount());
			ps.setString(6, environment.getCmd());
			ps.setInt(7, environment.getStatus());
			ps.setFloat(8, environment.getData());
			ps.setTimestamp(9, environment.getGather_date());
			// 将sql语句放入到批处理中
			ps.addBatch();
			count++;

			if (count % batchSize == 0) {
				ps.executeBatch();
			}
		}
		dealBatch();
		//循环结束提交数据
		log.info("数据库入库的数据" + count);
	}

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
}
