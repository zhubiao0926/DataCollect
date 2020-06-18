package com.briup.server;

import java.util.Collection;

import com.briup.bean.Environment;
import com.briup.util.WossModulelnit;

public interface DBStore extends WossModulelnit {
	public void save(Collection<Environment> coll) throws Exception;
}
