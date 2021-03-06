package com.cyb.tomcat;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.net.URLDecoder;
import java.util.StringTokenizer;

/**http://blog.csdn.net/study_zhxu/article/details/55212006
=======
import org.apache.commons.io.IOUtils;

/**
>>>>>>> f3fd406b7c69e10ee95ea6a57d34f4947d059f24
 * http://blog.csdn.net/qiangcai/article/details/60583330 作者 : iechenyb<br>
 * 类描述: 说点啥<br>
 * 创建时间: 2017年10月19日
 * http://www.cnblogs.com/lyrand/p/7689325.html
 */
public class TomcatServer {

	private final static int PORT = 8088;

	public static void main(String[] args) {

		try {
			// 根据端口号启动一个serverSocket
			ServerSocket server = new ServerSocket(PORT);
			ServletHandler servletHandler = new ServletHandler(server);
			servletHandler.start();// 开启一个服务线程 如tomcat服务
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
					if (client != null) {
						try {
							System.out.println("接收到一个客户端的请求");

							// 根据客户端的Socket对象获取输入流对象。
							// 封装字节流到字符流
							BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
							DataInputStream reader1 = new DataInputStream((client.getInputStream()));
							// GET /test.jpg /HTTP1.1
							// http请求由三部分组成，分别是：请求行、消息报头、请求正文。
							// 这里取的第一行数据就是请求行。http协议详解可以参考http://www.cnblogs.com/li0803/archive/2008/11/03/1324746.html说的很详细
							String line = reader.readLine();

							System.out.println("line: " + line);

							// 拆分http请求路径，取http需要请求的资源完整路径
							String resource = line.substring(line.indexOf('/'), line.lastIndexOf('/') - 5);

							System.out.println("the resource you request is: " + resource);

							resource = URLDecoder.decode(resource, "UTF-8");

							// 获取到这次请求的方法类型，比如get或post请求
							String method = new StringTokenizer(line).nextElement().toString();

							System.out.println("the request method you send is: " + method);

							// 继续循环读取浏览器客户端发出的一行一行的数据
							while ((line = reader.readLine()) != null) {
								if (line.equals("")) {// 当line等于空行的时候标志Header消息结束
									break;
								}
								System.out.println("the Http Header is : " + line);
							}
							/*for(int i=0;i<10;i++) {
								System.out.println("the body is :"+reader.readLine());
							}*/
							char[] c=new char[1024];  
					        int temp=0;  
					        int len=0;  
					        while((temp=reader.read())!=-1){  
					            c[len]=(char) temp;  
					            len++;  
					        }  
					        System.out.println(new String(c,0,len));  
							// 如果是POST的请求，直接打印POST提交上来的数据
							if ("post".equals(method.toLowerCase())) {
								System.out.println("the post request body is: " + reader.readLine());
							} else if ("get".equals(method.toLowerCase())) {
								// 判断是get类型的http请求处理
								// 根据http请求的资源后缀名来确定返回数据
								// 比如下载一个图片文件，我这里直接给定一个图片路径来模拟下载的情况
								if (resource.endsWith(".jpg")) {
									transferFileHandle("d://123.jpg", client);
									closeSocket(client);
									continue;
								} else {
									// 直接返回一个网页数据
									// 其实就是将html的代码以字节流的形式写到IO中反馈给客户端浏览器。
									// 浏览器会根据http报文“Content-Type”来知道反馈给浏览器的数据是什么格式的，并进行什么样的处理
									PrintStream writer = new PrintStream(client.getOutputStream(), true);
									writer.println("HTTP/1.0 200 OK");// 返回应答消息,并结束应答
									writer.println("Content-Type:text/html;charset=utf-8");
									writer.println();
									// writer.println("Content-Length:" +
									// html.getBytes().length);// 返回内容字节数
									writer.println("<html><body>");
									writer.println("<a href='www.baidu.com'>百度</a>");
									writer.println(
											"<img src='https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png'></img>");
									writer.println("</html></body>");
									// writer.println("HTTP/1.0 404 Not
									// found");// 返回应答消息,并结束应答
									writer.println();// 根据 HTTP 协议, 空行将结束头信息
									writer.close();
									closeSocket(client);// 请求资源处理完毕，关闭socket链接
									continue;
								}
							}

						} catch (Exception e) {
							System.out.println("HTTP服务器错误:" + e.getLocalizedMessage());
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		

		private void closeSocket(Socket socket) {
			try {
				socket.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			System.out.println(socket + "离开了HTTP服务器");
		}

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

}