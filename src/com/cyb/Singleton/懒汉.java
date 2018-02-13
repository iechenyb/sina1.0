package com.cyb.Singleton;

public class 懒汉 {
	public static void useSingle(){
		System.out.println(Singleton3.getInstance());
	}
	public static void main(String[] args) {
		System.out.println(Singleton.getInstance());
	}
}
//第一种（懒汉，线程不安全）这种写法lazy loading很明显，但是致命的是在多线程不能正常工作
class Singleton {
	private static Singleton instance;

	private Singleton() {
	}
	//Double-checked Locking (DCL)
	public static Singleton getInstance() {
		if (instance == null) {
			synchronized (Singleton.class) {
				if(instance==null){
					instance = new Singleton();
				}
			}
		}
		return instance;
	}
}
//第二种（懒汉，线程安全）这种写法能够在多线程中很好的工作，而且看起来它也具备很好的lazy loading，但是，遗憾的是，效率很低，99%情况下不需要同步
class Singleton2 {
	private static Singleton2 instance;

	private Singleton2() {
	}

	public static  synchronized Singleton2 getInstance() {
		if (instance == null) {
			instance = new Singleton2();
		}
		return instance;
	}
}