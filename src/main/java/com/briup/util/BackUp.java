package com.briup.util;

public interface BackUp {
	public void backUps(String filePath,Object object, boolean append) throws Exception;

	public Object load(String filePath,boolean del) throws Exception;
}
