package com.cyb.tree;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.CollectionUtils;
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
public class Leafs2Tree {
	private static int rootId = 0;
	Log log = LogFactory.getLog(Leafs2Tree.class);
	private static List<Menu> list = new ArrayList<Menu>();
	private static Map<Integer,Menu> map = new HashMap<>();
	//找到叶子节点
    static List<Menu> paramLeafs = new ArrayList<>();
	public static void init(){
		    Menu a=new Menu(1,"a",rootId,false);
	        Menu b=new Menu(2,"b",rootId,false);
	        Menu c=new Menu(3,"c",rootId,false);
	        Menu d=new Menu(4,"d",1,true);
	        Menu e=new Menu(5,"e",2,false);
	        Menu f=new Menu(6,"f",2,true);
	        Menu g=new Menu(7,"g",3,true);
	        Menu h=new Menu(8,"h",6,true);
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
	        paramLeafs.add(g);
	        paramLeafs.add(h);
	        
	}
	public static Map<Integer,Object> getParents(int leafId){
		Menu root = new Menu();
		root.setParentId(-1);
		root.setId(rootId);
		root.setIsLeaf(false);
		root.setMenuName("系统菜单");
		Map<Integer, Object> rootM = new HashMap<Integer, Object>();
		rootM.put(root.getId(), root);
		if(rootId!=leafId){//除掉根节点
			Object obj =  map.get(leafId);
			if(obj!=null){
				Menu m = (Menu)obj;
				rootM.put(m.getId(), m);
				buildParents(rootM,m);
			}
		}
		return rootM;
	}
	
	public static void buildParents(Map<Integer,Object> ps ,Menu menu){
		if(rootId!=menu.getParentId()){//是否遍历到根节点
		    Object obj =  map.get(menu.getParentId());
			if(obj!=null){
				Menu m = (Menu)obj;	
				ps.put(m.getId(), m);
				buildParents(ps,m);
			}
		}
	}
	
	public static void main(String[] args) {
		init();
		Map<Integer,Object> mapTreeFull = new HashMap<Integer, Object>();
		if(!CollectionUtils.isEmpty(paramLeafs)){
			for(Menu rm:paramLeafs){
				mapTreeFull.putAll(getParents(rm.getId()));
			}
		}
		System.out.println(mapTreeFull);
	}
}
