package com.cyb.mongodb;

import java.util.Set;

import com.mongodb.DB;
import com.mongodb.Mongo;
/**
 * http://www.cnblogs.com/zengen/archive/2011/04/23/2025722.html
 * @author DHUser
 *
 */
@SuppressWarnings("deprecation")
public class Test {
	static DB db = null;
	static {
		 Mongo m = new Mongo("localhost",27017); 
		 db = m.getDB("admin");
		//db.dropDatabase();删除数据库
	}
  public static void main(String[] args) {
	  showCollections();
	  addUser(0);
	  
  }
  /**
   * 
   *作者 : iechenyb<br>
   *方法描述: 显示当前数据库的集合数<br>
   *创建时间: 2017年7月15日
   */
  public static void showCollections(){
	  //是否存在某个集合
	  System.out.println("存在user集合吗？"+db.collectionExists("user"));
	  db.createCollection("anyNae", null);
	  Set<String> colls = db.getCollectionNames(); 
	  for (String s : colls) { 
	       System.out.println("集合名称："+s); 
	  } 
  }
  //新增一个用户
  public static void addUser(int i){
	  db.addUser("cyb2", "cyb".toCharArray());
  }
  public static void insertOneDefault(){
	  
  }
}
