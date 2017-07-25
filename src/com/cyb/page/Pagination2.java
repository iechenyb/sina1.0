package com.cyb.page;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

import com.cyb.reflect.ReflectUtils;
/**
 * 
 * @author DHUser
 *
 * @param <T>
 */
public class Pagination2 implements Serializable{
    
    private static final long serialVersionUID = 5104811017362151385L;

    /**当前页*/
    private int currentPage;
    
    /**每页显示记录数*/
    private int pageSize;
    
    /**总记录数*/
    private long recordCount = 1L;
    
    /**总页数*/
    private int pageCount;
    
    /**偏移数*/
    private int offset;
    
    /**上一页*/
    private int prePage;
    
    /**下一页*/
    private int nextPage;
    
    /**是否有上一页*/
    private boolean hasPrePage;
    
    /**是否有下一页*/
    private boolean hasNextPage;
  
    
    /**
     * 构造函数,计算总页数、是否有上一页、下一页等.
     * @param currentPage   当前页
     * @param pageSize      每页显示记录数
     * @param recordCount   总记录数
     */
    public  Pagination2(int currentPage,int pageSize,long recordCount) {
        this.currentPage = currentPage;
        if(currentPage < 1) {
            this.currentPage = 1;
        }
        this.pageSize = pageSize;
        this.recordCount = recordCount;
        //上一页等于当前页减一
        this.prePage = this.currentPage - 1;
        if(this.prePage < 1) {
            this.hasPrePage = false;//没有上一页
            this.prePage = 1;
        }else {
            this.hasPrePage = true;//有上一页
        }
        //计算总页数
        /**
         * print("ceilTest8 : ".. math.ceil(8)) 结果: 8
		   print("ceilTest88.5: ".. math.ceil(8.5))结果: 9
		   print("ceilTest8-8: ".. math.ceil(-8))结果: -8
		   print("ceilTest8-8.5: ".. math.ceil(-8.5))结果: -8
         */
        this.pageCount = (int)Math.ceil(recordCount / (double)pageSize);
        if(this.currentPage > this.pageCount) {
            this.currentPage = this.pageCount;
        }
        //下一页等于当前页加一
        this.nextPage = this.currentPage + 1;
        if(this.nextPage > this.pageCount) {
            this.hasNextPage = false;//没有下一页
            this.nextPage = this.pageCount;
        }else {
            this.hasNextPage = true;//有下一页
        }
        //偏移量
        this.offset = (this.currentPage - 1)*pageSize+1;
    }
    public int getCurrentPage() {
        return currentPage;
    }
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
    public boolean isHasNextPage() {
        return hasNextPage;
    }
    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }
    public boolean isHasPrePage() {
        return hasPrePage;
    }
    public void setHasPrePage(boolean hasPrePage) {
        this.hasPrePage = hasPrePage;
    }
    public int getNextPage() {
        return nextPage;
    }
    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }
    public int getOffset() {
        return offset;
    }
    public void setOffset(int offset) {
        this.offset = offset;
    }
    public int getPageCount() {
        return pageCount;
    }
    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }
    public int getPageSize() {
        return pageSize;
    }
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    public int getPrePage() {
        return prePage;
    }
    public void setPrePage(int prePage) {
        this.prePage = prePage;
    }
    public long getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(long recordCount) {
        this.recordCount = recordCount;
    }

    public static void main(String[] args) throws InvocationTargetException {
    	Pagination2 p = new Pagination2(1,15,300);
    	for(int i=1;i<p.getPageCount();i++){
    		Pagination2 p_ = new Pagination2(i,15,300);
    		System.out.println(p_.getOffset()+","+p_.getPageSize()*i);
    	}
	}
}