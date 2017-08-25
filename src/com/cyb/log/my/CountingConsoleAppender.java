package com.cyb.log.my;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年8月25日
 */
import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.ErrorCode;
import org.apache.log4j.spi.LoggingEvent;  
  
public class CountingConsoleAppender extends AppenderSkeleton  
{  
    protected int count = 0;  
    protected int limit = 10;  
  
    /** 
     * 关闭资源 
     */  
    @Override  
    public void close()  
    {  
        if (this.closed)  
        {  
            return;  
        }  
        this.closed = true;  
    }  
  
    /** 
     * 这里需要使用格式化器 
     */  
    @Override  
    public boolean requiresLayout()  
    {  
        return true;  
    }  
  
    @Override  
    protected void append(LoggingEvent event)  
    {  
        // 1，验证，如果没有格式化器，报错，如果次数超过限制，报错  
        if (this.layout == null)  
        {  
            errorHandler.error("没有设置[" + name + "]日志格式化器。", null, ErrorCode.MISSING_LAYOUT);  
            return;  
        }  
        if (count >= limit)  
        {  
            errorHandler.error("输出次数[" + limit + "]达到了[" + getName() + "]的上限。", null, ErrorCode.WRITE_FAILURE);  
            return;  
        }  
        // 控制台打印日志  
        System.out.println("xxxx:"+this.layout.format(event));  
        // 如果配置的格式化器没有处理异常，这里打印异常栈信息  
        if (layout.ignoresThrowable())  
        {  
            String[] throwableStrRep = event.getThrowableStrRep();  
            if (throwableStrRep!=null)  
            {  
                for (String throwStr : throwableStrRep)  
                {  
                    System.out.println(throwStr);  
                }  
            }  
        }  
        // 打印日志结束，修改打印次数  
        count++;  
    }  
  
    public int getCount()  
    {  
        return count;  
    }  
  
    public CountingConsoleAppender setCount(int count)  
    {  
        this.count = count;  
        return this;  
    }  
  
    public int getLimit()  
    {  
        return limit;  
    }  
  
    public void setLimit(int limit)  
    {  
        this.limit = limit;  
    }  
  
} 
