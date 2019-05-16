package com.cyb.spring.componentscan;

public class WhitePeople extends IPeople {
 
	@Override
	@PeopleAnnotion(say = "White")
	public void say() {
		System.out.println("I am White");
	}
 
}