package com.cyb.id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.annotations.GenericGenerator;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2018年8月29日
 */
public class HibernateIdType {
	Log log = LogFactory.getLog(HibernateIdType.class);
	@SequenceGenerator(name="generator",sequenceName="SEQ_NAME")
	@Id
	@Column(name="id",unique=true,nullable=false,precision=22,scale=0)
	public Long id1;
	
	//自增
	@GeneratedValue(strategy = GenerationType.IDENTITY)//GenerationType.AUTO
	@Id
	@Column(name="id",unique=true,nullable=false,precision=22,scale=0)
	public Long id2;
	
	@SequenceGenerator(name="generator",sequenceName="SEQ_NAME")
	@Id
	@Column(name="id",unique=true,nullable=false,precision=22,scale=0)
	public Long id3;
	
	@SequenceGenerator(name="generator",sequenceName="SEQ_NAME")
	@Id
	@Column(name="id",unique=true,nullable=false,precision=22,scale=0)
	public Long id4;
	
	@Id  
	@GeneratedValue(generator="stu_id")  
	@GenericGenerator(name="stu_id",strategy="uuid")
	public String getSid() {return null;}
}
