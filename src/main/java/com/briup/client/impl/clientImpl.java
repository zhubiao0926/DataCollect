package com.briup.client.impl;

import java.io.BufferedOutputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Collection;

import com.birup.bean.Environment;
import com.briup.client.client;
import com.briup.util.Log;
import com.briup.util.XML;
import com.briup.util.impl.LogImpl;

public class clientImpl implements client {
	private String ip;
	private int port;
	private Socket socket;
	Log log = new LogImpl();

	public void send(Collection<Environment> coll) throws Exception {
		ip = XML.xmlpath("Client", "ip");
		port = Integer.parseInt(XML.xmlpath("Client", "port"));
		socket = new Socket(ip, port);
		log.info("客户端正在和服务器建立连接...");
		OutputStream os = socket.getOutputStream();
		log.info("客户端和服务器连接成功准备发送数据...");
		BufferedOutputStream bos = new BufferedOutputStream(os);
		ObjectOutputStream oos = new ObjectOutputStream(bos);
		oos.writeObject(coll);
		oos.flush();
		log.info("客户端发送成功");
		socket.close();
	}

	public static void main(String[] args) {
		Collection<Environment> coll = new GatherImpl().gather();
		try {
			new clientImpl().send(coll);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
