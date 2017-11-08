package com.app.lhug.logic;

import java.io.IOException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.Assert;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.app.lhug.data.DataGeneror;
import com.app.lhug.po.TB_A;
import com.app.lhug.utils.CalUtils;
import com.cyb.data.DataUtils;
import com.cyb.file.FileUtils;

/**
 * 作者 : iechenyb<br>
 * 类描述: 说点啥<br>
 * 创建时间: 2017年11月8日
 */
public class LhugCaculatorOther {
	Log log = LogFactory.getLog(LhugCaculatorOther.class);
	static List<TB_A> aList = null;//人员成绩记录
	public static void calByAccount(String account, int season, boolean check) throws IOException {
		int count = 240*season;
		// 计算第一天数据
		{
			TB_A a = aList.get(0);
			// 第一天参赛的成绩进行默认的初始化
			a.setLjdwjz(1);// 默认累计单位净值1
			a.setDrdwjz(1);// 默认当日单位净值1
			a.setDrdwfs(a.getDrqy());// 默认单位出金
			a.setDrljdwcj(0);// 默认单位出金
			a.setDrdwfs(a.getDrqy());
			a.setSrqy(0);// 待定，暂时复制为0
			a.setDrjyk(a.getDrqy() - a.getSrqy() - a.getDrjrj());// 计算当日净盈亏
			// 截至当日累计净入金
			a.setDrljjrj(0 + a.getDrzdjrj());// 计算截至当日累计净入金
			a.setZdljjrj(a.getDrljjrj() < 0 ? 0 : a.getDrljjrj());// 计算当日累计净入金
		}
		for (int i = 1; i < count; i++) {
			TB_A preA = aList.get(i - 1);
			// 获取当日的成绩基础数据
			TB_A curA = aList.get(i);
			curA = CalUtils.calLjdwjz(curA, preA);// 计算累计单位净值并将当日成绩重新赋值
			curA = null;
			preA = null;
		}
		 /*String result = JSON.toJSONString(aList);
		 FileUtils.overideString2File(result, "d://data//lhug//tmp//" + account + "-" + season + ".txt");
		*/
		if (check) {
			checkLjdwjz(aList);// 验证数据的正确性与基准数据
		}
		// aList.clear();
		// aList = null;
		// result = null;
	}

	// 跟基准单位净值进行比较
	public static void checkLjdwjz(List<TB_A> from) throws IOException {
		// 读取带单位净值的json,基准数据保留六位小数
		List<TB_A> baseList = JSONObject
				.parseArray(FileUtils.readContentFromFile(DataGeneror.standardTxtFilePathHashDwjz), TB_A.class);
		if (from.size() != baseList.size()) {
			Assert.isTrue(from.size() == baseList.size());
		} else {
			for (int i = 0; i < from.size(); i++) {
				// 先断定日期一样
				Assert.isTrue(from.get(i).getYwrq() == baseList.get(i).getYwrq());
				System.out.println("日期：" + from.get(i).getYwrq() + "," + from.get(i).getLjdwjz() + "=="
						+ baseList.get(i).getLjdwjz());
				double wucha = Double
						.valueOf(DataUtils.e2String(Math.abs(from.get(i).getLjdwjz() - baseList.get(i).getLjdwjz())));
				// System.out.println(wucha<=0.000001);
				Assert.isTrue(wucha <= 0.000009);// 误差小于10万分之一
			}
		}
	}

	public static void main(String[] args) throws IOException {
		int season = 10;
		String account = "123456";
		//定义成绩数据文件目录
		String filePath = "D:\\data\\lhug\\rolling\\dhqh\\competitor\\"+account+"\\"+season+"\\a.txt";
		//查询成绩数据list
		aList = JSONObject.parseArray(FileUtils.readContentFromFile(filePath), TB_A.class);
		for (int i = 1; i <= 10; i++) {
			long s = System.currentTimeMillis();
			calByAccount("123456", 10, false);
			long e = System.currentTimeMillis();
			System.out.println("第"+i+"次测试共耗时：" + (e - s) / 1000 + "." + (e - s) % 1000);
		}
	}
}
