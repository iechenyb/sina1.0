package com.cyb.indiator;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cyb.data.DataUtils;
import com.cyb.date.DateUtil;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年7月26日
 */
public class 累计年化 {
	Log log = LogFactory.getLog(累计年化.class);
	public static void main(String[] args) {
		long days = DateUtil.daysBetween(DateUtil.calendar("2015-07-27").getTime(),DateUtil.calendar("2017-07-21").getTime());
	    double ljnh = (1.2880-1)*365/days;
		System.out.println((DataUtils.e2String(ljnh*100, 2))+"%");
	}
}
