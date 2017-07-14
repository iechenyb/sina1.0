package com.cyb.fanxing;

public interface BaseDao<T,T1> {  
    T get(String id);  
    T1 getT1(String id);  
} 
