package com.cyb.reflect.sql;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;  
@Target({ElementType.TYPE}) //作用在类或接口上  
@Retention(RetentionPolicy.RUNTIME)  
@interface MyTable {  
   String value();  
} 
@Target({ElementType.FIELD})    //作用在字段上  
@Retention(RetentionPolicy.RUNTIME)  
@interface MyColome {  
    String value();  
} 
@MyTable("t_user")  
public class User {  
    @MyColome("id")  
    private String id;  
      
    @MyColome("user_name")  
    private String userName;  
      
    @MyColome("tel")  
    private String tel;  
      
      
    public String getId() {  
        return id;  
    }  
    public void setId(String id) {  
        this.id = id;  
    }  
    public String getUserName() {  
        return userName;  
    }  
    public void setUserName(String userName) {  
        this.userName = userName;  
    }  
    public String getTel() {  
        return tel;  
    }  
    public void setTel(String tel) {  
        this.tel = tel;  
    }  
      
} 