package com.briup.client.impl;

import java.io.BufferedOutputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Collection;

import com.briup.bean.Environment;
import com.briup.client.Client;
import com.briup.util.BackUp;
import com.briup.util.Configuration;
import com.briup.util.Log;
import com.briup.util.impl.BackUpImpl;
import com.briup.util.impl.LogImpl;

public class ClientImpl implements Client {
	public static String ip;
	public static String port;
	private Socket socket;
	Log log = new LogImpl();
	public String backup;
	BackUp backUp = new BackUpImpl();

	public void send(Collection<Environment> coll) throws Exception {
		try {
		Collection<Environment> c = (Collection<Environment>) backUp.load(backup, true);
		if(c!=null) {
			log.info("找到备份文件");
			coll.addAll(c);
			log.info("装载完成");
		}
		socket = new Socket(ip, Integer.parseInt(port));
		log.info("客户端正在和服务器建立连接...");
		OutputStream os = socket.getOutputStream();
		log.info("客户端和服务器连接成功准备发送数据...");
		BufferedOutputStream bos = new BufferedOutputStream(os);
		ObjectOutputStream oos = new ObjectOutputStream(bos);
		oos.writeObject(coll);
		oos.flush();
		log.info("客户端发送成功");
		socket.close();
		}catch (Exception e) {
			try {
				//发生异常，生成备份文件
				log.info("正在备份数据");
				backUp.backUps(backup, coll, true);
				log.info("备份完成");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
