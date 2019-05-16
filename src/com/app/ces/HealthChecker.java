package com.app.ces;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.cyb.url.HttpsClient;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2018年12月21日
 */
public class HealthChecker {
	Log log = LogFactory.getLog(HealthChecker.class);
	public static void main(String[] args) {
		String result = HttpsClient.getPageHtml("https://zkqq.kiiik.com/ZKQQServiceJ/public/requestService.do?action=CanOpenZhiku&param1=GHTSU2f1f3bdd-4704-4f91-a22c-40868a1d7150&param2=Android&param3=ZhiKu&param4=XiWeiChiCang&user=guest&password=125178351&clientDatetime=15150349");
		if("0".equals(JSON.parseObject(result).getString("resultStatus"))){
			System.err.println("掌控全球Web服务器ok!");
		}
		
	   result = HttpsClient.getPageHtml("https://hisdata.kiiik.com/HQDataServiceJ/public/getMainDayKLineDataM.do?&market=XSGE&species=rb&symbolCode=rb__zhuli&startDate=20180315&endDate=20181208&user=guest&password=125178351&app=zkqq&time=15133528");
	   if(!StringUtils.isEmpty(JSON.parseObject(result).getString("market"))){
		   System.err.println("日K线数据服务ok!");
	   }
	   
	   
	}
}
