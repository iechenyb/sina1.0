package com.cyb.tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 作者 : iechenyb<br>
 * 类描述: 说点啥<br>
 * 创建时间: 2018年1月29日
 */
public class CodeTree {
	Log log = LogFactory.getLog(CodeTree.class);

	public static void main(String[] args) {
		CodeNode node = new CodeNode();//
		initTreeData(node);// 构建树前的初始化平铺数据，模拟数据库查询出的数据

		Map<String, CodeNode> rMap = new LinkedHashMap<String, CodeNode>();// 临时组织数据map

		for (CodeNode thisN : node.children) {
			turnToMap(rMap, thisN);// 将平铺的数据，解析到map中，构建一颗逻辑树
		}

		CodeNode root = new CodeNode();// 结果树
		turnToList(rMap, root);// 递归解析map树，并放入root这个根节点中
		System.out.println(root);
		// root既是结果树
	}

	// n为当前节点
	static void turnToMap(Map<String, CodeNode> rMap, CodeNode n) {
		String key = null;
		List<String> keyList = new ArrayList<String>();
		for (int i = 0; i < n.code.length() / 3; i++) {// 组装code的父级结构
			key = n.code.substring(0, 3 + (i * 3));
			keyList.add(key);
		}
		String thisKey = null;
		CodeNode tmpNode = null;
		Map<String, CodeNode> tmpMap = rMap;
		for (int i = 0; i < keyList.size(); i++) {
			thisKey = keyList.get(i);
			tmpNode = tmpMap.get(thisKey);
			if (i + 1 == keyList.size()) {
				tmpMap.put(n.code, n);// 如果是末级节点，则放入该节点
			} else {
				tmpMap = tmpNode.childMap;// 如果不是末级节点，则将该节点赋值给临时变量
			}
		}
	}

	static void turnToList(Map<String, CodeNode> rMap, CodeNode rn) {
		Set<Entry<String, CodeNode>> eSet = rMap.entrySet();
		Iterator<Entry<String, CodeNode>> mIt = eSet.iterator();
		while (mIt.hasNext()) {
			Entry<String, CodeNode> entry = mIt.next();
			CodeNode node = entry.getValue();
			rn.children.add(node);
			turnToList(node.childMap, node);
		}
	}

	static List<CodeNode> initTreeData(CodeNode node) {
		//node.children.add(new CodeNode("0010"));//错误数据测试
		node.children.add(new CodeNode("001"));
		node.children.add(new CodeNode("001001"));
		node.children.add(new CodeNode("001002"));
		node.children.add(new CodeNode("002"));
		node.children.add(new CodeNode("002001"));
		node.children.add(new CodeNode("002002"));
		node.children.add(new CodeNode("002002001"));
		node.children.add(new CodeNode("002002002"));
		node.children.add(new CodeNode("003"));
		node.children.add(new CodeNode("003001"));
		node.children.add(new CodeNode("003002"));
		node.children.add(new CodeNode("003002001"));
		node.children.add(new CodeNode("003002002"));
		node.children.add(new CodeNode("003002003"));
		node.children.add(new CodeNode("003002004"));
		node.children.add(new CodeNode("003002005"));
		node.children.add(new CodeNode("003002006"));
		node.children.add(new CodeNode("005"));
		node.children.add(new CodeNode("005001"));
		node.children.add(new CodeNode("005001002"));
		node.children.add(new CodeNode("005001002001"));
		node.children.add(new CodeNode("005001002002"));
		node.children.add(new CodeNode("003"));
		node.children.add(new CodeNode("003001"));
		node.children.add(new CodeNode("003002"));
		node.children.add(new CodeNode("003002001"));
		node.children.add(new CodeNode("003002002"));
		node.children.add(new CodeNode("003002003"));
		node.children.add(new CodeNode("003002004"));
		node.children.add(new CodeNode("003002005"));
		node.children.add(new CodeNode("003002006"));
		return node.children;
	}
}
