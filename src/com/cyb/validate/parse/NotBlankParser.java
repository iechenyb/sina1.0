package com.cyb.validate.parse;
import java.lang.reflect.Field;

import com.cyb.validate.annotation.NotBlank;
import com.cyb.validate.bean.ValidateResult;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2018年2月9日
 */
public class NotBlankParser implements IAnnotationParser{
	/**
     * 校验字段f的值不能为null或者是空字符串，校验结果保存在result中
     */
    @Override
    public ValidateResult validate(Field f, Object value) {
        ValidateResult result = new ValidateResult();
        if(f.isAnnotationPresent(NotBlank.class)){
            NotBlank notBlank = f.getAnnotation(NotBlank.class);
            if(value == null || "".equals(value)){
                result.setMessage(notBlank.fieldName() + "不能为空。");
            }
        }
        return result;
    }
}
