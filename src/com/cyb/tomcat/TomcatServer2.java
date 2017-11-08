package com.cyb.tomcat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import com.sun.tools.jdi.LinkedHashMap;

/**
 * http://blog.csdn.net/qiangcai/article/details/60583330 作者 : iechenyb<br>
 * 类描述: 说点啥<br>
 * 创建时间: 2017年10月19日
 * http://www.cnblogs.com/lyrand/p/7689325.html
 */
public class TomcatServer2 {

	private final static int PORT = 8088;

	public static void main(String[] args) {

		try {
			// 根据端口号启动一个serverSocket
			ServerSocket server = new ServerSocket(PORT);
			ServletHandler servletHandler = new ServletHandler(server);
			servletHandler.start();// 开启一个服务线程 如tomcat服务
			System.out.println("Tomcat 服务启动成功！");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static class ServletHandler extends Thread {
		ServerSocket server = null;

		public ServletHandler(ServerSocket server) {
			this.server = server;
		}

		@Override
		public void run() {
			while (true) {
				try {
					Socket client = null;
					client = server.accept();// ServerSocket阻塞等待客户端请求数据
					if(client!=null){
						parseRequest(client);
					}
					new Thread(new HandleRequestTask(client)).start();//启动一个处理线程！
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		
		class HandleRequestTask implements Runnable{
			Socket client ;
			public HandleRequestTask(Socket client){
				this.client = client;
			}
			@Override
			public void run() {
				if (client != null) {
					try {
						System.out.println("接收到一个客户端的请求");
						// 封装字节流到字符流
						BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
                        int ch ;
                        while((ch=reader.read())!=-1){
                        	System.out.print((char)ch);
                        }
                        defalutRespone(client);
					} catch (Exception e) {
						System.out.println("HTTP服务器错误:" + e.getLocalizedMessage());
					}
					System.out.println("当前用户关闭连接！");
				}
				closeSocket(client);
			}
			
		}
		public void defalutRespone(Socket client) throws IOException{
        	PrintStream writer = new PrintStream(client.getOutputStream(), true);
			writer.println("HTTP/1.0 200 OK");// 返回应答消息,并结束应答
			writer.println("Content-Type:text/html;charset=utf-8");
			writer.println();
			writer.println("<html><body>");
			writer.println("<a href='www.baidu.com'>百度</a>");
			writer.println(
					"<img src='https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png'></img>");
			writer.println("</html></body>");
			writer.println();// 根据 HTTP 协议, 空行将结束头信息
			writer.close();
        }
		private void closeSocket(Socket socket) {
			try {
				socket.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			System.out.println(socket + "离开了HTTP服务器");
		}
        //响应文件，模拟下载
		@SuppressWarnings("unused")
		private void transferFileHandle(String path, Socket client) {

			File fileToSend = new File(path);

			if (fileToSend.exists() && !fileToSend.isDirectory()) {
				try {
					// 根据Socket获取输出流对象，将访问的资源数据写入到输出流中
					PrintStream writer = new PrintStream(client.getOutputStream());
					writer.println("HTTP/1.0 200 OK");// 返回应答消息,并结束应答
					writer.println("Content-Type:application/binary");
					writer.println("Content-Length:" + fileToSend.length());// 返回内容字节数
					writer.println();// 根据 HTTP 协议, 空行将结束头信息

					FileInputStream fis = new FileInputStream(fileToSend);
					byte[] buf = new byte[fis.available()];
					fis.read(buf);
					writer.write(buf);
					writer.close();
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}
	public static void parseRequest(Socket client) throws IOException {  
		Map<String,String> headers = new LinkedHashMap();
		Map<String,String> paramters = new LinkedHashMap();
		String method="";
	    LineNumberReader br = new LineNumberReader(new InputStreamReader(client.getInputStream()));  
	    StringBuffer sb = new StringBuffer();  
	    String str = null;  
	    try {  
	        //读取请求行  
	        String requestLine = br.readLine();  
	        if (!StringUtils.isEmpty(requestLine)) {  
	            sb.append(requestLine);  
	            String[] reqs = requestLine.split(" ");  
	            if (reqs != null && reqs.length > 0) {  
	                if ("GET".equals(reqs[0])) {  
	                    method = "GET";  
	                } else {  
	                    method = "POST";  
	                }  
	            }  
	        }  
	        //读取请求头  
	        while ((str = br.readLine()) != null) {  
	            if ("".equals(str)) {  
	                break;  
	            }  
	            if (!StringUtils.isEmpty(str)) {  
	                if (str.indexOf(":") > 0) {  
	                    String[] strs = str.split(":");  
	                    headers.put(strs[0].toLowerCase(), strs[1].trim());  
	                }  
	            }  
	            sb.append(str).append("\n");  
	        }  
	        //POST请求，Content-type为 multipart/form-data  
	        String contentType = null;  
	        if ("POST".equals(method) && ((contentType = headers.get("content-type")) != null  
	                && headers.get("content-type").startsWith("multipart/form-data"))) {  
	        	//带文件的请求
	            //文件上传的分割位 这里只处理单个文件的上传  
	            String boundary = contentType.substring(contentType.indexOf("boundary") +  
	                    "boundary=".length());  
	            //解析消息体  
	            while ((str = br.readLine()) != null) {  
	                //解析结束的标记  
	                do {  
	                    //读取boundary中的内容  
	                    //读取Content-Disposition  
	                    str = br.readLine();  
	                    //说明是文件上传  
	                    if (str.indexOf("Content-Disposition:") >= 0 && str.indexOf("filename") > 0) {  
	                        str = str.substring("Content-Disposition:".length());  
	                        String[] strs = str.split(";");  
	                        String fileName = strs[strs.length - 1].replace("\"", "").split("=")[1];  
	                        System.out.println("fileName = " + fileName);  
	                        //这一行是Content-Type  
	                        br.readLine();  
	                        //这一行是换行  
	                        br.readLine();  
	                        //正式去读文件的内容  
	                        BufferedWriter bw = null;  
	                        try {  
	                            bw = new BufferedWriter(new OutputStreamWriter(new  
	                                    FileOutputStream("d://data//"+File.separator + fileName), "UTF-8"));  
	                            System.out.println("已经将文件上传到："+"d://data//" + fileName);
	                            while (true) {  
	                                str = br.readLine();  
	                                if (str.startsWith("--" + boundary)) {  
	                                    break;  
	                                }  
	                                bw.write(str);  
	                                bw.newLine();  
	                            }  
	                            bw.flush();  
	                        } catch (Exception e) {  
	  
	                        } finally {  
	                            if (bw != null) {  
	                                bw.close();  
	                            }  
	                        }  
	                    }  
	                    if (str.indexOf("Content-Disposition:") >= 0) {  
	                        str = str.substring("Content-Disposition:".length());  
	                        String[] strs = str.split(";");  
	                        String name = strs[strs.length - 1].replace("\"", "").split("=")[1];  
	                        br.readLine();  
	                        StringBuilder stringBuilder = new StringBuilder();  
	                        while (true) {  
	                            str = br.readLine();  
	                            if (str.startsWith("--" + boundary)) {  
	                                break;  
	                            }  
	                            stringBuilder.append(str);  
	                        }  
	                        paramters.put(name, stringBuilder.toString());  
	                    }  
	                } while (("--" + boundary).equals(str));  
	                //解析结束  
	                if (str.equals("--" + boundary + "--")) {  
	                    break;  
	                }  
	            }  
	        }else if ("POST".equals(method) && ((contentType = headers.get("content-type")) != null  
	                && headers.get("content-type").startsWith("application/x-www-form-urlencoded"))) {
	        	System.out.println("普通的post请求！");
	        }  
	        System.out.println(sb.toString());  
	        //获取URI  
	        String uri = sb.toString();  
	        int flag = -1;  
	        //说明有参数  
	        if ((flag = uri.indexOf('?')) >= 0) {  
	            String oldUri = uri;  
	            uri = uri.substring(0,flag);  
	            String parameterPath = oldUri.substring(flag+1);  
	            String[] parameter = parameterPath.split("&");  
	            if (parameter != null && parameter.length > 0) {  
	                for (int i = 0; i < parameter.length; i++) {  
	                    String str1 = parameter[i];  
	                    if((flag = str1.indexOf('=')) >= 0){  
	                        String key = str1.substring(0,flag);  
	                        String value = str1.substring(flag+1);  
	                        paramters.put(key,value);  
	                    }else{  
	                    	paramters.put(str,null);  
	                    }  
	                }  
	            }  
	        }  
	        System.out.println("header:"+headers);
	        System.out.println("params:"+paramters);
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    }  
	}  

}