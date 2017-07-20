package com.cyb.fanxing;

public class Main {
	public static void main(String[] args) {
		 ZhangsanDao zhangsanDao = new ZhangsanDao();  
	     Zhangsan zhangsan = zhangsanDao.get(null); 
	     System.out.println(zhangsan);
	     LisiDao lisiDao = new LisiDao();  
	     Lisi lisi = lisiDao.get(null); 
	     System.out.println(lisi);
	     System.out.println(lisiDao.getT1());
	     System.out.println(lisiDao.getT2());
	     LisiService service = new LisiService();
	     System.out.println(service.dao.getT1());
	     System.out.println(service.dao.getT2());
	}
}
