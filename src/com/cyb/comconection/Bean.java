package com.cyb.comconection;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "tb_bean")
public class Bean {
	@Id
	@Column
	public String id;
	@Column(name = "name")
	public String name;
	@Column(name = "url")
	public String url;
	@Column(name = "type")
	public String type;
    public Bean(String id,String name){
    	this.id=id;
    	this.name=name;
    }
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
