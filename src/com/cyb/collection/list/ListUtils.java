package com.cyb.collection.list;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 *作者 : iechenyb<br>
 *类描述: 只对数字型数组进行比较，其他字符串和复杂对象不处理。<br>
 *创建时间: 2019年1月8日
 */
public class ListUtils {
	Log log = LogFactory.getLog(ListUtils.class);
	/**
	 * 
	 *作者 : iechenyb<br>
	 *方法描述: 查找element在集合中的位置<br>
	 *创建时间: 2017年7月15日
	 *@param data
	 *@param element
	 *@return
	 */
	public static int elementIndex(List<String> data,String element){
	    int ele = Integer.valueOf(element);
		for(String e:data){
	    	if(Integer.valueOf(e)>=ele){
	    		return data.indexOf(e);
	    	}
	    }
		return -1;	
	}
	/**
	 * 
	 *作者 : iechenyb<br>
	 *方法描述: 查找element在集合中的位置<br>
	 *创建时间: 2017年7月15日
	 *@param data
	 *@param element
	 *@return
	 */
	public static int elementIndex(List<Integer> data,int element){
		for(Integer e:data){
	    	if(e.intValue()>=element){
	    		return data.indexOf(e);
	    	}
	    }
		return -1;	
	}
	
	
}
