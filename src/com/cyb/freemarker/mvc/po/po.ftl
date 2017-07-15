package ${clazz.packagename};  
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator; 
/**
 *作者: ${author}<br>
 *类描述: 说点啥<br>
 *创建时间: ${date}
 */
@Entity
@Table(name="${clazz.tableName}")
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ${clazz.classname} {
 	@Id
	@GenericGenerator(strategy="uuids",name="user_uuid")
	@GeneratedValue(generator="user_uuid")
	@Column(name="id",unique=true, nullable=false,length=50)
	private String id;   
	<#list attributes as being>
    <#if being.len=0> 
    @Column
	<#else> 
  	@Column(length=${being.len},nullable=${being.nullable?c},columnDefinition="${being.comment}")
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