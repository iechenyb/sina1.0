package com.cyb.diffcult;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年8月23日
 */
public class SwitchStudy {
	Log log = LogFactory.getLog(SwitchStudy.class);
	public static void main(String[] args) {
		int i=9;
		switch(i){ 
			case 1: System.out.print("");
			default: System.out.print("Error");
			case 2: System.out.print("Good");
			case 3: System.out.print("Best"); 
		}
		//ErrorGoodBest
	}
}
