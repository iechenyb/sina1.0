package com.cyb.regex;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cyb.collection.utils.RandomUtils;
import com.cyb.context.TimeContext;

/**
 * 作者 : iechenyb<br>
 * 类描述: 说点啥<br>
 * 创建时间: 2018年9月11日
 */
public class 逻辑表达式 {
	Log log = LogFactory.getLog(逻辑表达式.class);

	public static void test() {
		MessageFormat mf = new MessageFormat("aaa{0}{0}", Locale.US);
		Object arr[] = { "he" };
		System.out.println(mf.format(arr));

		String booleanString = "{0}>1 && {0}<=5";
		System.out.println(Boolean.valueOf(MessageFormat.format(booleanString, 6)));
		System.out.println(Boolean.valueOf(MessageFormat.format(booleanString, 2)));

		String expression = "value>1 && value<=5";
		Boolean rs = assertExpressTrue("2", expression);
		for (int i = 1; i < 8; i++) {
			rs = assertExpressTrue(i + "", expression);
			System.out.println(i + ",rs=" + rs);
		}
	}
    static List<String> expressions = new ArrayList<String>();
    static{
    	expressions.add("value>1 && value<=5");
    	expressions.add("value>1 || value<=5");
    	expressions.add("(value>1 && value<=5)||value > 10");
    }
    
    static List<String> arithmetics = new ArrayList<String>();
    static{
    	arithmetics.add("10 * 2 + 6 / (3 - 1)");
    	arithmetics.add("1/100+10");
    	arithmetics.add("2*3+5*2/100");
    }
    
	public static void main(String[] args) throws InterruptedException {
		TimeContext.recordTimeStart();
		test();
		int i=0;
		while(i<10000){
			i++;
			for(String ex:expressions){
				String param = String.valueOf(RandomUtils.getNum(1, 20));
				boolean res = assertExpressTrue(String.
						valueOf(param),
						ex);
				System.out.println("表达式："+ex+"\t参数:"+param+"\t"+res);
			}
		}
		for(String ex:arithmetics){
			String result = calStringExpression(ex);
			System.out.println("表达式："+ex+"\t结果:"+result);
		}
		TimeContext.calExecuteTime();
	}

	/**
	 * 
	 * @author: Longjun
	 * @Description: 将${money>=2000&&money<=4000}字符串截取成
	 *               "money>=2000&&money<=4000"， 然后判断一个数值字符串是否在此区间内
	 * @date:2016年3月21日 上午11:25:32
	 */
	public static Boolean assertExpressTrue(String value, String expression) {
		String el = expression;
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("js");
		engine.put("value", value);
		boolean eval = false;
		try {
			eval = (boolean) engine.eval(el);
		} catch (ScriptException e) {
			e.printStackTrace();
		}
		return eval;
	}

	public static String calStringExpression(String expression) {
		ScriptEngineManager scriptEngineManager 
		= new ScriptEngineManager();
		ScriptEngine scriptEngine 
		= scriptEngineManager.getEngineByName("js");//nashorn
		try {
			String result = String.valueOf(scriptEngine.eval(expression));
			return result;
		} catch (ScriptException e) {
			e.printStackTrace();
			return "";
		}
	}
}
