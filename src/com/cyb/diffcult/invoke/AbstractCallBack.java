package com.cyb.diffcult.invoke;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 作者 : iechenyb<br>
 * 类描述: 说点啥<br>
 * 创建时间: 2017年8月31日
 */
public abstract class AbstractCallBack{
	Log log = LogFactory.getLog(AbstractCallBack.class);
	
	CallBack callBack;

	public void addLisenter(CallBack callBack) {
		this.callBack = callBack;
	}
}
