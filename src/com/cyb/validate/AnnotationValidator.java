package com.cyb.validate;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cyb.validate.annotation.NotNull;
import com.cyb.validate.bean.ValidateResult;
import com.cyb.validate.parse.IAnnotationParser;
import com.cyb.validate.parse.NotBlankParser;
import com.cyb.validate.parse.NotNullParser;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2018年2月9日
 */
public class AnnotationValidator {
	static Log log = LogFactory.getLog(AnnotationValidator.class);

	    private final static List<IAnnotationParser> vList = new ArrayList<IAnnotationParser>();
	    static {
	        vList.add(new NotNullParser());
	        vList.add(new NotBlankParser());
	    }

	    /**
	     * 遍历所有字段，用所有解析器进行校验，如果校验失败，则终止校验返回结果，如果校验成功，同样返回校验结果
	     * @param t
	     * @return
	     */
	    public static <T> List<ValidateResult> validate(T t){
	        ValidateResult result = null;
	        List<ValidateResult> lst = new ArrayList<ValidateResult>();
	        for (Field f : t.getClass().getDeclaredFields()) {
	            f.setAccessible(true);
	            Object value = null;
	            try {
	                value = f.get(t);
	            } catch (IllegalArgumentException e) {
	                log.info("Exception", e);
	            } catch (IllegalAccessException e) {
	                log.info("Exception", e);
	            }

	            for (IAnnotationParser va : vList) {
		                result = va.validate(f, value);
		                if(!result.isValid()){
		                	//没有验证信息，则跳过
		                	if(result.getMessage()!=null){//跳过验证
		                		lst.add(result);
		                	}
		                }
	            }
	        }
	        //System.out.println(lst);
	        return lst;
	    }

	    /**
	     * 注册解析器
	     * @param parser
	     */
	    public static void register(IAnnotationParser parser){
	        vList.add(parser);
	    }
	    
	    public static boolean hasAnnotation(Annotation[] annotations){
	    	boolean has = false;
	    	List<Annotation> fans = Arrays.asList(annotations);
	    	for(Annotation an:fans){
	    		System.out.println(an.getClass().getSimpleName());
	    		if(vList.contains(an)){
	    			has = true;
	    			System.out.println("has annotation");
	    			break;
	    		}
	    	}
	    	return has;
	    }
	    public static boolean hasAnnotation(Field f){
	    	boolean has = false;
	    	for(IAnnotationParser an:vList){
	    		System.out.println(an.getClass().getSimpleName());
	    		if(vList.contains(an)){
	    			has = true;
	    			System.out.println("has annotation");
	    			break;
	    		}
	    	}
	    	return has;
	    }
	    
	    public Map<String, Object> notNull(Object value, Field field) {  
	        Map<String, Object> validateResult = new HashMap<String, Object>();  
	        NotNull annotation = field.getAnnotation(NotNull.class);  
	        if(annotation==null){
	        	return null;//不需要校验
	        }
	        return validateResult;  
	    }  
}
