package com.briup.bean;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class Environment implements Serializable {
	// 环境种类名称
	private String name;
	// 发送端ID
	private String srcid;
	// 树莓派系统ID
	private String desid;
	// 试验箱模块ID（1-8）
	private String devid;
	// 模块上传传感器地址
	private String sersorAddress;
	// 传感器个数
	private int count;
	// 发送指令标号3表示接受数据16表示发送数据
	private String cmd;
	// 状态默认1表示成功
	private int status;
	// 环境值
	private float data;
	// 采集时间
	private Timestamp gather_date;
	public Environment(String name, String srcid, String desid, String devid, String sersorAddress, int count,
			String cmd, int status, float data, Timestamp gather_date) {
		super();
		this.name = name;
		this.srcid = srcid;
		this.desid = desid;
		this.devid = devid;
		this.sersorAddress = sersorAddress;
		this.count = count;
		this.cmd = cmd;
		this.status = status;
		this.data = data;
		this.gather_date = gather_date;
	}
	public Environment() {
		super();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSrcid() {
		return srcid;
	}
	public void setSrcid(String srcid) {
		this.srcid = srcid;
	}
	public String getDesid() {
		return desid;
	}
	public void setDesid(String desid) {
		this.desid = desid;
	}
	public String getDevid() {
		return devid;
	}
	public void setDevid(String devid) {
		this.devid = devid;
	}
	public String getSersorAddress() {
		return sersorAddress;
	}
	public void setSersorAddress(String sersorAddress) {
		this.sersorAddress = sersorAddress;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getCmd() {
		return cmd;
	}
	public void setCmd(String cmd) {
		this.cmd = cmd;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public float getData() {
		return data;
	}
	public void setData(float data) {
		this.data = data;
	}
	public Timestamp getGather_date() {
		return gather_date;
	}
	public void setGather_date(Timestamp gather_date) {
		this.gather_date = gather_date;
	}
	@Override
	public String toString() {
		return "Environment [name=" + name + ", srcid=" + srcid + ", desid=" + desid + ", devid=" + devid
				+ ", sersorAddress=" + sersorAddress + ", count=" + count + ", cmd=" + cmd + ", status=" + status
				+ ", data=" + data + ", gather_date=" + gather_date + "]";
	}
	

}

	