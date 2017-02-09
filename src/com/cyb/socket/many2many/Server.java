package com.cyb.socket.many2many;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Server {
	public static boolean state = false;
	public static int PORT = 9193;
	public  ServerSocket server = null;
	public static List<Socket> clients_list = new ArrayList<Socket>();
	public static Map<String,Socket> clients_map = new HashMap<String, Socket>();
	public static void showClient(){
		System.out.println("clients:"+clients_map);
	}

	public  void start(){
		Socket socket = null;
		BufferedReader br = null;
		PrintWriter pw = null;
		try {
			//�趨����˵Ķ˿ں�
			server = new ServerSocket(PORT);
			state = true;
			new Thread(new ClientLoginListenerThread(server)).start();
			System.out.println("ServerSocket Start:"+server);
			//�ȴ�����,�˷�����һֱ����,ֱ����������������
			//socket = server.accept();
			//System.out.println("Connection accept socket:"+socket);
			//���ڽ��տͻ��˷���������
			/*br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			//���ڷ��ͷ�����Ϣ,���Բ���Ҫװ����ô��io��ʹ�û�����ʱ��������Ҫע�����.flush()����
			pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
			while(true){
				String str = br.readLine();
				if(str.equals("END")){
					break;
				}
				System.out.println("from Client Socket Message:"+str);
				Thread.sleep(1000);
				pw.println("Message Received");
				pw.flush();
			}		*/	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("����˳����쳣���������Ա��ϵ��");
		}
	}
	public static  void main(String[] agrs) {
		new Server().start();
	}
}
