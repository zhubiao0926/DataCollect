package com.briup.server.impl;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collection;

import com.briup.bean.Environment;
import com.briup.server.Server;

import com.briup.util.Log;
import com.briup.util.BackUp;
import com.briup.util.Configuration;
import com.briup.util.impl.BackUpImpl;
import com.briup.util.impl.LogImpl;

public class ServerImpl implements Server {
	public static String port;
	public static String backup;
	private String backupFile;
	private Collection<Environment> coll;
	private ServerSocket ss;
	private Socket socket;
	private InputStream is;
	private BufferedInputStream bis;
	private ObjectInputStream ois;
	public static Log log = new LogImpl();
	private boolean flag = true;
	DBStoreImpl instanceObject = (DBStoreImpl) Configuration.getInstanceObject("DBStore");
	BackUp backUp = new BackUpImpl();

	public void receiver() throws Exception {
		log.info("等待客户端连接");
		ss = new ServerSocket(Integer.parseInt(port));
		// 服务器一直处于开启状态，一旦遇到异常关闭

		while (flag) {
			try {
				socket = ss.accept();
				new Thread() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						String ipAddress = socket.getInetAddress().getHostName();
						log.info("客户端地址：" + ipAddress);
						backupFile = backup + ipAddress;
						try {
							ObjectInputStream ois = new ObjectInputStream(
									new BufferedInputStream(socket.getInputStream()));
							coll = (Collection<Environment>) ois.readObject();
							Collection<Environment> c = (Collection<Environment>) backUp.load(backupFile, true);
							if (c != null) {
								log.info("找到备份文件");
								coll.addAll(c);
								log.info("装载完成");
							}
							log.info(getName() + "接受数据：" + coll.size());
							instanceObject.save(coll);
							log.info(getName() + "入库数据：" + coll.size());
						} catch (Exception e) {
							try {
								//发生异常，生成备份文件
								log.info("正在备份数据");
								backUp.backUps(backupFile,coll, true);
								log.info("备份完成");
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						} finally {
							if (socket != null)
								try {
									socket.close();
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
						}
					}
				}.start();

				// ServerThread thread = new ServerThread();
				// thread.setSocket(socket);
				// thread.setLog(log);
				// thread.start();
			} catch (Exception e) {
				
				log.error("服务器异常");
				shutdown();
			}
		}
	}

	public void shutdown() throws Exception {

		if (ois != null) {
			ois.close();
		}
		if (bis != null) {
			bis.close();
		}
		if (is != null) {
			is.close();
		}
		if (socket != null) {
			socket.close();
		}
		if (ss != null) {
			ss.close();
		}

	}

}
