package com.cyb.diffcult.abstrac;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年8月31日
 */
public class AbstractChild extends Abstract{
	Log log = LogFactory.getLog(AbstractChild.class);

	@Override
	public void print() {
		//必须实现接口的方法
		System.out.println("heihei!");
	}
	public static void main(String[] args) {
		Interface in = new AbstractChild();
		in.print();
		Abstract ab = new AbstractChild();
		ab.print();
		Abstract ab1 = new Abstract() {
			@Override
			public void print() {
				System.out.println("abstract override!");
			}
		};
		ab1.print();
		Interface in1 = new Interface() {
			@Override
			public void print() {
				System.out.println("interface override!");
			}

			@Override
			public void absprint() {//实现抽象方法
				
			}
		};
		in1.print();
	}
}
