package com.cyb.memecache;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import com.cyb.collection.CollectionFactory;
import com.cyb.collection.User;
import com.cyb.socket.one2one.Message;
import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;
//管理员进入   memcached -d start  http://blog.csdn.net/cn_yaojin/article/details/51943794
public class MemcachedUtil {
	protected static MemCachedClient mcc = new MemCachedClient();

	protected static MemcachedUtil memCached = new MemcachedUtil();

	static {
		String[] servers = { "127.0.0.1:11211" };
		Integer[] weights = { 3 };

		SockIOPool pool = SockIOPool.getInstance();

		pool.setServers(servers);
		pool.setWeights(weights);

		pool.setInitConn(5);
		pool.setMinConn(5);
		pool.setMaxConn(250);
		pool.setMaxIdle(1000 * 60 * 60 * 6);

		pool.setMaintSleep(30);

		pool.setNagle(false);
		pool.setSocketTO(3000);
		pool.setSocketConnectTO(0);

		pool.initialize();

		mcc.setCompressEnable(true);
		mcc.setCompressThreshold(64 * 1024);
	}

	
	protected MemcachedUtil() {

	}

	
	public static MemcachedUtil getInstance() {
		return memCached;
	}

	
	public boolean add(String key, Object value) {
		return mcc.add(key, value);
	}

	public boolean add(String key, Object value, Date expiry) {
		return mcc.add(key, value, expiry);
	}

	public boolean replace(String key, Object value) {
		return mcc.replace(key, value);
	}

	public boolean replace(String key, Object value, Date expiry) {
		return mcc.replace(key, value, expiry);
	}
    public boolean delete(String key){
    	return mcc.delete(key);
    }
	
	public Object get(String key) {
		return mcc.get(key);
	}

	public static void main(String[] args) {
		System.out.println("****");
		MemcachedUtil cache = MemcachedUtil.getInstance();
		cache.add("hello", "memcached Test");//
		cache.delete("hello");
		mcc.set("hello", "new values");
		cache.add("hello", "memcached Test2");//
		cache.add("hello1", "memcached Test1");//
		System.out.println("get value : " + cache.get("hello"));//ֵ
		System.out.println("get value1 : " + cache.get("hello1"));//ֵ
		System.out.println("****");
		cache.add("user", CollectionFactory.getUser());
		cache.add("map", CollectionFactory.getMap());
		cache.add("list", CollectionFactory.getList());
		User user = (User) cache.get("user");
		Map map = (Map) cache.get("map");
		System.out.println(" test Object :"+user.getName());
		System.out.println(" test Map :"+map.get("name"));
	}


}
