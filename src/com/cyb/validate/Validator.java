package com.cyb.validate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2018年2月9日
 */
public class Validator {
	Log log = LogFactory.getLog(Validator.class);
	public Validator validate(Rule rule) {
		/*isValid 初始化为true，如果一直未true，则校验正确，
		 * 如果一个环节出现false，则后续不在进行消息复制，
		 * 校验停留在第一个false点。
        */
		if(this.isValid){
            this.isValid = rule.isValid();
            this.message = rule.getMessage();
        }
        return this;
    }

    public Validator validateAnnotation(Object o){
        return validate(new AnnotationRule(o));
    }

    public Validator notNull(Object fieldValue, String fieldName) {
        if(this.isValid){
            if(fieldValue == null){
                this.isValid = false;
                this.message = fieldName + "不能为空";
            }
        }
        return this;
    }

    /**
     * 是否有效
     * @return
     *      true 校验通过，值有效
     *      message 校验未通过的错误信息
     */
    public boolean isValid() {
        return isValid;
    }
    public String getMessage() {
        return message;
    }

    private boolean isValid = true;        // 是否校验开关
    private String message;  
}
