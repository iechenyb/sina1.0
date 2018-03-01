package com.cyb.collection.utils;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2018年2月28日
 */
class OrderVO{
	private String orderNo;
	private String userId;
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}
public class 集合去重 {
	Log log = LogFactory.getLog(集合去重.class);
	public static void main(String[] args) {
        List<OrderVO> orderList = new ArrayList<OrderVO>();
        OrderVO orderVO = new OrderVO();
        orderVO.setOrderNo("11");
        orderVO.setUserId("aa");
        orderList.add(orderVO);

        orderVO = new OrderVO();
        orderVO.setOrderNo("22");
        orderVO.setUserId("bb");
        orderList.add(orderVO);

        orderVO = new OrderVO();
        orderVO.setOrderNo("33");
        orderVO.setUserId("aa");
        orderList.add(orderVO);
        
        List<OrderVO> dataList = removeDuplicateOrder(orderList);
        for(OrderVO data : dataList){
            System.out.println(data.getOrderNo() + ":" + data.getUserId());
        }
    }

    /**
     * 去重
     * 
     * @param orderList
     * @return
     * @author jqlin
     */
    private static List<OrderVO> removeDuplicateOrder(List<OrderVO> orderList) {
        Set<OrderVO> set = new TreeSet<OrderVO>(new Comparator<OrderVO>() {
            @Override
            public int compare(OrderVO a, OrderVO b) {
                // 字符串则按照asicc码升序排列
                return a.getUserId().compareTo(b.getUserId());
            }
        });
        
        set.addAll(orderList);
        return new ArrayList<OrderVO>(set);
    }
}
