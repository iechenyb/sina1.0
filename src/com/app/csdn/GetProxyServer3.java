package com.app.csdn;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.cyb.cmd.CmdUtils;
import com.cyb.file.FileUtils;

public class GetProxyServer3 {
	static String urlinha = "https://www.kuaidaili.com/free/inha/";
	static String urlintr = "https://www.kuaidaili.com/free/intr/";
	public static void main(String[] args) {
		try {
			inha();
			intr();
			//check();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void check(){
		List<String> data = FileUtils.readFileToList(filePath);
		System.out.println("总记录数为"+data.size());
		for(String str:data){
			boolean rs = CmdUtils.telnet(str.split("#")[0], Integer.valueOf(str.split("#")[1]));
			if(rs){
				System.out.println(str+"可用！");
			}
		}
	}
	public static String filePath = "d:/data/proxy/3/list.txt";
	//国内高匿名代理
	public static void inha() throws MalformedURLException, IOException{
		for(int i=1;i<2403;i++){
			getAllIpPortTr(urlintr+i);
		}
	}
	public static void intr() throws MalformedURLException, IOException{
		for(int i=1;i<2403;i++){
			getAllIpPortTr(urlinha+i);
		}
	}
	static Map<String,String> map = new HashMap<>();
	public static void getAllIpPortTr(String url) throws MalformedURLException, IOException{
		try {
			Document doc = Jsoup.parse(new URL(url), 20000);
			Elements as = doc.select("td[data-title='IP']");
			for(int i=0;i<as.size();i++){
				Proxy p = new Proxy(as.get(i).html(),as.get(i).siblingElements().get(0).html());
				System.out.println(p);
				if(!map.containsKey(p.toString().split("#")[0])){//去重！！！
					map.put(p.toString().split("#")[0], "".intern());
					FileUtils.append(filePath, p.toString()+"\n");
					boolean rs = CmdUtils.telnet(p.toString().split("#")[0], Integer.valueOf(p.toString().split("#")[1]));
					if(rs){
						System.out.println(p.toString()+"可用！");
					}
				}
			}
		} catch (Exception e) {
			
		}
	}
}
class Proxy{
	private String ip;
	private String port;
	Proxy(String ip,String port){
		this.ip=ip;
		this.port =port;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String toString(){
		return ip+"#"+port;
	}
}
