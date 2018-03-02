package com.cyb.collection.po;

public class User_ implements Comparable<User_>{
	private String name;
	private String pwd;
    public User_(String name,String pwd){
    	this.name = name;
    	this.pwd = pwd;
    }
    public User_(){
    	
    }
    public User_(String name){
    	this.name = name;
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
	
	@Override
	public String toString(){
		return this.name+"#"+this.pwd;
	}
	@Override
	public int compareTo(User_ o) {
		return -this.name.compareTo(o.name);
	}
}
