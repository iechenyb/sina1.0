package com.cyb.spring.componentscan;

public class YellowPeople extends IPeople{
 
	@Override
	@PeopleAnnotion(say="Yellow")
	public void say() {
		System.out.println("I am Yellow");			
	}
 
}