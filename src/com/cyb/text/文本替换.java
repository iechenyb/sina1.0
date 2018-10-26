package com.cyb.text;
import java.text.MessageFormat;
import java.util.Locale;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2018年9月11日
 */
public class 文本替换 {
	Log log = LogFactory.getLog(文本替换.class);
	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		MessageFormat mf = new MessageFormat("aaa{0}{0}", Locale.US);
        Object arr[] = {"he"};
        System.out.println(mf.format(arr));
        
        String booleanString = "{0}>1 && {0}<=5";
        System.out.println(Boolean.valueOf(mf.format(booleanString, 6)));
        System.out.println(Boolean.valueOf(mf.format(booleanString, 2)));
        
        
        String expression = "value>1 && value<=5";
        //expression = "30<value<=50";//不能正确解析
        //System.out.println(Boolean.parseBoolean(expression));
        Boolean rs = isInclude("2",expression);
        for(int i=1;i<8;i++){
        	rs = isInclude(i+"",expression);
        	System.out.println(i+",rs="+rs);
        }
        
        calStringExpression();
	}
	
	/**
	 * 
	 * @author: Longjun
	 * @Description: 将${money>=2000&&money<=4000}字符串截取成"money>=2000&&money<=4000"，
	 * 然后判断一个数值字符串是否在此区间内
	 * @date:2016年3月21日 上午11:25:32
	 */
	public static Boolean isInclude(String elValue,String elString){
		String el = elString;//elString.substring(elString.indexOf("{")+1, elString.indexOf("}"));
		ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");
        engine.put("value",elValue);
        boolean eval = false;
		try {
			eval = (boolean) engine.eval(el);
		} catch (ScriptException e) {
			e.printStackTrace();
		}     
		return eval;
	}
	
	
	public static void calStringExpression(){
		 ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
	        ScriptEngine scriptEngine = scriptEngineManager.getEngineByName("nashorn");
	        String expression = "10 * 2 + 6 / (3 - 1)";

	        try {
	            String result = String.valueOf(scriptEngine.eval(expression));
	            System.out.println(result);
	        } catch (ScriptException e) {
	            e.printStackTrace();
	        }
	}
}
