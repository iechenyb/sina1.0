package com.cyb.validate;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 作者 : iechenyb<br>
 * 类描述: 说点啥<br>
 * 创建时间: 2018年2月9日
 */
public class AnnotationRule implements Rule {
	Log log = LogFactory.getLog(AnnotationRule.class);
	private String message;
	private Object o;
	public AnnotationRule(Object o) {
        this.o = o;
        isValid();
    }
	
	@Override
	public String getMessage() {
		return message;
	}

	@Override
	public boolean isValid() {
		List<?> rs = AnnotationValidator.validate(o);
		if(rs.size()>0){
			setMessage(rs.toString());
			return false;
		}else{
			return true;
		}
	}
	public static boolean isValid(Object o){
		List<?> rs = AnnotationValidator.validate(o);
		if(rs.size()>0){
			return false;
		}else{
			return true;
		}
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
