package com.cyb.tree;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import net.sf.json.JSONObject;


/**
 *作者 : iechenyb<br>
 *类描述: 将所有的节点先分级，1、2、3、4、5等<br>
 *创建时间: 2018年1月30日
 */
public class MyCodeTreeUtils {
	Log log = LogFactory.getLog(MyCodeTreeUtils.class);
	public static void main(String[] args) {
		CodeNode2 node = new CodeNode2();//  
		initTreeData(node);// 构建树前的初始化平铺数据，模拟数据库查询出的数据  
		Map<Integer, List<CodeNode2>> levelMap = new LinkedHashMap<Integer, List<CodeNode2>>();// 临时组织数据map
	    int maxLevel=1;
		for (CodeNode2 thisN : node.children) {  
			if(levelMap.containsKey(thisN.level)){
				levelMap.get(thisN.level).add(thisN);
				if(thisN.level>maxLevel){
					maxLevel = thisN.level;
				}
			}else{
				List<CodeNode2> newList = new ArrayList<CodeNode2>();
				newList.add(thisN);
				levelMap.put(thisN.level, newList);
				if(thisN.level>maxLevel){
					maxLevel = thisN.level;
				}
			}
        }
	    //从第一层级开始，逐渐遍历深层级的节点
		/*for(int i=1;i<maxLevel;i++){
			List<CodeNode2> curLayer = levelMap.get(i);//当前层级
			List<CodeNode2> nextLayer = levelMap.get(i+1);//获取下个层级
			for(int j=0;j<curLayer.size();j++){
				for(int k=0;k<nextLayer.size();k++){
					if(nextLayer.get(k).code.startsWith(curLayer.get(j).code)){
						//去重
						if(!curLayer.get(j).children.contains(nextLayer.get(k))){
							curLayer.get(j).children.add(nextLayer.get(k));
						}
					}
				}
			}
		}*/
		CodeNode2 cur = null;
		CodeNode2 next = null;
		Iterator<CodeNode2> curLayer = null;
		Iterator<CodeNode2> nextLayer = null;
		for(int i=1;i<maxLevel;i++){//深度遍历
			curLayer = levelMap.get(i).iterator();//当前层级
			while(curLayer.hasNext()){
				cur = curLayer.next();
				nextLayer = levelMap.get(i+1).iterator();//获取下个层级
				while(nextLayer.hasNext()){
					next = nextLayer.next();
					if(next.code.startsWith(cur.code)){
						if(!cur.children.contains(next)){
							cur.children.add(next);
						}
					}
				}
			}
		}
		node.setChildren(levelMap.get(1));//连接首层
		System.out.println(JSONObject.fromObject(node));
	}
	
	static List<CodeNode2> initTreeData(CodeNode2 node) {
		//node.children.add(new CodeNode2("0010"));//错误数据测试
		node.children.add(new CodeNode2("001"));
		node.children.add(new CodeNode2("001001"));
		node.children.add(new CodeNode2("001002"));
		node.children.add(new CodeNode2("002"));
		node.children.add(new CodeNode2("002001"));
		node.children.add(new CodeNode2("002002"));
		node.children.add(new CodeNode2("002002001"));
		node.children.add(new CodeNode2("002002002"));
		/*node.children.add(new CodeNode2("003"));//测试数据，当菜单维护重复的时候，是否考虑屏蔽掉？！
		node.children.add(new CodeNode2("003001"));
		node.children.add(new CodeNode2("003002"));
		node.children.add(new CodeNode2("003002001"));
		node.children.add(new CodeNode2("003002002"));
		node.children.add(new CodeNode2("003002003"));
		node.children.add(new CodeNode2("003002004"));
		node.children.add(new CodeNode2("003002005"));
		node.children.add(new CodeNode2("003002006"));*/
		node.children.add(new CodeNode2("005"));
		node.children.add(new CodeNode2("005001"));
		node.children.add(new CodeNode2("005001002"));
		node.children.add(new CodeNode2("005001002001"));
		node.children.add(new CodeNode2("005001002002"));
		node.children.add(new CodeNode2("005001002001001"));
		node.children.add(new CodeNode2("005001002002001"));
		node.children.add(new CodeNode2("003"));
		node.children.add(new CodeNode2("003001"));
		node.children.add(new CodeNode2("003002"));
		node.children.add(new CodeNode2("003002001"));
		node.children.add(new CodeNode2("003002002"));
		node.children.add(new CodeNode2("003002003"));
		node.children.add(new CodeNode2("003002004"));
		node.children.add(new CodeNode2("003002005"));
		node.children.add(new CodeNode2("003002006"));
		return node.children;
	}
}
