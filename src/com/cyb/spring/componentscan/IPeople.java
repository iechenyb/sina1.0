package com.cyb.spring.componentscan;

public abstract class IPeople {
	@PeopleAnnotion(say="NoColor")
	public abstract void say();
	
	public void walk(){
		System.out.println("I can Walk");
	}
 
}