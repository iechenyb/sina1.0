package com.cyb.log.my;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年8月25日
 */
import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.ErrorCode;
import org.apache.log4j.spi.LoggingEvent;

import com.fasterxml.jackson.core.JsonProcessingException;  
  
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
    public Map<String, Object> format(LoggingEvent loggingEvent) {
        String json;
        Map<String, Object> map = new LinkedHashMap<String, Object>(0);
        Map<String, Object> source = new LinkedHashMap<String, Object>(0);
        source.put("method", loggingEvent.getLocationInformation().getMethodName());
        source.put("class", loggingEvent.getLocationInformation().getClassName());
        source.put("file", loggingEvent.getLocationInformation().getFileName());
        source.put("line", safeParse(loggingEvent.getLocationInformation().getLineNumber()));

        map.put("timeMillis", loggingEvent.getTimeStamp());
        map.put("thread", loggingEvent.getThreadName());
        map.put("level", loggingEvent.getLevel().toString());
        map.put("loggerName", loggingEvent.getLocationInformation().getClassName());
        map.put("source", source);
        map.put("endOfBatch", false);
        map.put("loggerFqcn", loggingEvent.getFQNOfLoggerClass());
        map.put("message", safeToString(loggingEvent.getMessage()));
        map.put("thrown", formatThrowable(loggingEvent));
        return map;
    }
    private List<Map<String, Object>> formatThrowable(LoggingEvent le) {
        if (le.getThrowableInformation() == null ||
                le.getThrowableInformation().getThrowable() == null)
            return null;
        List<Map<String, Object>> traces = new LinkedList<Map<String, Object>>();
        Map<String, Object> throwableMap = new LinkedHashMap<String, Object>(0);
        StackTraceElement[] stackTraceElements = le.getThrowableInformation().getThrowable().getStackTrace();
        for (StackTraceElement stackTraceElement : stackTraceElements) {
            throwableMap.put("class", stackTraceElement.getClassName());
            throwableMap.put("file", stackTraceElement.getFileName());
            throwableMap.put("line", stackTraceElement.getLineNumber());
            throwableMap.put("method", stackTraceElement.getMethodName());
            throwableMap.put("location", "?");
            throwableMap.put("version", "?");
            traces.add(throwableMap);
        }
        return traces;
    }
    
    private static String safeToString(Object obj) {
        if (obj == null) return null;
        try {
            return obj.toString();
        } catch (Throwable t) {
            return "Error getting message: " + t.getMessage();
        }
    }

    private static Integer safeParse(String obj) {
        try {
            return Integer.parseInt(obj.toString());
        } catch (NumberFormatException t) {
            return null;
        }
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
        // 控制台打印日志  event
        //System.out.println(this.layout.format(event));
        System.out.println(format(event));
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
