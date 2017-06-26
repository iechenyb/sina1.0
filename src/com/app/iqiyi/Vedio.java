package com.app.iqiyi;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "vedio")
public class Vedio {
	@Column(name = "name")
	private String name;
	@Column(name = "url")
	private String url;
	@Column(name = "type")
	private String type;

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

}
