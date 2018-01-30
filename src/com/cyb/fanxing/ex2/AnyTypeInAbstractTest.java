package com.cyb.fanxing.ex2;

import java.util.Arrays;
import java.util.Random;

/**
 * 作者 : iechenyb<br>
 * 类描述: 泛型在抽象类中的使用<br>
 * 创建时间: 2017年12月1日
 */
 abstract class AnyTypeInAbstract<T> {
	public T x;
	public T y;
	public abstract void add();
	public abstract void sub();
	public String show(String method){
		return (this.x+method+this.getY()+"=");
	}
	public T getX(){
		return x;
	}
	public T getY(){
		return y;
	}
}
 
 public class AnyTypeInAbstractTest extends AnyTypeInAbstract<Integer>{

	 public AnyTypeInAbstractTest(){
		 x = new Random().nextInt(100);
		 y = new Random().nextInt(500);
	 }
	 
	@Override
	public void sub() {
		System.out.println(show("-")+(x-y));
	}
	
	@Override
	public void add() {
		System.out.println(show("+")+(x+y));
	}
	public static void main(String[] args) {
		new AnyTypeInAbstractTest().sub();
		new AnyTypeInAbstractTest().add();
	}

 }
