package com.cyb.abstrac;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年12月1日
 */
public class Main {
	Log log = LogFactory.getLog(Main.class);
	public static void main(String[] args) {
		AbstractAnimal  fish = new Fish();
		fish.breath();
		fish.print();//抽象类的父类
		//fish.fatherMethod();不可见
		AbstractAnimal  tigger = new Tigger();
		tigger.breath();
		tigger.print();
		//tigger.grandMethod();//不可见
		//tigger.fatherMethod();//不可见
	}
}
