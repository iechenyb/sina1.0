package com.cyb.abstrac;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 作者 : iechenyb<br>
 * 类描述:抽象类不用实现接口的方法<br>
 * 创建时间: 2017年12月1日
 */
public abstract class AbstractAnimal implements MyInterfacce {
	Log log = LogFactory.getLog(AbstractAnimal.class);
	String type;
	String name;
	int age;
	int weight;

	void eat() {
		System.out.println("动物爱吃饭");
		breath();//调用子类的实现
		fly();//调用子类的实现
	}
    
	// 抽象方法在抽象类中只能声明，不能具体实现
	abstract void breath();
	abstract void fly();//子类必须重写
	void sleep() {
		System.out.println("动物在睡觉");
	}
}
