package com.cyb.xml.bean;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2018年3月19日
 */
import java.util.Date;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;  
  
@XmlRootElement  
public class Student {  
    @XmlElement(name="myname") // 如果  
    //@XmlElement  
    private String name;  
    @XmlElement  
    private int age;  
    @XmlElement  
    private Date birthday;  
    @XmlElement  
    private Role role;  
    @XmlAttribute  
    private Integer id;  
  
    @XmlTransient  
    public String getName() {  
        return name;  
    }  
  
    public void setName(String name) {  
        this.name = name;  
    }  
    @XmlTransient  
    public int getAge() {  
        return age;  
    }  
  
    public void setAge(int age) {  
        this.age = age;  
    }  
    @XmlTransient  
    public Date getBirthday() {  
        return birthday;  
    }  
  
    public void setBirthday(Date birthday) {  
        this.birthday = birthday;  
    }  
    @XmlTransient  
    public Role getRole() {  
        return role;  
    }  
  
    public void setRole(Role role) {  
        this.role = role;  
    }  
    @XmlTransient  
    public Integer getId() {  
        return id;  
    }  
  
    public void setId(Integer id) {  
        this.id = id;  
    }  
  
    @Override  
    public String toString() {  
        return "Student [name=" + name + ", age=" + age + ", birthday=" + birthday + ", role=" + role + ", id=" + id + "]";  
    }  
  
}  
