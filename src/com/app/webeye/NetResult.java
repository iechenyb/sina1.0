package com.app.webeye;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "netresult")
public class NetResult {
	
	//服务名称  比如某某管理系统
	@Id
	@Column(name = "id",columnDefinition="varchar(32)")
	public String id;
		
	//服务名称  比如某某管理系统
	@Column(name = "name",columnDefinition="varchar(50)")
	private String name;
	
	@Column(name = "ip",columnDefinition="varchar(20)")
	private String ip;
	
	@Column(name = "domain",columnDefinition="varchar(50)")
	private String domain;
	
	// 1 port 2url 3ip  
	@Column(name = "type",columnDefinition="varchar(50)")
	private String type;
	
	//状态  0 成功  1 失败
	@Column(name = "zt" ,columnDefinition="varchar(1)")
	private String zt;
	
	@Column(name = "desc",columnDefinition="varchar(100)")
	private String desc;
	
	@Column(name = "time",columnDefinition="varchar(25)")
	private String time;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getZt() {
		return zt;
	}

	public void setZt(String zt) {
		this.zt = zt;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	
	

}
