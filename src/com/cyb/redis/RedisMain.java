package com.cyb.redis;

import java.util.Collections;

import redis.clients.jedis.Jedis;

public class RedisMain {
	static RedisClient client = null;
	public static void main(String[] args) {
		//key string list sortedset set hash
       //new RedisClient().show("list"); 
		//new RedisClient().readData();
		RedisClient client = new RedisClient();
		Jedis jedis = client.getJedis();
		for(int i=0;i<100;i++){
			for(int j=0;j<10;j++){
				/*jedis.hset("Qutoes:"+i+":"+j, "name", "chenyb");
				jedis.hset("Qutoes:"+i+":"+j, "age", j+i+"");
				jedis.hset("Qutoes:"+i+":"+j, "tel", "139387"+i);*/
				jedis.hdel("Qutoes:"+i+":"+j, "name","chenyb");
				jedis.hdel("Qutoes:"+i+":"+j, "age", j+i+"");
				jedis.hdel("Qutoes:"+i+":"+j, "tel", "139387"+i);
			}
		}
		System.out.println("over!");
		jedis.disconnect();
    }
	
	
	 @SuppressWarnings("unused")
	private static final String LOCK_SUCCESS = "OK";
     @SuppressWarnings("unused")
	private static final String SET_IF_NOT_EXIST = "NX";
     @SuppressWarnings("unused")
	private static final String SET_WITH_EXPIRE_TIME = "PX";
     private static final Long RELEASE_SUCCESS = 1L;

	    /**
	     * 尝试获取分布式锁
	     * @param jedis      Redis客户端
	     * @param lockKey    锁
	     * @param requestId  请求标识
	     * @param expireTime 超期时间
	     * @return 是否获取成功
	     */
	    public static boolean tryGetDistributedLock(Jedis jedis, String lockKey, String requestId, int expireTime) {
	        
	    	/*
	    	 * String result = jedis.set(lockKey, requestId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);
	            if (LOCK_SUCCESS.equals(result)) {
	            return true;
	        }*/
	        return false;
	    }
	    
	    /**
	     * 释放分布式锁
	     * @param jedis     Redis客户端
	     * @param lockKey   锁
	     * @param requestId 请求标识
	     * @return 是否释放成功
	     */
	    public static boolean releaseDistributedLock(Jedis jedis, String lockKey, String requestId) {
	        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
	        Object result = jedis.eval(script, Collections.singletonList(lockKey), Collections.singletonList(requestId));
	        if (RELEASE_SUCCESS.equals(result)) {
	            return true;
	        }
	        return false;
	    }

}
