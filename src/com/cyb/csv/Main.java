package com.cyb.csv;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年12月18日
 */
public class Main {
	Log log = LogFactory.getLog(Main.class);
	
	public static void main(String[] args) {
		String file="d://data//mycsv.csv";
		/*CSVFileUtil.writeCSV(file,"a,b,c,d");
		CSVFileUtil.writeCSV(file,"1,2,3,4");
		CSVFileUtil.genData();*/
		CSVFileUtil.show(file);
	}
}
