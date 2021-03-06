package com.cyb.report;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
/**
 *作者 : iechenyb<br>
 *类描述: 单数据源<br>
 *创建时间: 2018年1月31日
 */
public class ReportDataSource implements JRDataSource {
	Log log = LogFactory.getLog(ReportDataSource.class);
	 private Iterator iter;  
     
	    //创建一个，map对象用与数据对应  
	    Map map = new HashMap();  
	      
	    //无参的构造函数  
	    public ReportDataSource(){  
	          
	    }  
	      
	    //以sex为参数的有参构造函数，用于数据初始化  
	    public ReportDataSource(String sex){  
	        //通过性别获取相应用户的数据  
	        List datas=DateSourceBaseFactory.createBeanCollection(sex);  
	        //要将List中的数据迭代，需要使用Iterator迭代对象  
	        iter=datas.iterator();  
	    }  
	      
	    //通过key获取value值  
	    public Object getFieldValue(JRField arg0) throws JRException {  
	        return map.get(arg0.getName());  
	    }  
	  
	    //接口JRDataSource的方法，判断是否有下一个数据  
	    public boolean next() throws JRException {  
	        if(iter.hasNext()){  
	            map=(Map)iter.next();  
	            return true;  
	        }  
	        return false;  
	    }  
}
