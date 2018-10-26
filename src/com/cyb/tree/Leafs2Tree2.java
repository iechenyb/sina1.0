package com.cyb.tree;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
//https://blog.csdn.net/qq827245563/article/details/81505494?utm_source=blogxgwz5
public class Leafs2Tree2 {
	private static Integer rootId = 0;
	Log log = LogFactory.getLog(Leafs2Tree2.class);
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
	        
	      /*  paramFirstLevelLeafs.add(a);
	        paramFirstLevelLeafs.add(b);
	        paramFirstLevelLeafs.add(c);*/
	        
	        
	        paramLeafs.add(d);
	        paramLeafs.add(f);
	        /*paramLeafs.add(g);
	        paramLeafs.add(h);*/
	        
	}
	public static List<Map<String,Object>> getUserMenusTree(String userId) {
		Map<String,Object> root = new LinkedHashMap<String, Object>();
		root.put("id", -1);
		root.put("pid",  -1);
		root.put("text", "系统菜单");
		root.put("isleaf", false);
		List<Map<String,Object>> children = new ArrayList<Map<String,Object>>();
		List<Menu> firstRoots = paramFirstLevelLeafs;//查询第一级菜单 
		/*this.getSession().createQuery("select distinct m from Menu m,RoleMenu rm"
				+ " where m.parentId = 'menuroot' "
				+ " and m.id = rm.menuId"
				+ " and rm.roleId in (select roleId from UserRole where userId=?) order by m.ordor")
				.setString(0, userId)
				.setCacheable(true)
				.list();*/
		if(!CollectionUtils.isEmpty(firstRoots)){
			for(int i=0;i<firstRoots.size();i++){
				Map<String,Object> nodeTmp = new LinkedHashMap<String, Object>();
				nodeTmp.put("text",firstRoots.get(i).getMenuName());
				//nodeTmp.put("pid",firstRoots.get(i).getParentId());
				nodeTmp.put("isleaf",firstRoots.get(i).getIsLeaf());
				nodeTmp.put("id",firstRoots.get(i).getId());
				//nodeTmp.put("url",firstRoots.get(i).getUrl());
				nodeTmp.put("children", new ArrayList<Map<String,Object>>());
				children.add(nodeTmp);
			}
			buildUserMenuTree(children,userId);
		}
		if(children.size()>0){
			root.put("children",children);
		}
		List<Map<String,Object>> data = new ArrayList<Map<String,Object>>();
		data.add(root);
		return data;
	}
	@SuppressWarnings("unchecked")
	public static void buildUserMenuTree(List<Map<String,Object>> children,String userId){
		for(Map<String,Object> child:children){
			List<Menu> nodes = paramLeafs;//所有叶子节点
			if(!CollectionUtils.isEmpty(nodes)){//存在子节点
				List<Map<String,Object>> childs = (List<Map<String, Object>>)child.get("children");
				for(int j=0;j<nodes.size();j++){//获取所有的子节点
					Map<String,Object> nodeTmp = new LinkedHashMap<String, Object>();
					nodeTmp.put("text",nodes.get(j).getMenuName());
					//nodeTmp.put("pid",nodes.get(j).getParentId());
					nodeTmp.put("id",nodes.get(j).getId());
					//nodeTmp.put("url",nodes.get(j).getUrl());
					nodeTmp.put("isleaf",nodes.get(j).getIsLeaf());
					nodeTmp.put("children", new ArrayList<Map<String,Object>>());
					childs.add(nodeTmp);
				}
			    buildTree(childs);
			}else{
				if(Boolean.valueOf(child.get("isleaf").toString())){
					child.remove("children");
				}
			}
		}
	}
	
	static List<Menu> getParentMenus(String  menuId){
		List<Menu> data = new ArrayList<>();
		for(Menu m:list){
			if(m.getParentId()==Integer.valueOf(menuId)){
				data.add(m);
			}
		}
		return data;
	}
	@SuppressWarnings("unchecked")
	public static void buildTree(List<Map<String,Object>> children){
		for(Map<String,Object> child:children){
			List<Menu> nodes = getParentMenus(child.get("id").toString());
					//this.getSession().createQuery(" from Menu where parentId = '"+child.get("id").toString()+"' order by ordor").setCacheable(true).list();//查询一级菜单
			if(!CollectionUtils.isEmpty(nodes)){//存在子节点
				List<Map<String,Object>> childs = (List<Map<String, Object>>)child.get("children");
				for(int j=0;j<nodes.size();j++){//获取所有的子节点
					Map<String,Object> nodeTmp = new LinkedHashMap<String, Object>();
					nodeTmp.put("text",nodes.get(j).getMenuName());
					nodeTmp.put("pid",nodes.get(j).getParentId());
					nodeTmp.put("id",nodes.get(j).getId());
					nodeTmp.put("url",nodes.get(j).getUrl());
					nodeTmp.put("isleaf",nodes.get(j).getIsLeaf());
					nodeTmp.put("children", new ArrayList<Map<String,Object>>());
					childs.add(nodeTmp);
				}
			    buildTree(childs);
			}else{//不存在子节点
				if("1".equals(child.get("isleaf").toString())){
					child.remove("children");
				}/*else{
					child.put("children",new HashMap<String, String>());
				}*/
			}
		}
	}
	public static  Map<Integer,Object> getParents(Integer leafId){
		Menu root = new Menu();
		root.setParentId(rootId);
		root.setId(rootId);
		root.setIsLeaf(false);
		root.setMenuName("系统菜单");
		Map<Integer, Object> rootM = new HashMap<Integer, Object>();
		rootM.put(root.getId(), root);
		if(!"menuroot".equals(leafId)){//除掉根节点
			Object obj =  map.get(leafId);//this.getSession().get(Menu.class, leafId);
			if(obj!=null){
				Menu m = (Menu)obj;
				rootM.put(m.getId(), m);
				buildParents(rootM,m);
			}
		}
		return rootM;
	}
	
	public static void buildParents(Map<Integer,Object> ps ,Menu menu){
		if(!"menuroot".equals(menu.getParentId())){//是否遍历到根节点
		    Object obj =  map.get(menu.getParentId());//this.getSession().get(Menu.class, menu.getParentId());
			if(obj!=null){
				Menu m = (Menu)obj;	
				ps.put(m.getId(), m);
				buildParents(ps,m);
			}
		}
	}
	public static Map<Integer,Object> getUserMenus(String userId){
		List<Menu> urs = paramLeafs;//dao.getUserMenus(userId);
		Map<Integer,Object> mapTreeFull = new HashMap<Integer, Object>();
		if(!CollectionUtils.isEmpty(urs)){
			for(Menu rm:urs){
				mapTreeFull.putAll(getParents(rm.getId()));
			}
		}
		return mapTreeFull;
	}
	public static void main(String[] args) {
		init();
		//先获取一级目录 paramLeafs
		Map<Integer, Object> a = getUserMenus("");//getUserMenusTree("");
		System.out.println(a);
		System.out.println("-----------------------------------");
		System.out.println(getUserMenusTree(""));
	}
}
