package com.cyb.collection.po;

public class Bean {
	private Integer id;
	private String name;
	private String pwd;
	private int age=0;
    public Bean(Integer id,String name,String pwd){
    	this.id = id;
    	this.name = name;
    	this.pwd = pwd;
    }
    public Bean(String name,String pwd){
    	this.name = name;
    	this.pwd = pwd;
    }
    public Bean(){
    	
    }
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	

}
