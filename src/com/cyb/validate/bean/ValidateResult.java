package com.cyb.validate.bean;

/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2018年2月9日
 */
public class ValidateResult {
	private String message;
    private boolean valid;
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}
	public String toString(){
		return this.message;
	}
}
