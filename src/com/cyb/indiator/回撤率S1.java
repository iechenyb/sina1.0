package com.cyb.indiator;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
/*
 * 直接计算回撤率
 */
public class 回撤率S1 {
	public static Map<Integer, Double> ljsyl = new LinkedHashMap<Integer, Double>();
	public static List<Syl> syls = new ArrayList<Syl>(21);

	public static void main(String[] args) {
		init();// 初始化累计收益率数据
		cal();// 计算回撤率
	}

	public static void cal() {
		for (int i = 0; i < syls.size(); i++) {
			Syl syl = syls.get(i);
			if (syl.getDate() == 1) {// 计算第一天初始化
				syl.setSfhc(false);
				syl.setZdhcsyl(syl.getLjsyl());// 设置最大值
				syl.setZxhcsyl(syl.getLjsyl());// 设置最小值
			} else {
				Syl preSyl = syls.get(i - 1);
				if (syl.getLjsyl() >= preSyl.getZdhcsyl()) {// 斜率大于0
					if (preSyl.isSfhc()) {// 上日在回撤中，今日超过最大回撤点，结束回撤
						// 计算回撤率
						syl.setSfhc(false);// 结束回撤
						syl.setZdhcsyl(syl.getLjsyl());// 设置当日为最高点
						syl.setZxhcsyl(syl.getLjsyl());// 设置最小值
						syl.setZdhcl(preSyl.getZdhcl());//取上日最大回撤
					} else {//今日继续走高，依然不在回撤中
						syl.setSfhc(false);
						syl.setZdhcsyl(syl.getLjsyl());// 设置当日为最高点
						syl.setZxhcsyl(syl.getLjsyl());// 设置最小值
//						syl.setZdhcl((preSyl.getZdhcl() - preSyl.getZxhcsyl()) / (1 + preSyl.getZdhcl()));
//						System.out.println(syl.getDate()+"日回撤率：" + syl.getZdhcl());
						syl.setZdhcl(preSyl.getZdhcl());//取上日最大回撤
					}
				} else {// 斜率小于0，回撤中
					syl.setSfhc(true);
					// 设置上日最高点为当日最高点
					syl.setZdhcsyl(preSyl.getZdhcsyl());
					//回撤中，当日小于最高点回撤且小于上日回撤，则当日为回撤最小值
					if (syl.getLjsyl() < preSyl.getZxhcsyl() && syl.getLjsyl() < preSyl.getLjsyl()) {
						syl.setZxhcsyl(syl.getLjsyl());// 设置最小收益率
					} else {
						syl.setZxhcsyl(preSyl.getZxhcsyl());// 设置最小收益率
					}
					
					double curZdhcl = (syl.getZdhcsyl() - syl.getZxhcsyl()) / (1 + syl.getZdhcsyl());
					if(curZdhcl>preSyl.getZdhcl()){
						syl.setZdhcl(curZdhcl);
					}else{
						syl.setZdhcl(preSyl.getZdhcl());
					}
					System.out.println(syl.getDate()+"日回撤率：" + syl.getZdhcl());
				}
			}
		}
		System.out.println(JSONArray.fromObject(syls).toString());
	}

	public static void init() {
		syls = new ArrayList<Syl>(21);
		ljsyl = new LinkedHashMap<Integer, Double>();
		ljsyl.put(1, 0.1d);
		ljsyl.put(2, 1.9d);
		ljsyl.put(3, 2.2d);
		ljsyl.put(4, 3.9d);// 回调起始点1
		ljsyl.put(5, 0.2d);// 回调结束点1
		ljsyl.put(6, 9d);
		ljsyl.put(7, 12.9d);// 回调开始点2
		ljsyl.put(8, 10.6d);
		ljsyl.put(9, 10d);
		ljsyl.put(10, 6.5d);
		ljsyl.put(11, 7.8d);
		ljsyl.put(12, 8d);
		ljsyl.put(13, 9.6d);
		ljsyl.put(14, 9.9d);
		ljsyl.put(15, 8d);
		ljsyl.put(16, 5d);// 回调结束点2
		ljsyl.put(17, 7d);
		ljsyl.put(18, 12.3d);
		ljsyl.put(19, 14d);
		ljsyl.put(20, 15d);
		ljsyl.put(21, 16d);
		ljsyl.put(22, 14d);
		// Iterator<Integer> it =ljsyl.keySet().iterator();
		Syl syl = null;
		/*
		 * while(it.hasNext()){ syl = new Syl(); int date = it.next();
		 * syl.setDate(date); syl.setLjsyl(ljsyl.get(date)); syls.add(syl); }
		 */

		try {
			for (int i = 1; i <= 22; i++) {
				syl = new Syl();
				syl.setDate(i);
				syl.setLjsyl(ljsyl.get(i));
				//System.out.println(i + "," + ljsyl.get(i));
				syls.add(syl);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
