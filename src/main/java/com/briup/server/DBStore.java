package com.briup.server;

import java.util.Collection;

import com.briup.bean.Environment;

public interface DBStore {
	public void save(Collection<Environment> coll) throws Exception;
}
