package com.cyb.aop;

import java.lang.reflect.Method;

import org.aopalliance.aop.Advice;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.PointcutAdvisor;

/**
 * 作者 : iechenyb<br>
 * 类描述: 说点啥<br>
 * 创建时间: 2018年8月22日
 */
public class GreetingAdvisor implements PointcutAdvisor {
	Log log = LogFactory.getLog(GreetingAdvisor.class);

	@Override
	public Advice getAdvice() {
		/*return new MethodBeforeAdvice() {

			@Override
			public void before(Method method, Object[] args,
					Object target) throws Throwable {
				System.out.println("BeforeAdvice实现，在目标方法被调用前调用，目标方法是："
						+ method.getDeclaringClass().getName() + "." + method.getName());
			}
		};*/return new GreetingAdviceAdvisor();
	}

	@Override
	public boolean isPerInstance() {
		return false;
	}

	@Override
	public Pointcut getPointcut() {
		return Pointcut.TRUE;
	}
}
