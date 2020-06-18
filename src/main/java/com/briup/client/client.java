package com.briup.client;

import java.util.Collection;

import com.briup.bean.Environment;
import com.briup.util.WossModulelnit;

public interface Client extends WossModulelnit {
	public void send(Collection<Environment> coll) throws Exception;
}
