package com.cyb.aop;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.aop.framework.AopContext;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.aop.framework.ProxyFactory;

/**
 * 作者 : iechenyb<br>
 * 类描述: 说点啥<br>
 * 创建时间: 2018年8月22日
 */
/*boolean equalsProxiedInterfaces(AdvisedSupport a, AdvisedSupport b)
判断两个（即将）代理出来的对象是否拥有相同接口；

boolean equalsAdvisors(AdvisedSupport a, AdvisedSupport b)
判断两个（即将）代理出来的对象是否拥有相同的建议者（Advisor）；

boolean equalsInProxy(AdvisedSupport a, AdvisedSupport b)
判断两个（即将）代理出来的对象是否相同；*/

public class Test2 {
	Log log = LogFactory.getLog(Test2.class);
	/**
	 * 
	 *作者 : iechenyb<br>
	 *方法描述: 代理普通的类，没有接口<br>
	 *创建时间: 2017年7月15日
	 *@param args
	 */
	public static void main(String[] args) {
		ProxyFactory pf = new ProxyFactory();
		Object target = new GreetingImpl2();
		pf.setTarget(target);
		pf.setProxyTargetClass(true);//直接代理类
		//如果当前类的路径满足切面的路径规则，添加advice
		pf.addAdvice(new Advice());// 根据add的切面顺序执行切面，拦截器优先于adivce执行
		pf.addAdvice(new GreetingBeforeAndAfterAdvice());
		pf.addAdvisor(new GreetingAdvisor());
		pf.setExposeProxy(true);
		GreetingImpl2 greeting = (GreetingImpl2) pf.getProxy();
		System.out.println("代理对象："+greeting);
		/*
		调用底层的addAdvice实际上还是调用了addAdvistor。关系是后者使用前者。
		Advice是Advisor的成员
		通过字面意思，Advisor是顾问，Advice是顾问中的通知，当操作了通知，也就是操作了顾问.
		*/
		((GreetingImpl)(AopContext.currentProxy()))
		.sayHello("helloiechenyb!");//不可用
	}

	/*public AopProxy createAopProxy(AdvisedSupport config) 
			throws AopConfigException {
		if (config.isOptimize() || config.isProxyTargetClass() 
				|| hasNoUserSuppliedProxyInterfaces(config)) {
			Class<?> targetClass = config.getTargetClass();
			if (targetClass == null) {
				throw new AopConfigException("TargetSource cannot determine target class: "
						+ "Either an interface or a target is required for proxy creation.");
			}
			if (targetClass.isInterface()) {
				return new JdkDynamicAopProxy(config);
			}
			return new ObjenesisCglibAopProxy(config);
		} else {
			return new JdkDynamicAopProxy(config);
		}
	}*/
}
/**http://elim.iteye.com/blog/2396274
 * 对于环绕通知只能添加一个,多添加也是没有用的，spring会选第一个advice，请看结果
 *
 * 从运行结果来看，配置多个通知时，前置通知正序执行，后置通知逆序执行，环绕通知前正、后逆。
 * MethodInterceptor：方法拦截器可以实现MethodBeforeAdvice接口、
 * AfterReturningAdvice接口、ThrowsAdvice三个接口所能够实现的效果。
注：BeanPostProcessor、ProxyFactory、MethodInterceptor可用来实现动态数据源。
*/