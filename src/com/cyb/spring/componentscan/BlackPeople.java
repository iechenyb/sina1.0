package com.cyb.spring.componentscan;

public class BlackPeople extends IPeople{
 
	@Override
	@PeopleAnnotion(say="Black")
	public void say() {
		System.out.println("I am Black");
	}
 
}