package com.cyb.validate.annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RegexExpression {
    public String expression();//表达式
    public String fileName();
    public String desc();
}