package com.cyb.tree;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2018年10月19日
 *     root
 *   /   |   \
 *   a   b    c
 *   |   /\   /
 *   d   e f  g
 *       |
 *       h
 */
//https://blog.csdn.net/qq827245563/article/details/81505494?utm_source=blogxgwz5
public class Leafs2Tree4 {
	private static Integer rootId = 0;
	Log log = LogFactory.getLog(Leafs2Tree4.class);
	private static List<Menu> list = new ArrayList<Menu>();
	private static Map<Integer,Menu> map = new HashMap<>();
	//找到叶子节点
    static List<Menu> paramLeafs = new ArrayList<>();
    static List<Menu> paramFirstLevelLeafs = new ArrayList<>();
	public static void init(){
		    Menu a=new Menu(1,"a",rootId,false);
	        Menu b=new Menu(2,"b",rootId,false);
	        Menu c=new Menu(3,"c",rootId,false);
	        Menu d=new Menu(4,"d",1,true);
	        Menu e=new Menu(5,"e",2,false);
	        Menu f=new Menu(6,"f",2,true);
	        Menu g=new Menu(7,"g",3,true);
	        Menu h=new Menu(8,"h",5,true);
	        //跟顺序没有关系
	        list.add(a);
	        list.add(b);
	        list.add(c);
	        list.add(d);
	        list.add(e);
	        list.add(f);
	        list.add(g);
	        list.add(h);
	        map.put(1, a);
	        map.put(2, b);
	        map.put(3, c);
	        map.put(4, d);
	        map.put(5, e);
	        map.put(6, f);
	        map.put(7, g);
	        map.put(8, h);
	        
	        paramLeafs.add(d);
	        paramLeafs.add(f);
	        /*paramLeafs.add(g);*/
	        paramLeafs.add(h);
	        
	}
	
	public static void main(String[] args) {
		init();//初始化菜单节点
		//生成完整的树，删除目录。
		/**
		 * 组装目录树，每个节点都进行遍历。
		 */
		showTreeLeaf();
	}
	
	//显示所有叶子的分支，需要合并算法 加上层级显示空目录
	public static void showTreeLeaf(){
		for(Menu m:paramLeafs){//遍历叶子节点
			Menu p = map.get(m.getParentId());
			if(!p.getChild().contains(m)){
				p.getChild().add(m);
			}
			System.out.println(JSONObject.toJSON(bulidParent(p)));
		}
	}
	public static Menu bulidParent(Menu m){
		if(m.getParentId()!=rootId){
			Menu p = map.get(m.getParentId());
			p.getChild().add(m);
			return bulidParent(p);
		}else{
			return m;
		}
	}
	public static void bulidParent(List<Menu> ms){//不一定是同一层级节点
		Iterator<Menu> iter = ms.iterator();
		List<Menu> msd = new ArrayList<Menu>();
		while(iter.hasNext()){
			Menu m = iter.next();
			if(m.getParentId()!=rootId){
				Menu p = map.get(m.getParentId());
				p.getChild().add(m);
				msd.add(p);
				//iter.remove();
			}
		}
		bulidParent(msd);
	}
	public static void leaf2RootTree(){
		List<Menu> ms = new ArrayList<Menu>();
		System.out.println("叶子节点："+paramLeafs);
		for(Menu m:paramLeafs){//遍历叶子节点的父节点
			Menu p = map.get(m.getParentId());
			//查看是否存在相同父节点的其他兄弟节点
			Iterator<Menu> iter = list.iterator();
			while(iter.hasNext()){
				Menu bother = iter.next();
				if(bother.getParentId()==p.getId()){
					p.getChild().add(bother);
				}
			}
			/*for(Menu bother:list){
				if(bother.getParentId()==p.getId()){
					p.getChild().add(bother);
				}
			}*/
			ms.add(p);
	    }
		System.out.println("叶子的上层节点："+ms);
		bulidParent(ms);
		System.out.println(JSONArray.toJSON(ms));
		//生成一棵完整的树，根据叶子节点剪枝！
	}
}
//http://ysj5125094.iteye.com/blog/2283159 list生成树
