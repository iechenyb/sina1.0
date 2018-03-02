package com.cyb.thread;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2018年3月2日
 */
public class MyThreadLocal<T> {
	Log log = LogFactory.getLog(MyThreadLocal.class);
	  private Map<Thread, T> map 
	  = Collections.synchronizedMap(new HashMap<Thread, T>());
	  
	    public void set(T newValue) {
	        map.put(Thread.currentThread(), newValue);
	    }
	 
	    public T get() {
	        return map.get(Thread.currentThread());
	    }
	 
	    public void remove() {
	        map.remove(Thread.currentThread());
	    }
}
