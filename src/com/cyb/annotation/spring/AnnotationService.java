package com.cyb.annotation.spring;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented  
@Target({ElementType.METHOD, ElementType.TYPE, ElementType.FIELD})  
@Retention(RetentionPolicy.RUNTIME)  
public @interface AnnotationService {  
    //public String className() default "";  
}  