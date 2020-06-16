package com.birup.server.impl;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Collection;

import com.birup.bean.Environment;
import com.briup.util.Log;

public class ServerThread extends Thread {
	// 让当前多线程类知道到底是哪个客户端进行的连接
	private Socket socket;
	private Log log;
	private Collection<Environment> coll;
	public Log getLog() {
		return log;
	}

	public void setLog(Log log) {
		this.log = log;
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		String ipAddress = socket.getInetAddress().getHostName();
		log.info("客户端地址：" + ipAddress);

		try {

			ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
			coll = (Collection<Environment>) ois.readObject();

			log.info(getName() + "接受数据：" + coll.size() );
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
}
