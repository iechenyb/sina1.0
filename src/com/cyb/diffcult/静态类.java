package com.cyb.diffcult;
//静态方法不能访问非静态方法和非静态成员变量
//pirvate protect  一般的非内部类，是不允许有 private 与protected权限的，但内部类可以
public class 静态类 {
	static int a = 3;
	int c = init("no");
    static int b=init("yes");
	static void meth(int x) {
		System.out.println("静态方法执行1。");
		System.out.println("x = " + x);
		System.out.println("a = " + a);
		System.out.println("b = " + b);
		System.out.println("-----------------------");
	}
    void test1(){
    	System.out.println(a);//非静态方法可以访问静态成员变量。
    	System.out.println(c);//非静态方法访问非静态成员
    	meth(0);//访问静态方法
    }
    static void  test2(){
    	System.out.println(a);//非静态方法可以访问静态成员变量。
    	//System.out.println(c);//静态方法访问非非静态成员，不可以
    	meth(0);//访问静态方法
    	//test1(0);//访问非静态方法不可以。
    }
	static {
		//meth(1);
		System.out.println("static block initialized1");
		b = a * 4;
	}
	static {
		//meth(2);
		a = 1;
		System.out.println("static block initialized2");
		b = a * 4;
	}
	static void meth2(int x) {
		System.out.println("静态方法执行2。");
		System.out.println("x = " + x);
		System.out.println("a = " + a);
		System.out.println("b = " + b);
		System.out.println("-----------------------");
	}
    static int init(String str){
    	System.out.println("成员变量初始化。"+str);
    	System.out.println("-----------------------");
    	return 2;
    }
	public static void main(String[] args) {
		/*for(int i=0;i<10;i++){
			System.out.println("执行次数："+(i+1));
			meth(42);//静态语句块仅仅加载一次
		}*/
	}
}
