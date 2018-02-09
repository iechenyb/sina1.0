package com.cyb.validate.parse;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.cyb.validate.annotation.DateFormat;
import com.cyb.validate.bean.ValidateResult;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2018年2月9日
 */
public class DateFormatParser implements IAnnotationParser{

    /**
     * 校验f字段的值是否符合value的日期格式
     * @see DateFormat
     */
    @Override
    public ValidateResult validate(Field f, Object value) {
        ValidateResult result = new ValidateResult();
        if(f.isAnnotationPresent(DateFormat.class)){
            DateFormat dateFormat = f.getAnnotation(DateFormat.class);
            try {
                if(value != null){
                    SimpleDateFormat format = new SimpleDateFormat(dateFormat.format());
                    format.parse(value.toString());
                }
            } catch (ParseException e) {
                result.setMessage(dateFormat.fieldName() + "不满足格式：" + dateFormat.format());
            }   
        }
        return result;
    }
}
