package com.briup.server;

import com.briup.util.WossModulelnit;

public interface Server extends WossModulelnit {
	public void receiver() throws Exception;

	public void shutdown() throws Exception;
}
