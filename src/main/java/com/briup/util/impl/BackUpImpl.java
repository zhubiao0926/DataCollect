package com.briup.util.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.briup.util.BackUp;
import com.briup.util.Log;

public class BackUpImpl implements BackUp {
	Log log = new LogImpl();
	
	public void backUps(String filepath,Object object, boolean append) throws Exception {
		File file = new File(filepath);
		if (!file.exists()) {
			file.createNewFile();
		}

		ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file, append));
		objectOutputStream.writeObject(object);
		objectOutputStream.flush();
		objectOutputStream.close();
	}

	public Object load(String filepath,boolean del) throws Exception {
		File file = new File(filepath);
		if (!file.exists())
			return null;
		ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
		Object object = objectInputStream.readObject();
		objectInputStream.close();
		if (del) {
			file.delete();
			log.info("删除成功");
		}
		return object;

	}

}
