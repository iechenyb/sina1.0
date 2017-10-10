package com.cyb.diffcult;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年10月10日
 *https://wenku.baidu.com/view/ece6425da216147916112822.html
 */
public class 哈希散列 {
	static Log log = LogFactory.getLog(哈希散列.class);
	public static void main(String[] args) {
		int a=200,b=64;
		log.info(a%b);
		log.info(a&(b-1));//与运算取模
		log.info(a-((a>>6)<<6));//移位运算取模
		log.info(Integer.toBinaryString(a));
	    log.info(Integer.toBinaryString(a>>6));//取除数 a/64
	    log.info(Integer.toBinaryString((a>>6)<<6)); //取 除数*64
	    log.info((a>>6)<<6);//a-除数*64=余数
	}
}
