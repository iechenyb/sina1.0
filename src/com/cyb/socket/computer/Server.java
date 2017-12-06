package com.cyb.socket.computer;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class Server {
    static BlockingQueue<Callable<Integer>> tasks  = new LinkedBlockingQueue<Callable<Integer>>(10);
    static ExecutorService work = Executors.newFixedThreadPool(5);
	public static int PORT = 9193;
	public static void main(String[] agrs) {
		ServerSocket s = null;
		try {
			s = new ServerSocket(PORT);
			System.out.println("ServerSocket Start:"+s);
			new Thread(new Runnable() {
				public void run() {
					while(true){
						try {
							work.submit(tasks.take());
							Thread.sleep(10);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						
					}
				}
			}).start();
			while(true){
				Socket socket = s.accept();
				tasks.put(new Callable<Integer>() {
					Socket socket ;
					public Callable<Integer> init(Socket socket){
						this.socket = socket;
						return this;
					}
					@Override
					public Integer call() throws Exception {
						BufferedReader br = null;
						PrintWriter pw = null;
						br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
						pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
						String str = br.readLine();
						pw.println(str);
						pw.flush();
						socket.isClosed();
						socket.isConnected();
						return 1;
					}
				}.init(socket));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
		
		}
	}
}
