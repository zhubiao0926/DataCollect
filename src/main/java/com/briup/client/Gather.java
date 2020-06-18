package com.briup.client;

import java.util.List;

import com.briup.bean.Environment;
import com.briup.util.WossModulelnit;

/*数据采集接口，采集数据
 * 封装成Environment对象
 * 放进list集合中
 * */

public interface Gather extends WossModulelnit {
	/* gather()方法用来采集数据，返回一个list集合泛型为Environment。 */
	public abstract List<Environment> gather() throws Exception;

}
