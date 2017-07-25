package com.app.stock;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cyb.UUIDUtils;
import com.cyb.file.FileUtils;
import com.cyb.file.ObjectFileUtils;
import com.cyb.h2.H2Manager;
import com.cyb.page.Pagination;
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
	public static void main(String[] args) throws Exception {
		 H2Manager.start();
		 RedisClient client = new RedisClient();
		 jedis = client.getJedis();
		 shardedJedis  = client.getShardedJedis();
		 //initSZStockCodes();
		 //initSHStockCodes();
		 //log.info(getQutoes("sh600868"));
		 initStockCodesBatch();
		 H2Manager.stop();
		 System.exit(0);
	}
	public void getQutoesByPage() throws IOException{
		@SuppressWarnings("unchecked")
		List<Stock> ss = (List<Stock>) ObjectFileUtils.readObjectFromFile("d:/data/shstocks.data");
		int pageSize = 850;
		int totalcount = ss.size();
		StringBuffer sb = new StringBuffer("");
		Pagination p = new Pagination(1, pageSize, totalcount);
		for (int i = 1; i < p.getPageCount(); i++) {
			Pagination p_ = new Pagination(i, pageSize, totalcount);
			for (int j = p_.getOffset(); j <= (p_.getPageSize() * i - 1) - 1; j++) {
				sb.append(ss.get(j).getExchange() + ss.get(j).getCode() + ",");
			}
			List<RealQutoes> rqs = getRealQutoesBatch(sb.toString());
			sb.delete(0, sb.length());
   	    }
	}
	
	//code=sh1,sz2,sh2,
	public static List<RealQutoes> getRealQutoesBatch(String code) throws IOException{
		String orginStr = FileUtils.getJsonFromNet(url+code, null,"gbk");//.replaceAll("\"", "").replaceAll(";", "");
		String[] strs = orginStr.replaceAll("\n", "#").split("#");
		List<RealQutoes> lst = new ArrayList<RealQutoes>();
		for(String str:strs){
			RealQutoes rq = getQutoesByString(str);
			if(rq!=null){
				lst.add(rq);
			}
		}
		return lst;
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
			      log.info(left.split("=")[0].substring(left.length()-6, left.length())+","+right.split(",")[0].replace("\"", ""));
			      //StockCodeUtils.saveCodeToRedis(stock,jedis);
			      stocks.add(stock);
				}else{
					//log.info("code["+i+"]不可用！");
				}			
			}
			String path = FileUtils.getAbsolutePathAtClass(DrawCodesUtils.class)+lxs[lx]+"stocks.data";
			log.info("股票代码写入文件："+path);
			ObjectFileUtils.writeObjectToFile(stocks,path );
		}
		
	}
	public static int pageSize=850;
	public static void initStockCodesBatch() throws IOException{
		String[] lxs = new String[]{"sh","sz"};
		List<Stock> stocks= new ArrayList<Stock>(); 
		StringBuffer sb = new StringBuffer();
		Pagination page = new Pagination(1, pageSize, 999999);
		int pageCount = page.getPageCount();
		for(int lx=0;lx<2;lx++){
			for(int i=1;i<pageCount;i++){//遍历页数
				Pagination p_ = new Pagination(i, pageSize, 999999);
				for (int j = p_.getOffset(); j <= (p_.getPageSize() * i - 1) - 1; j++){ //遍历页面数据
					int len = String.valueOf(j).length();
					String code = lxs[lx]+getZeros(6-len)+j;
					sb.append(code+",");
				}
				log.info("交易所："+lxs[lx]+",总页数:"+p_.getPageCount()+",当前页："+i);
				stocks.addAll(getStockCodeBatch(sb.toString(),lxs[lx]));
				sb.delete(0, sb.length());
			}
			String path = FileUtils.getAbsolutePathAtClass(DrawCodesUtils.class)+lxs[lx]+"stocks.data";
			log.info("股票代码写入文件："+path);
			ObjectFileUtils.writeObjectToFile(stocks,path );
		}
	}
	public static RealQutoes getQutoesByString(String orginStr) throws IOException{
		String left=orginStr.split("=")[0]; 
		String right = orginStr.split("=")[1];
		String code = left.split("=")[0].substring(left.length()-8, left.length());
		 if(right.length()>4){
			 RealQutoes qutoes = RealQutoesUtils.String2Qutoes(right, code);
		     return qutoes;
		 }else{
			 return null;
		 }
	}
	public static Stock getStockCode(String orginStr,String exchange) throws IOException{
		String left=orginStr.split("=")[0]; 
		String right = orginStr.split("=")[1];
		String code = left.split("=")[0].substring(left.length()-8, left.length());
		Stock stock = null;
		 if(right.length()>4){
			  stock =new Stock();
		      stock.setId(UUIDUtils.getUUID());
		      stock.setCode(left.split("=")[0].substring(left.length()-6, left.length()));
		      stock.setName(right.split(",")[0].replace("\"", ""));
		      stock.setCode_(code);
		      stock.setExchange(exchange);
		      return stock;
		 }else{
			 return null;
		 }
	}
	public static List<Stock> getStockCodeBatch(String codes,String exchange) throws IOException{
		String orginStr = FileUtils.getJsonFromNet(url+codes, null,"gbk");//.replaceAll("\"", "").replaceAll(";", "");
		List<Stock> data = new ArrayList<Stock>();
		String[] strs = orginStr.replaceAll("\n", "#").split("#");
		for(String str:strs){
			Stock stock = getStockCode(str,exchange);
			if(stock!=null){
				data.add(stock);
			}
		}
		return data;
	}
	public static RealQutoes getQutoes(String stockcode) throws IOException{
		if(StringUtils.isNotEmpty(getOriginQutoes(stockcode))){
			 String orginStr = FileUtils.getJsonFromNet(url+stockcode, null,"gbk");//.replaceAll("\"", "").replaceAll(";", "");
			 String right = orginStr.split("=")[1];
			 if(right.length()>4){
				 RealQutoes qutoes = RealQutoesUtils.String2Qutoes(right, stockcode);
			     return qutoes;
			 }else{
				 return null;
			 }
		}
		return null;
	}
	public static String getOriginQutoes(String stockcode) throws IOException{
		String orginStr = FileUtils.getJsonFromNet(url + stockcode, null, "gbk");// .replaceAll("\"",
		String right = orginStr.split("=")[1];
		if (right.length() > 4) {
			return right;
		}else{
			return "";
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
			      log.info(left.split("=")[0].substring(left.length()-6, left.length())+","+right.split(",")[0].replace("\"", ""));
			      //StockCodeUtils.saveCodeToRedis(stock,jedis);
			      stocks.add(stock);
				}else{
					//log.info("code["+i+"]不可用！");
				}			
			}
		}
		String path = FileUtils.getAbsolutePathAtClass(DrawCodesUtils.class)+"szstocks.data";
		log.info("股票代码写入文件："+path);
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
			      log.info(left.split("=")[0].substring(left.length()-6, left.length())+","+right.split(",")[0].replace("\"", ""));
			      //StockCodeUtils.saveCodeToRedis(stock,jedis);
			      stocks.add(stock);
				}else{
					//log.info("code["+i+"]不可用！");
				}			
			}
		}
		String path = FileUtils.getAbsolutePathAtClass(DrawCodesUtils.class)+"shstocks.data";
		log.info("股票代码写入文件："+path);
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
        log.info("沪深市场共有股票："+i);
	}
	
	public static void saveRealQutoesToRedis(String code) throws IOException{
		String orginStr = FileUtils.getJsonFromNet(url+code, null,"gbk");//.replaceAll("\"", "").replaceAll(";", "");
		String right = orginStr.split("=")[1];
		if(right.length()>4){
			RealQutoesUtils.String2Object(code, right, jedis);
		}else{
			//log.info("请求行情数据失败！"+"#"+orginStr);
		}
	}
}
