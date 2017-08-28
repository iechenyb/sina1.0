package com.cyb.diffcult.structurecontrol;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年8月28日
 */
public class Case {
	static Log log = LogFactory.getLog(Case.class);
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		switch ("aa") {
		case "aa":
			log.info("支持字符串");
			break;
		default:
			break;
		}
		
		switch ('a') {
		case 'a':
			log.info("支持字符");
			break;
		default:
			break;
		}
		
		switch (1) {
		case 1:
			log.info("支持数字");
			break;
		default:
			break;
		}
		long i=0l;//不支持
		byte i1=0;//支持
		short i2=0;//支持
		boolean i3=true;//不支持
		switch (i2) {
		case 1:
			log.info("支持数字");
			break;
		default:
			break;
		}
	}
}
