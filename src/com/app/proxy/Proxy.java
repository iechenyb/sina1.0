package com.app.proxy;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name="proxy")
public class Proxy {
	@Column(name="ip")
	private String ip;
	@Column(name="port")
	private int port;

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
   public String toString(){
	   return this.ip+","+this.port;
   }
}
