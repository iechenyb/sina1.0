package com.app.stock;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cyb.UUIDUtils;
import com.cyb.file.FileUtils;
import com.cyb.file.ObjectFileUtils;
import com.cyb.h2.H2Manager;
import com.cyb.redis.RedisClient;
import com.cyb.redis.qutoes.RealQutoesUtils;
import com.cyb.redis.qutoes.StockCodeUtils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.ShardedJedis;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年7月24日
 */
public class DrawCodesUtils {
	public static String url="http://hq.sinajs.cn/list=";
	public static ShardedJedis shardedJedis;
	public static Jedis jedis ;
	static Log log = LogFactory.getLog(DrawCodesUtils.class);
	class TaskSH implements  Runnable {
		public void run() {
			try {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
					
				}
				initSZStockCodes();
			} catch (IOException e) {
				e.printStackTrace();
				
			}
		}
	}
	class TaskSZ implements  Runnable {
		public void run() {
			try {
				initSHStockCodes();
			} catch (IOException e) {
				e.printStackTrace();
				
			}
		}
	}
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {
		 H2Manager.start();
		 RedisClient client = new RedisClient();
		 jedis = client.getJedis();
		 shardedJedis  = client.getShardedJedis();
		 //initStockCodes();
		 //new Thread(new DrawCodesUtils().new TaskSH()).start();
		// new Thread(new DrawCodesUtils().new TaskSZ()).start();
		 //initStockCodes();
		 //showKeys("*");//*StockCodeUtils.CodePrix+"sh"
		 //System.out.println(jedis.keys("Qutoes:Code*"));
		 String shpath = FileUtils.getAbsolutePathAtClass(DrawCodesUtils.class)+"shstocks.data";
		 String szpath = FileUtils.getAbsolutePathAtClass(DrawCodesUtils.class)+"szstocks.data";
		 List<Stock> shStocks =  (List<Stock>) ObjectFileUtils.readObjectFromFile(shpath);
		 List<Stock> szStocks = (List<Stock>) ObjectFileUtils.readObjectFromFile(szpath);
		 System.out.println(shStocks.size()+szStocks.size());
		 for(int i=0;i<szStocks.size();i++){
			 System.out.println(i+","+szStocks.get(i).name+","+szStocks.get(i).getCode());
		 }
		 H2Manager.stop();
		 System.exit(0);
	}
	
	public static void initStockCodes() throws IOException{
		Stock stock = null;
		String[] lxs = new String[]{"sh","sz"};
		List<Stock> stocks= new ArrayList<Stock>(); 
		for(int lx=0;lx<2;lx++){
			for(int i=1;i<999999;i++){
				int len = String.valueOf(i).length();
				String code = getZeros(6-len)+i;
				String orginStr = FileUtils.getJsonFromNet(url+lxs[lx]+code, null,"gbk");//.replaceAll("\"", "").replaceAll(";", "");
				String left=orginStr.split("=")[0];
				String right = orginStr.split("=")[1];
				if(right.length()>4){
				  stock =new Stock();
			      stock.setId(UUIDUtils.getUUID());
			      stock.setCode(left.split("=")[0].substring(left.length()-6, left.length()));
			      stock.setName(right.split(",")[0].replace("\"", ""));
			      stock.setExchange(lxs[lx]);
			      System.out.println(left.split("=")[0].substring(left.length()-6, left.length())+","+right.split(",")[0].replace("\"", ""));
			      //StockCodeUtils.saveCodeToRedis(stock,jedis);
			      stocks.add(stock);
				}else{
					//System.out.println("code["+i+"]不可用！");
				}			
			}
			String path = FileUtils.getAbsolutePathAtClass(DrawCodesUtils.class)+lxs[lx]+"stocks.data";
			System.out.println("股票代码写入文件："+path);
			ObjectFileUtils.writeObjectToFile(stocks,path );
		}
		
	}
	public static void initSZStockCodes() throws IOException{
		Stock stock = null;
		String[] lxs = new String[]{"sz"};
		List<Stock> stocks= new ArrayList<Stock>(); 
		for(int lx=0;lx<1;lx++){
			for(int i=1;i<999999;i++){
				int len = String.valueOf(i).length();
				String code = getZeros(6-len)+i;
				String orginStr = FileUtils.getJsonFromNet(url+lxs[lx]+code, null,"gbk");//.replaceAll("\"", "").replaceAll(";", "");
				String left=orginStr.split("=")[0];
				String right = orginStr.split("=")[1];
				if(right.length()>4){
				  stock =new Stock();
			      stock.setId(UUIDUtils.getUUID());
			      stock.setCode(left.split("=")[0].substring(left.length()-6, left.length()));
			      stock.setName(right.split(",")[0].replace("\"", ""));
			      stock.setExchange(lxs[lx]);
			      System.out.println(left.split("=")[0].substring(left.length()-6, left.length())+","+right.split(",")[0].replace("\"", ""));
			      StockCodeUtils.saveCodeToRedis(stock,jedis);
			      stocks.add(stock);
				}else{
					System.out.println("code["+i+"]不可用！");
				}			
			}
		}
		String path = FileUtils.getAbsolutePathAtClass(DrawCodesUtils.class)+"szstocks.data";
		System.out.println("股票代码写入文件："+path);
		ObjectFileUtils.writeObjectToFile(stocks,path );
	}
	public static void initSHStockCodes() throws IOException{
		Stock stock = null;
		String[] lxs = new String[]{"sh"};
		List<Stock> stocks= new ArrayList<Stock>(); 
		for(int lx=0;lx<1;lx++){
			for(int i=1;i<999999;i++){
				int len = String.valueOf(i).length();
				String code = getZeros(6-len)+i;
				String orginStr = FileUtils.getJsonFromNet(url+lxs[lx]+code, null,"gbk");//.replaceAll("\"", "").replaceAll(";", "");
				String left=orginStr.split("=")[0];
				String right = orginStr.split("=")[1];
				if(right.length()>4){
				  stock =new Stock();
			      stock.setId(UUIDUtils.getUUID());
			      stock.setCode(left.split("=")[0].substring(left.length()-6, left.length()));
			      stock.setName(right.split(",")[0].replace("\"", ""));
			      stock.setExchange(lxs[lx]);
			      System.out.println(left.split("=")[0].substring(left.length()-6, left.length())+","+right.split(",")[0].replace("\"", ""));
			      StockCodeUtils.saveCodeToRedis(stock,jedis);
			      stocks.add(stock);
				}else{
					System.out.println("code["+i+"]不可用！");
				}			
			}
		}
		String path = FileUtils.getAbsolutePathAtClass(DrawCodesUtils.class)+"shstocks.data";
		System.out.println("股票代码写入文件："+path);
		ObjectFileUtils.writeObjectToFile(stocks,path );
	}
	public static  String getZeros(int len){
		StringBuffer str = new StringBuffer();
		for(int i=1;i<=len;i++){
			str.append("0");
		}
		return str.toString();
	}
	public static void showKeys(String keyRegex) throws IOException{
		long s = System.currentTimeMillis();
		Set<String> keys = jedis.keys(keyRegex); 
        Iterator<String> it=keys.iterator() ;
        int i=1;
        while(it.hasNext()){   
        	i++;
            String key = it.next();  
            saveRealQutoesToRedis(jedis.hget(key, "exchange")+jedis.hget(key, "code"));
        }
        long e = System.currentTimeMillis();
		log.info("处理记录数="+((e-s)/1000)+"秒"+(e-s)%1000+"毫秒");
        System.out.println("沪深市场共有股票："+i);
	}
	
	public static void saveRealQutoesToRedis(String code) throws IOException{
		String orginStr = FileUtils.getJsonFromNet(url+code, null,"gbk");//.replaceAll("\"", "").replaceAll(";", "");
		String right = orginStr.split("=")[1];
		if(right.length()>4){
			RealQutoesUtils.String2Object(code, right, jedis);
		}else{
			//System.out.println("请求行情数据失败！"+"#"+orginStr);
		}
	}
}
