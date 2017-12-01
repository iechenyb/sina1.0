package com.cyb.abstrac;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cyb.interfac.FatherInterface;
/**
 *作者 : iechenyb<br>
 *类描述: 抽象类不需要对接口方法进行实现<br>
 *创建时间: 2017年12月1日
 */
public class Fish extends AbstractAnimal implements FatherInterface{
	Log log = LogFactory.getLog(Fish.class);

	@Override
	void breath() {
		System.out.println("鱼在水里呼吸！");
	}

	@Override
	public void fatherMethod() {
		
	}

	@Override
	public void print() {
		
	}

	
}
