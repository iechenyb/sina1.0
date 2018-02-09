package com.cyb.validate.parse;

import java.lang.reflect.Field;

import com.cyb.regex.RegExpValidateUtils;
import com.cyb.validate.annotation.RegexExpression;
import com.cyb.validate.bean.ValidateResult;

/**
 * 作者 : iechenyb<br>
 * 类描述: 说点啥<br>
 * 创建时间: 2018年2月9日
 */
public class RegexParser implements IAnnotationParser {
	/**
	 * 校验字段f的值不能为null或者是空字符串，校验结果保存在result中
	 */
	@Override
	public ValidateResult validate(Field f, Object value) {
		ValidateResult result = new ValidateResult();
		if (f.isAnnotationPresent(RegexExpression.class)) {
			RegexExpression regex = f.getAnnotation(RegexExpression.class);
			if(value==null){
				value="";
			}
			if (!RegExpValidateUtils.match(regex.expression(), value.toString())) {
				result.setMessage(regex.fileName() + regex.desc());
			}
		}
		return result;
	}
}
