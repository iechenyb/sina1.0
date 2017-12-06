package com.cyb.reflect.bean;

import java.io.File;


public class BeanVo {
	public String id;
	public String name;
	public String url="";
	public int type;
	
	public File file;
	
    public BeanVo(String id,String name){
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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	
}
