package com.cyb.jvm;

public class 栈溢出 {
	public static void main(String[] args) {
		//-Xmx20m -Xms5m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=d:/a.dump
		print();//java.lang.StackOverflowError
	}
	public static void print(){
		print();
	}
}
