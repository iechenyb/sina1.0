package com.cyb.validate.parse;

import java.lang.reflect.Field;

import com.cyb.validate.annotation.NotNull;
import com.cyb.validate.bean.ValidateResult;

/**
 * 作者 : iechenyb<br>
 * 类描述: 说点啥<br>
 * 创建时间: 2018年2月9日
 */
public class NotNullParser implements IAnnotationParser {
	@Override
	public ValidateResult validate(Field f, Object value) {
		ValidateResult result = new ValidateResult();
		if (f.isAnnotationPresent(NotNull.class)) {
			NotNull notNull = f.getAnnotation(NotNull.class);
			if (value == null || "".equals(value)) {
				result.setMessage(notNull.fieldName() + "不能为空!");
				result.setValid(false);
			}
		}
		return result;
	}
}
