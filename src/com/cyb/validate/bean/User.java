package com.cyb.validate.bean;

import com.cyb.validate.annotation.DateFormat;
import com.cyb.validate.annotation.NotBlank;
import com.cyb.validate.annotation.NotNull;
import com.cyb.validate.annotation.RegexExpression;

/**
 * 作者 : iechenyb<br>
 * 类描述: 说点啥<br>
 * 创建时间: 2018年2月9日
 */
public class User {
	
	@NotBlank(fieldName = "主键")
	@NotNull(fieldName = "主键")
	private Long id;
	
	@NotBlank(fieldName = "姓名")
	@NotNull(fieldName = "姓名")
	private String name;
	
	//@Less(fieldName = "年龄", value = 100)
	@NotNull(fieldName = "年龄")
	@NotBlank(fieldName = "年龄")
	private int age;
	
	@RegexExpression(fileName="手机",expression="[0-9]{11}", desc = "必须为11位数字")
	@NotBlank(fieldName = "手机")
	@NotNull(fieldName = "手机")
	private String phone;
	
	@DateFormat(fieldName="生日",format="yyyy-mm-dd")
	@NotBlank(fieldName = "生日")
	@NotNull(fieldName = "生日")
	private String birthday;
	
    static String EMAIL = "SDFS";
	
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
}
