package com.cyb.validate.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2018年2月9日
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DateFormat {
    public String fieldName();
    public String format();
}
