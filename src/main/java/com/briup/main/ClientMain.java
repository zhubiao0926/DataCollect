package com.briup.main;

import java.util.Collection;

import com.briup.bean.Environment;
import com.briup.client.impl.ClientImpl;
import com.briup.client.impl.GatherImpl;
import com.briup.util.Configuration;

/*
 * 客户端入口
 * */

public class ClientMain {
	public static void main(String[] args) {
		GatherImpl instanceObject = (GatherImpl) Configuration.getInstanceObject("Gather");
		Collection<Environment> coll = instanceObject.gather();
		ClientImpl instanceObject2 = (ClientImpl) Configuration.getInstanceObject("Client");
		try {
			instanceObject2.send(coll);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
