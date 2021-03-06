package com.briup.util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import com.briup.util.impl.LogImpl;

public class ConnectionFactory {

	private static String driver;
	private static String url;
	private static String username;
	private static String password;
	private static Connection connection;
	private static Properties properties;
	private static Log log = new LogImpl();
	public static String path;
	/*
	 * 通过ConnectionFactory工厂类，可以直接获得Connection对象
	 * 通过jdbc.properties文件中的变量使用Properties对象
	 */

	static {
		properties = new Properties();
		Configuration.getInstanceObject("Connection");
		try {

			InputStream is = new FileInputStream(path);
			properties.load(is);
			driver = properties.getProperty("driver");
			url = properties.getProperty("url");
			username = properties.getProperty("user");
			password = properties.getProperty("password");
			log.debug(driver + "|" + url + "|" + username + "|" + password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return connection;
	}
}
