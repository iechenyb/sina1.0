package com.cyb.collection.set;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import com.cyb.collection.po.User;
import com.cyb.collection.po.User_;


public class SetStudy {
  public static void main(String[] args) {
	  Set<User_> stus = new TreeSet<User_>();
	  stus.add(new User_("f"));
	  stus.add(new User_("b"));
	  stus.add(new User_("d"));
	  stus.add(new User_("e"));
	  stus.add(new User_("a"));
	  stus.add(new User_("a"));
	  System.out.println(stus.contains(new User_("f")));
	  System.out.println(stus);//报错
	  Set<User> stus1 = new TreeSet<User>();
	  stus1.add(new User(5,"cyb","1"));
	  stus1.add(new User(3,"iechenyb","2"));
	  stus1.add(new User(1,"chenyb","111"));
	  stus1.add(new User(2,"chenyb1","111"));
	  System.out.println(stus1);//
	  /*Iterator<User> it=  stus1.iterator();
	  while(it.hasNext()){
		  System.out.println(it.next());
	  }*/
	  TreeSet<String> ts = new TreeSet<String>();
	  ts.add("3");
	  ts.add("4");
	  ts.add("1");
	  ts.add("2");
	  ts.add("4");
	  System.out.println("treeset 有序："+ts);//自动去重（覆盖）
	  HashSet<String> hs = new HashSet<String>();//基于hashmap实现
	  
	  
	  hs.add("1");
	  hs.add("2");
	  hs.add("3");
	  hs.add("4");
	  hs.add("5");
	  hs.add("6");
	  hs.add("7");
	  hs.add("8");
	  hs.add("8");//默认会去重
	  System.out.println("hashset 无序："+hs);	
	  Object[] us = (Object[]) stus1.toArray();
	  for(int i=0;i<us.length;i++){
		  User user = (User) us[i];
		  System.out.println(user.getName());
	  }
}
}
