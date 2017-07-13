package com.cyb.freemarker.mvc.po;  
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator; 
import java.util.ArrayList;  
import java.util.List;  
 @Entity
 @Table(name="tb_role")
 @Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
 public class Role {
 	@Id
	@GenericGenerator(strategy="uuids",name="user_uuid")
	@GeneratedValue(generator="user_uuid")
	private String id;   
  	@Column(length=50)
    private String name;  
    @Column
    private int age;  
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}  
    public String getName() {  
        return name;  
    }  
    public void setName(String name) {  
        this.name = name;  
    }  
    public int getAge() {  
        return age;  
    }  
    public void setAge(int age) {  
        this.age = age;  
    }  
 }