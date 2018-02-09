package com.cyb.validate.test;

import java.util.List;

import org.springframework.util.CollectionUtils;

import com.cyb.validate.AnnotationRule;
import com.cyb.validate.AnnotationValidator;
import com.cyb.validate.Validator;
import com.cyb.validate.bean.User;
import com.cyb.validate.parse.DateFormatParser;
import com.cyb.validate.parse.RegexParser;

/**
 * 作者 : iechenyb<br>
 * 类描述: 说点啥<br>
 * 创建时间: 2018年2月9日
 */
public class TestAnnotationValidator {
	/**
	 * 
	 * 作者 : iechenyb<br>
	 * 方法描述: 不同的注解如何跟指定类型的注解进行强制关联？<br>
	 * 创建时间: 2017年7月15日
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		User user = new User();
		user.setId(90l);
		user.setName("wzj");
		user.setAge(21);
		user.setBirthday("20150102");// 正确格式  2015-05-25
		user.setPhone("1365645");//非数字提示错误
		AnnotationValidator.register(new DateFormatParser());//日期校验
		AnnotationValidator.register(new RegexParser());//万能正则表达式匹配校验
		showRs(AnnotationValidator.validate(user));
		showRs(new AnnotationRule(user));
		showRs(new Validator()
				.validate(new AnnotationRule(user))
				.validate(new AnnotationRule(user)));//多重校验
	}
	public static void showRs(List<?> lst){
		if (CollectionUtils.isEmpty(lst)) {
			System.out.println("校验通过");
		} else {
			System.out.println(lst);
		}
	}
	public static void showRs(AnnotationRule ar){
		if (ar.isValid()) {
			System.out.println("校验通过");
		} else {
			System.out.println(ar.getMessage());
		}
	}
	public static void showRs(Validator ar){
		if (ar.isValid()) {//发起验证
			System.out.println("校验通过");
		} else {
			System.out.println(ar.getMessage());
		}
	}
}
