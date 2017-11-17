package com.cyb.indiator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class 回撤率S0 {
public static Map<Integer,Double> ljsyl = new LinkedHashMap<Integer,Double>();
public static Map<String,String> rs = new LinkedHashMap<String,String>();
public static List<Syl> syls = new ArrayList<Syl>();
public static void main(String[] args) {
	init();
	for(int i=0;i<syls.size();i++){
		if(start!=0){ 
			start = findSection(start,i);
		}else{
			start = findSection(0,i);
		}
		
	}
}
static double min=0.0;static boolean sfht=false;static int start=0;
public static int findSection(int start,int end){
	double max=0.0;Integer maxD=0;Integer minD=0;
	int i=0;
	try {
		for(int tmpD=start;tmpD<=end;tmpD++){
			double tmp = syls.get(tmpD).getLjsyl();
			if(sfht&&tmp>=max) {//出现回调，且超过第一个记录高点
				System.out.println("回撤结束：min="+(minD));
				sfht=false;
				break;
			}
			if(!sfht&&tmp>=max) {
				max = tmp;//记录最高点 
				maxD=tmpD;min=tmp;minD=tmpD;
			}
			if(tmp<max) {//开始回调
				if(i==0){min=tmp;minD=tmpD;}//记录回撤时最小值
				if(tmp<min){min=tmp;minD=tmpD;}
				//记录回撤更小值
				sfht=true;
				i++;
			}
		}
		
		System.out.print("start="+(start+1)+",end="+(end+1)+":");
		if(!sfht) {
			if(rs.containsKey("huiche")){
				System.out.println("第"+(end+1)+"天,"+"区间未出现回调点！使用新上次计算信息："+rs.get("ms"));
			}else{
				System.out.println("第"+(end+1)+"天,"+"区间未出现回调点！");
			}
		}else{
			if(!rs.containsKey("huiche")){
				rs.put("huiche", max-min+"");
				rs.put("ms", maxD+"="+max+","+minD+"="+min+",回撤幅度="+(max-min)+",回撤率="+(max-min)/(1+max));
			}else{
				if(Double.valueOf(rs.get("huiche")).doubleValue()<(max-min)){
					rs.put("huiche", max-min+"");
					rs.put("ms", maxD+"="+max+","+minD+"="+min+",回撤幅度="+(max-min)+",回撤率="+(max-min)/(1+max));
				}
			}
			System.out.println(sfht+",第"+(end+1)+"天,"+maxD+"="+max+","+minD+"="+min+",回撤幅度="+(max-min)+",回撤率="+(max-min)/(1+max));
		}
	} catch (Exception e) {
		System.out.println("start="+start);
		e.printStackTrace();
	}	
	return minD;
}
//http://echarts.baidu.com/echarts2/doc/example/line1.html
//[1, 2, 3, 4, 5, 6, 7,8,9,10,11,12,13,14,15,16,17,18,19,20,21]
//[0.1,1.9,2.2,3.9,0.2,9,12.9,10.6,10,6.5,7.8,8,9.6,9.9,8,5,7,12.3,14,15,16]
public static void init(){
	ljsyl.put(1, 0.1d);
	ljsyl.put(2,1.9d);
	ljsyl.put(3, 2.2d);
	ljsyl.put(4,3.9d);//回调起始点1
	ljsyl.put(5, 0.2d);//回调结束点1
	ljsyl.put(6,9d);
	ljsyl.put(7, 12.9d);//回调开始点2
	ljsyl.put(8,10.6d);
	ljsyl.put(9, 10d);
	ljsyl.put(10,6.5d);	
	ljsyl.put(11,7.8d);
	ljsyl.put(12, 8d);
	ljsyl.put(13,9.6d);
	ljsyl.put(14, 9.9d);
	ljsyl.put(15,8d);
	ljsyl.put(16,5d);//回调结束点2
	ljsyl.put(17, 7d);
	ljsyl.put(18,12.3d);
	ljsyl.put(19, 14d);
	ljsyl.put(20,15d);
	ljsyl.put(21,16d);
	Iterator<Integer> it =ljsyl.keySet().iterator();
    Syl syl = null;
    while(it.hasNext()){
    	syl = new Syl();
    	int date = it.next();
    	syl.setDate(date);
    	syl.setLjsyl(ljsyl.get(date));
    	syls.add(syl);
	}
}
public static String findMin(){
	Iterator<Integer> it =ljsyl.keySet().iterator();
	double min=0.0; 
	int i=0;
	int index = 0;
	while(it.hasNext()){
		 Integer tmpD = it.next();
    	 double tmp = ljsyl.get(tmpD);
		 if(i==0){
			 min = tmp;
		}else{
			 if(tmp<min){
				 min=tmp;
				 index = tmpD;
			 }
		 }
		 i++;
	 }
	return index+"="+min;
}
}
