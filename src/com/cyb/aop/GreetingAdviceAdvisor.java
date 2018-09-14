package com.cyb.aop;
import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2018年8月22日
 */
public class GreetingAdviceAdvisor 
implements MethodBeforeAdvice,
AfterReturningAdvice,
MethodInterceptor{
	Log log = LogFactory.getLog(GreetingAdviceAdvisor.class);

	@Override
	public void before(Method arg0, Object[] arg1, Object arg2) throws Throwable {
		log.info("before...");
	}

	@Override
	public void afterReturning(Object arg0, Method arg1, Object[] arg2, Object arg3) throws Throwable {
		log.info("after...");
	}

	@Override
	public Object invoke(MethodInvocation arg0) throws Throwable {
		log.info("拦截器-调用目标方法之前。");
		Object result=arg0.proceed();
		log.info("拦截器-调用目标方法之后！");
		return result;
	}

}
