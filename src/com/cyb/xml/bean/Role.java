package com.cyb.xml.bean;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2018年3月19日
 */
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;  
  
@XmlRootElement  
public class Role {  
    @XmlAttribute  
    private String roleId;  
    @XmlElement  
    private String roleName;  
    @XmlElement  
    private String memo;  
  
    @XmlTransient  
    public String getRoleId() {  
        return roleId;  
    }  
  
    public void setRoleId(String roleId) {  
        this.roleId = roleId;  
    }  
  
    @XmlTransient  
    public String getRoleName() {  
        return roleName;  
    }  
  
    public void setRoleName(String roleName) {  
        this.roleName = roleName;  
    }  
  
    @XmlTransient  
    public String getMemo() {  
        return memo;  
    }  
  
    public void setMemo(String memo) {  
        this.memo = memo;  
    }  
  
    @Override  
    public String toString() {  
        return "Role [roleId=" + roleId + ", roleName=" + roleName + ", memo=" + memo + "]";  
    }  
  
}  
