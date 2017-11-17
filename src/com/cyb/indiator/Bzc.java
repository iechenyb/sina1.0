package com.cyb.indiator;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年11月17日
 */
public class Bzc {
	public int order=0;//第几天
	public int days=0;//累计天数
	public double drsyl=0;//当日收益率
	public double ljsyl=0;//累计交易天数
    public double bzc=0;//当日标准差 增量计算
	public double ckbzc=0;//当日参考标准差 传统方式
	public double pjsyl=0;//平均收益率
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	public int getDays() {
		return days;
	}
	public void setDays(int days) {
		this.days = days;
	}
	public double getDrsyl() {
		return drsyl;
	}
	public void setDrsyl(double drsyl) {
		this.drsyl = drsyl;
	}
	public double getLjsyl() {
		return ljsyl;
	}
	public void setLjsyl(double ljsyl) {
		this.ljsyl = ljsyl;
	}
	public double getBzc() {
		return bzc;
	}
	public void setBzc(double bzc) {
		this.bzc = bzc;
	}
	public double getCkbzc() {
		return ckbzc;
	}
	public void setCkbzc(double ckbzc) {
		this.ckbzc = ckbzc;
	}
	public double getPjsyl() {
		return pjsyl;
	}
	public void setPjsyl(double pjsyl) {
		this.pjsyl = pjsyl;
	}
	
}
