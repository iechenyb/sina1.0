package com.cyb.diffcult.structurecontrol;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年8月28日
 */
public class Dowhile {
	static Log log = LogFactory.getLog(Dowhile.class);
	static int i=0;static int i1=0;
	public static void main(String[] args) {
		do{
			log.info(i);
		}while(max()<0);//至少执行一次 
		
		while(max1()<0){
			log.info(i1);
		}//至少执行一次
		
	}
	/**
	 * 2017-08-28 21:16:41,984 com.cyb.diffcult.structurecontrol.Dowhile.main:14[main] 0
	 */
	static int max(){
		return i++;
	}
	static int max1(){
		return i1++;
	}
}
