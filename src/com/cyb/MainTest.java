package com.cyb;

import java.util.ArrayList;
import java.util.List;
//http://blog.csdn.net/zdy0_2004/article/details/46822685
public class MainTest {
	public static void main(String[] args) {	
		List<Double> his = new ArrayList<Double>();
		his.add(1d);his.add(2d);his.add(3d);his.add(4d);his.add(5d);	
		System.out.println(his.toString()+",历史平均数:"+avg(his)+"，方差："+fc(his));
		List<Double> today = new ArrayList<Double>();
		today.add(1d);
		//today.add(2d);
		System.out.println(today.toString()+",历史平均数:"+avg(today)+"，方差："+fc(today));
		List<Double> all = new ArrayList<Double>();
		all.addAll(his);
		all.addAll(today);
		System.out.println(all.toString()+",历史平均数:"+avg(all)+"，方差："+fc(all));
		addfc(his,today);
		
	}
	public static void addfc(List<Double> his,List<Double> today){
		double zavg = (his.size()*avg(his)+today.size()*avg(today))/(his.size()+today.size());
		double zlfc = (his.size()*(Math.pow(fc(his),2)+Math.pow(zavg-avg(his),2))
				+today.size()*(Math.pow(fc(today),2)+Math.pow(zavg-avg(today),2)))/(his.size()+today.size());
		his.addAll(today);
		System.out.println(his.toString()+",历史平均数："+zavg+"，增量方差："+Math.sqrt(zlfc));
	}
	public static double fc(List<Double> his){				
		double sumHis = 0d;
		for(int i=0;i<his.size();i++){
			sumHis = his.get(i)+sumHis;
		}
		double avg = sumHis/his.size();
		double sumfc = 0;
        for(int i = 0;i<his.size();i++){
            sumfc += Math.pow((his.get(i) - avg),2);
        }
        double  fc = sumfc/his.size();
		return Math.sqrt(fc);
	}
	
	public static double avg(List<Double> his){				
		double sumHis = 0d;
		for(int i=0;i<his.size();i++){
			sumHis = his.get(i)+sumHis;
		}
		double avg = sumHis/his.size();
		return avg;
	}
	
}
