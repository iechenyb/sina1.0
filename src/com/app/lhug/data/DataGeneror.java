package com.app.lhug.data;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSON;
import com.app.lhug.po.TB_A;
import com.app.lhug.utils.CSVFileUtil;
import com.cyb.date.DateUtil;
import com.cyb.file.FileUtils;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年11月8日
 */
public class DataGeneror {
	Log log = LogFactory.getLog(DataGeneror.class);
	static int yearDays = 240;
	public static int start = 888666;
	public static int personNum = 5000;
	static String standardCsvFilePath = "d:/data/lhug/sjjy.csv";
	public static String standardTxtFilePath = "D:\\data\\lhug\\standard\\123456-3.txt";
	public static String standardTxtFilePathHashDwjz = "D:\\data\\lhug\\standard\\123456-3-dwjz.txt";
	public static void main(String[] args) throws Exception {
		
		genBatchDataTest();
		//genStandard();//生成json不带单位净值
		//genStandardHashDwjz();//生成json带单位净值
	}
	//批量生成5000人的数据
	public static void genBatchDataTest(){
		for(int i=start;i<start+personNum;i++){
			String account = String.valueOf(i);
			genAData(account,2);
			genAData(account,3);
			genAData(account,5);
			genAData(account,10);
		}
	}
	/**
	 * 
	 *作者 : iechenyb<br>
	 *方法描述: 生成基准数据，保存到txt中，
	 *用于校验算法正确性<br>
	 *创建时间: 2017年7月15日
	 */
	@SuppressWarnings("unchecked")
	public static void genStandard(){
		List<String> aList = CSVFileUtil.show(standardCsvFilePath);
		List<TB_A> newList = new ArrayList<TB_A>();
		int total = aList.size()-1;
		int i=0;
		try{
			for(i=total;i>0;i--){//去掉头
				TB_A a = new TB_A();
				ArrayList<String> ls = CSVFileUtil.fromCSVLinetoArray(aList.get(i));
				/*for(int j=0;j<ls.size();j++){
					System.out.print(ls.get(j)+"#");
				}*/
				//不对累计单位净值进行初始化
				a.setAccount(ls.get(0));
				if(!ls.get(5).equals("")){
					a.setDrjrj(Double.valueOf(ls.get(5)));
				}
				if(!ls.get(6).equals("")){
					a.setDrzdjrj(Double.valueOf(ls.get(6)));
				}
				if(!ls.get(3).equals("")){
					a.setDrqy(Double.valueOf(ls.get(3)));
				}
				a.setYwrq(Long.valueOf(ls.get(1)));
				newList.add(a);
			}
		}catch(Exception e){
			System.out.println(i);
			e.printStackTrace();
		}
		String res =  JSON.toJSONString(newList);
		FileUtils.overideString2File(res, standardTxtFilePath);
	}
	
	/**
	 * 
	 *作者 : iechenyb<br>
	 *方法描述: 生成基准数据，保存到txt中，
	 *用于校验算法正确性<br>
	 *创建时间: 2017年7月15日
	 */
	@SuppressWarnings("unchecked")
	public static void genStandardHashDwjz(){
		List<String> aList = CSVFileUtil.show(standardCsvFilePath);
		List<TB_A> newList = new ArrayList<TB_A>();
		int total = aList.size()-1;
		int i=0;
		try{
			for(i=total;i>0;i--){//去掉头
				TB_A a = new TB_A();
				ArrayList<String> ls = CSVFileUtil.fromCSVLinetoArray(aList.get(i));
				/*for(int j=0;j<ls.size();j++){
					System.out.print(ls.get(j)+"#");
				}*/
				//不对累计单位净值进行初始化
				a.setAccount(ls.get(0));
				if(!ls.get(5).equals("")){
					a.setDrjrj(Double.valueOf(ls.get(5)));
				}
				if(!ls.get(6).equals("")){
					a.setDrzdjrj(Double.valueOf(ls.get(6)));
				}
				if(!ls.get(3).equals("")){
					a.setDrqy(Double.valueOf(ls.get(3)));
				}
				if(!ls.get(2).equals("")){
					a.setLjdwjz(Double.valueOf(ls.get(2)));
				}
				a.setYwrq(Long.valueOf(ls.get(1)));
				newList.add(a);
			}
		}catch(Exception e){
			System.out.println(i);
			e.printStackTrace();
		}
		String res =  JSON.toJSONString(newList);
		FileUtils.overideString2File(res, standardTxtFilePathHashDwjz);
	}
	/**
	 * 
	 *作者 : iechenyb<br>
	 *方法描述: 生成指定赛季的所有成绩记录<br>
	 *创建时间: 2017年7月15日
	 *@param accout
	 *@param season
	 */
	public static void genAData(String account,int season){
		String filePath = "d://data//lhug//rolling/dhqh/competitor/"+account+"/"+season;
		File file = new File(filePath);
		if(!file.exists()){
			file.mkdirs();
		}
		filePath = filePath+"/a.txt";
		int totalDays = season*yearDays;
		List<TB_A> lstA = new ArrayList<TB_A>(totalDays);
		Date today = new Date();
		TB_A a = null;
		for(int i=0;i<totalDays;i++){
		    a = new TB_A(DateUtil.date2long8(DateUtil.nextSomeDay(today, i)));
		    lstA.add(a);
		}
		FileUtils.overideString2File(JSON.toJSONString(lstA), filePath);
	}
}
