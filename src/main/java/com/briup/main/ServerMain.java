package com.briup.main;

import com.briup.server.impl.ServerImpl;
import com.briup.util.Configuration;

/*
 * 服务器端入口
 * 
 * */
public class ServerMain {
	public static void main(String[] args) {
		ServerImpl instanceObject = (ServerImpl) Configuration.getInstanceObject("Server");
		try {
			instanceObject.receiver();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
