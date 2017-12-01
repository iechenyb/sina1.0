package com.cyb.abstrac;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cyb.interfac.FatherExtendGrandInterface;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年12月1日
 */
public class Tigger extends AbstractAnimal implements FatherExtendGrandInterface {
	Log log = LogFactory.getLog(Tigger.class);

	@Override
	void breath() {
		System.out.println("老虎在陆地上呼吸！");
	}

	@Override
	public void grandMethod() {
		
	}

	@Override
	public void fatherMethod() {
		
	}

	@Override
	public void print() {
		
	}

	
}
