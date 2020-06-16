package com.birup.server.impl;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collection;

import com.birup.bean.Environment;
import com.birup.server.server;
import com.briup.util.Log;
import com.briup.util.XML;
import com.briup.util.impl.LogImpl;

public class serverImpl implements server {
	private int port;
	ServerSocket ss;
	Socket socket;
	InputStream is;
	BufferedInputStream bis;
	ObjectInputStream ois;
	Log log = new LogImpl();

	public void receiver() throws Exception {
		port = Integer.parseInt(XML.xmlpath("Server", "port"));
		log.info("等待客户端连接");
		ss = new ServerSocket(port);
		// 服务器一直处于开启状态，一旦遇到异常关闭
		while (true) {
			try {
				socket = ss.accept();
				new Thread() {
					private Collection<Environment> coll;

					@Override
					public void run() {
						String ipAddress = socket.getInetAddress().getHostName();
						log.info("客户端地址：" + ipAddress);

						try {

							ObjectInputStream ois = new ObjectInputStream(
									new BufferedInputStream(socket.getInputStream()));
							coll = (Collection<Environment>) ois.readObject();

							log.info(getName() + "接受数据：" + coll.size());
						} catch (Exception e) {
							e.printStackTrace();
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
				// TODO Auto-generated catch block
				e.printStackTrace();
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

	public static void main(String[] args) {
		try {
			new serverImpl().receiver();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
