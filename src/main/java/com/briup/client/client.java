package com.briup.client;

import java.util.Collection;

import com.birup.bean.Environment;

public interface client {
	public void send(Collection<Environment>coll)throws Exception;
}
