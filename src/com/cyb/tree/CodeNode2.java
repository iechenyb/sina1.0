package com.cyb.tree;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.Assert;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2018年1月30日
 */
public class CodeNode2 {
	Log log = LogFactory.getLog(CodeNode2.class);
	// 节点对象，没有按照规范封装，直接.属性即可  
    String code;  
    int level;

    public List<CodeNode2> children = new ArrayList<CodeNode2>();  

    CodeNode2() {  
        this.code = "000"; 
        this.level=0;
    }  

    CodeNode2(String code) {  
        this.code = code;  
        Assert.isTrue(code.length()%3==0,"code的长度必须为3的倍数！");
        this.level = code.length()/3;
    }

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public List<CodeNode2> getChildren() {
		return children;
	}

	public void setChildren(List<CodeNode2> children) {
		this.children = children;
	}

	@Override
	public boolean equals(Object obj) {
		CodeNode2 o = (CodeNode2)obj;
		return this.code==o.code;
	}  
    
}
