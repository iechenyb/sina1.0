package com.cyb.validate.parse;
import java.lang.reflect.Field;

import com.cyb.validate.bean.ValidateResult;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
// *创建时间: 2018年2月9日
 */
public interface IAnnotationParser {
	 public ValidateResult validate(Field f, Object value);
}
