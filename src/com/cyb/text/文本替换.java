package com.cyb.text;
import java.text.MessageFormat;
import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2018年9月11日
 */
public class 文本替换 {
	Log log = LogFactory.getLog(文本替换.class);
	public static void main(String[] args) {
		MessageFormat mf = new MessageFormat("aaa{0}{0}", Locale.US);
        Object arr[] = {"he"};
        System.out.println(mf.format(arr));
	}
}
