package com.cyb.tree;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.Assert;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2018年1月30日
 */
public class CodeNode {
	Log log = LogFactory.getLog(CodeNode.class);
	// 节点对象，没有按照规范封装，直接.属性即可  
    String code;  
    int level;

    public List<CodeNode> children = new ArrayList<CodeNode>();  
    
    public Map<String, CodeNode> childMap = new LinkedHashMap<String, CodeNode>();  

    CodeNode() {  
        this.code = "000"; 
        this.level=0;
    }  

    CodeNode(String code) {  
        this.code = code;  
        /*if(code.length()!=3){
        	throw new Exception("code的长度必须为3的倍数！");
        }*/
        Assert.isTrue(code.length()%3==0,"code的长度必须为3的倍数！");
        this.level = code.length()/3;
    }  

    /*@Override  
    public String toString() {  
        return this.code+"#"+level;  
    } */ 

    public String printSelf(CodeNode n, StringBuilder str) {  
        List<CodeNode> list = n.children;  
        if (list != null && list.size() > 0) {  
            for (int i = 0; i < list.size(); i++) {  
            	CodeNode node = list.get(i);  
                int childeSize = node.children.size();  
                String mark = "";  
                if (childeSize != 0) {  
                    mark = ":";  
                }  
                str.append("[code:" + node.code + mark);  
                printSelf(node, str);  
                str.append("]");  
                if (i + 1 != list.size()) {  
                    str.append(",");  
                }  
            }  
        }  
        return str.toString();  
    }  
}
