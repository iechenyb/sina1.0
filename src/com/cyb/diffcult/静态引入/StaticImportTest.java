package com.cyb.diffcult.静态引入;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2018年8月21日
 */
import static com.cyb.diffcult.静态引入.Common.AGE;
import static com.cyb.diffcult.静态引入.Common.output;
public class StaticImportTest {
	Log log = LogFactory.getLog(StaticImportTest.class);
	public static void main(String[] args) {
		int a = AGE;
        output();
        System.out.println(a);
	}
}
