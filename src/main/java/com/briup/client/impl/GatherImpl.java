package com.briup.client.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.briup.bean.Environment;
import com.briup.client.Gather;
import com.briup.util.Log;
import com.briup.util.Xml;
import com.briup.util.impl.LogImpl;

public class GatherImpl implements Gather {
	String path = null;
	// 存放Environment对象的集合对象
	List<Environment> list = new ArrayList<Environment>();
	Log log = new LogImpl();

	// 消除重复步骤，封装写入Environment的属性的步骤
	public Environment loadEnvironment(String[] str) {
		Environment environment = new Environment();
		environment.setSrcid(str[0]);
		environment.setDesid(str[1]);
		environment.setDevid(str[2]);
		environment.setSersorAddress(str[3]);
		environment.setCount(Integer.parseInt(str[4]));
		environment.setCmd(str[5]);
		environment.setStatus(Integer.parseInt(str[7]));
		Long time = new Long(str[8]);
		Timestamp timestamp = new Timestamp(time);
		environment.setGather_date(timestamp);
		return environment;

	}

	public List<Environment> gather() {
		/*
		 * 创建bufferReader逐行读取文件 分割后写入Environment对象 返回对象
		 */
		try {
			String path = Xml.xmlpath("Gather","path");
			BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(path)));
			String data = null;
			int count1 = 0;
			int count2 = 0;
			int count3 = 0;

			while ((data = bufferedReader.readLine()) != null) {
				String[] str = data.split("[|]");
				if ("16".equals(str[3])) {
					Environment environment = loadEnvironment(str);
					environment.setName("温度");
					float value = Integer.valueOf(str[6].substring(0, 4), 16);
					float Temperature = (float) (value * 0.00268127 - 46.85);
					environment.setData(Temperature);
					list.add(environment);
					environment.setName("湿度");
					float value2 = Integer.valueOf(str[6].substring(4, 8), 16);
					float Humidity = (float) (value * 0.00190735 - 6);
					environment.setData(Humidity);
					list.add(environment);
					count1++;
				} else {
					float value = Integer.parseInt(str[6].substring(0, 4), 16);
					if ("256".equals(str[3])) {
						Environment environment = loadEnvironment(str);
						environment.setName("光照强度");
						environment.setData(value);
						list.add(environment);
						count2++;
					}
					if ("1280".equals(str[3])) {
						Environment environment = loadEnvironment(str);
						environment.setName("二氧化碳");
						environment.setData(value);
						list.add(environment);
						count3++;
					}
				}

			}
			log.info("数据收集完成");
			log.info("温度-湿度" + count1);
			log.info("光照强度" + count2);
			log.info("二氧化碳" + count3);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public static void main(String[] args) {
		List<Environment> gather = new GatherImpl().gather();
		
	}

}
