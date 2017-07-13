package ${clazz.packagename};  
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator; 
 @Entity
 @Table(name="${tableName}")
 @Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
 public class ${clazz.classname} {
 	@Id
	@GenericGenerator(strategy="uuids",name="user_uuid")
	@GeneratedValue(generator="user_uuid")
	private String id;   
	<#list attributes as being>
    <#if being.len=0> 
    @Column
	<#else> 
  	@Column(length=${being.len})
	</#if>    
    private ${being.type} ${being.name};  
	</#list>
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}  
	<#list attributes as being>
    public ${being.type} get${being.name?cap_first}() {  
        return ${being.name};  
    }  
    public void set${being.name?cap_first}(${being.type} ${being.name}) {  
        this.${being.name} = ${being.name};  
    }  
	</#list>
 }