package com.cyb.indiator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.Assert;

import com.cyb.data.DataUtils;
import com.cyb.date.DateUtil;

import net.sf.json.JSONArray;

//http://blog.csdn.net/zdy0_2004/article/details/46822685
public class 增量方差1 {
	public static int num=100;
	public static List<Bzc> syls = new ArrayList<Bzc>();//收益率记录
	public static void main(String[] args) {
		init();
		//传统方法计算方差
		old();
		//增量方式计算方差
		zl();
		System.out.println(JSONArray.fromObject(syls).toString());
		check();
	}
	public static void check(){
		for(int i=0;i<syls.size();i++){
			System.out.println("i:增量计算==传统计算  "+syls.get(i).getBzc()+"=="+syls.get(i).getCkbzc());
			Assert.isTrue(Math.abs(syls.get(i).getBzc()-syls.get(i).getCkbzc())<0.00000009);
		}
	}

	public static void old() {
		for(int i=0;i<syls.size();i++){
			if(i>0){//第一日标准差为0
				syls.get(i).setCkbzc(oldBZC(i));
			}
		}
	}
	
	public static double oldBZC(int index){
		double sum = 0;
		double curPjsyl = syls.get(index).getPjsyl();
		for(int i=0;i<=index;i++){
		  //求平方差的和  
			sum=sum+Math.pow(syls.get(i).getDrsyl()-curPjsyl, 2);
		}
		//求标准差
		double bzc = Math.sqrt(sum/(index+1));
		return Double.valueOf(DataUtils.e2StringBD(bzc, 8));
	}
    //计算第index+1天的标准差
	public static void zl() {
		for(int i=0;i<syls.size();i++){
			if(i>0){//第一日标准差为0
				syls.get(i).setBzc(zlBZC(i));
			}
		}
	}
	/*今日标准差=Sqrt【(【上日累计交易天数*(pow(上日标准差）+*pow(全量均值-历史收益率均值))】
	+1*【pow(当日标准差)+pow(全量收益率均值-今日收益率均值(一天的均值))】)/今日累计交易天数】
	*/
	public static double zlBZC(int index){
		Bzc preBzc = syls.get(index-1);//上日数据
		Bzc curBzc = syls.get(index);//今日数据
		double zlfc = (index * (Math.pow(preBzc.getBzc(), 2) + Math.pow(curBzc.getPjsyl() - preBzc.getPjsyl(), 2))
				+ 1 * (Math.pow(0, 2) + Math.pow(curBzc.getPjsyl() - curBzc.getDrsyl(), 2)))
				/ (index + 1);
		return Double.valueOf(DataUtils.e2StringBD(Math.sqrt(zlfc), 8));
	}
	
	
	public static void init(){
		try{
		syls = new ArrayList<Bzc>(num);
		int day = 1;
		for(int i=0;i<=num;i++){
			Bzc bzc = new Bzc();
			double drsyl = i+1;
			if(i==0){
				bzc.setDays(1);
				bzc.setLjsyl(1);
				bzc.setPjsyl(1);
			}else{
				//每次累计一天，因为连续
				bzc.setDays(syls.get(i-1).getDays()+day);
				bzc.setLjsyl(syls.get(i-1).getLjsyl()+drsyl);
				bzc.setPjsyl(bzc.getLjsyl()/bzc.getDays());
			}
			bzc.setDrsyl(drsyl);
			bzc.setOrder(i+1);
			syls.add(bzc);
		}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
