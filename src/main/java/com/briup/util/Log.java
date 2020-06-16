package com.briup.util;

public interface Log extends WossModulelnit{
	//记录日志
	public void debug(String message);
	
	public void info(String message);
	
	public void warn(String message);
	
	public void error(String message);
	
	public void fatal(String message);
}
