package com.cyb.diffcult.other;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年8月23日
 */
public class PublicCls {
	
	Log log = LogFactory.getLog(PublicCls.class);
	private String priStr;
	public String pubStr;
	String defStr;
	
	
	protected String proStr;
	public void pubMethod(){}
	private void priMethod(){}
	protected void proMethod(){}
	void defMethod(){}
	
	public static void pubStaticMethod(){}
	private static void priStaticMethod(){}
	protected static void proStaticMethod(){}
	static void defStaticMethod(){}
	
	
	public String getPriStr() {
		priMethod();
		priStaticMethod();
		return priStr;
	}
	public void setPriStr(String priStr) {
		this.priStr = priStr;
	}
	public String getPubStr() {
		return pubStr;
	}
	public void setPubStr(String pubStr) {
		this.pubStr = pubStr;
	}
	public String getDefStr() {
		return defStr;
	}
	public void setDefStr(String defStr) {
		this.defStr = defStr;
	}
	public String getProStr() {
		return proStr;
	}
	public void setProStr(String proStr) {
		this.proStr = proStr;
	}
	
}
