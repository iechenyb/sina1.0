package com.cyb.diffcult.invoke;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 作者 : iechenyb<br>
 * 类描述: 说点啥<br>
 * 创建时间: 2017年8月31日
 */
public abstract class AbstractListener{
	Log log = LogFactory.getLog(AbstractListener.class);
	
	Listener listener;
	List<Listener> listeners = new ArrayList<Listener>();
	public void addLisenter(Listener listener) {
		this.listener = listener;
		listeners.add(listener);
	}
	
	public void notifyAllPerson(){
		for(Listener lis :listeners){
			lis.doEvent();
		}
	}
	public void notifyPerson(){
		listener.doEvent();
	}
}
