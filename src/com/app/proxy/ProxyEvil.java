package com.app.proxy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import net.sf.json.JSONObject;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.cyb.h2.H2Manager;
import com.cyb.proxy.ProxyUtils;
import com.cyb.url.HttpsClient;
import com.cyb.url.UrlUtils;

public class ProxyEvil {
	private static String url = "http://www.iechenyb.website/baseweb/infor.php";
	public static void main(String[] args) throws IOException {
		H2Manager.start();
		new ProxyDbUtils("app"); 
		List<Proxy> d1 = getProxyIp31fHttps("https://31f.cn/https-proxy/");
		List<Proxy> d2 = getProxyIp31fHttps("https://31f.cn/http-proxy/");
		List<Proxy> d3 = getProxyIp31fHttps("https://31f.cn/socks-proxy/");
		List<Proxy> d4 = get360ProxyIp("http://www.proxy360.cn/Region/China");
		List<Proxy> d5 = get360ProxyIp("http://www.proxy360.cn/Region/Brazil");
		List<Proxy> d6 = get360ProxyIp("http://www.proxy360.cn/Region/America");
		List<Proxy> d7 = get360ProxyIp("http://www.proxy360.cn/Region/Taiwan");
		List<Proxy> d8 = get360ProxyIp("http://www.proxy360.cn/Region/Japan");
		List<Proxy> d9 = get360ProxyIp("http://www.proxy360.cn/Region/Thailand");
		List<Proxy> d10 = get360ProxyIp("http://www.proxy360.cn/Region/Vietnam");
		List<Proxy> d11 = get360ProxyIp("http://www.proxy360.cn/Region/bahrein");
		d1.addAll(d2);		d1.addAll(d3);		d1.addAll(d4);
		d1.addAll(d5);		d1.addAll(d6);		d1.addAll(d7);
		d1.addAll(d8);		d1.addAll(d9);		d1.addAll(d10);
		d1.addAll(d11);
		for(int type=1 ;type<=4;type++){
			for(int i=1;i<10;i++){
				d1.addAll(get3366ProxyIp(type,i));
			}
		}
		for(int i=0;i<d1.size();i++){
			try {
				Proxy proxy=d1.get(i);
				System.out.println((i+1)+"-"+d1.size());
				ProxyDbUtils.dbUtils.update("delete from proxy where ip='"+proxy.getIp()+"'"+" and port="+proxy.getPort());
				ProxyDbUtils.dbUtils.save(proxy);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		ProxyDbUtils.dbUtils.close();
		H2Manager.stop();
		System.exit(0);
	}
	public static List<Proxy> getProxyIp31fHttps(String url) throws IOException{
		List<Proxy> data = new ArrayList<Proxy>();
		Document doc = Jsoup.parse(HttpsClient.getPageHtml(url));
		Elements links = doc.select("table[class='table table-striped'] tr");
		String linkText = "";
		Proxy proxy = null;
		for (Element link : links) {
			linkText = link.text().replaceAll(" ", "#");
			if(com.app.proxy.ProxyUtils.isUseable(url, linkText.split("#")[1], linkText.split("#")[2])){
				proxy = new Proxy();
				try {
					proxy.setIp(linkText.split("#")[1]);
					proxy.setPort(Integer.valueOf(linkText.split("#")[2]));
					data.add(proxy);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return data;
	}
	public static List<Proxy> get360ProxyIp(String url) throws IOException{
		//"http://www.proxy360.cn/Region/China"
		List<Proxy> data = new ArrayList<Proxy>();
		Document doc = Jsoup.connect(url).get();
		Elements links = doc.select("div[name='list_proxy_ip']");
		String linkText = "";
		Proxy proxy = null;
		for (Element link : links) {
			linkText = link.text().replaceAll(" ", "#");
			if(com.app.proxy.ProxyUtils.isUseable(url, linkText.split("#")[0], linkText.split("#")[1])){
				proxy = new Proxy();
				try {
					proxy.setIp(linkText.split("#")[0]);
					proxy.setPort(Integer.valueOf(linkText.split("#")[1]));
					data.add(proxy);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return data;
	}
	public static List<Proxy> getProxyIp31fNew(String url) throws IOException{
		Document doc = Jsoup.connect(url).get();
		List<Proxy> data = new ArrayList<Proxy>();
		Elements links = doc.select("table[class='table table-striped'] tr");
		String linkText = "";
		Proxy proxy = null;
		for (Element link : links) {
			linkText = link.text().replaceAll(" ", "#");
			if(com.app.proxy.ProxyUtils.isUseable(url, linkText.split("#")[0], linkText.split("#")[1])){
				proxy = new Proxy();
				try {
					proxy.setIp(linkText.split("#")[0]);
					proxy.setPort(Integer.valueOf(linkText.split("#")[1]));
					data.add(proxy);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return data;
	}
	public static String m1(String host,String port) throws IOException{
		String str="";
		ProxyUtils.setServiceProxy(host, port);
		str=UrlUtils.downLoadFromUrl(url, null, null);
		ProxyUtils.removeServiceProxy();
		return str;
	}
	public static void m2(String host,String port) throws IOException{
		com.cyb.html.UrlUtils.downLoadFromUrl(url, host, port);
	}
	@SuppressWarnings("null")
	public static List<Proxy> get3366ProxyIp(int type,int page) throws IOException{
		String url1 = "http://www.ip3366.net/?";
		List<Proxy> data = new ArrayList<Proxy>();
		Document doc = Jsoup.connect(url1+"stype="+type+"&page="+page).get();
		Elements links = doc.select("tbody>tr");
		String linkText = "";
		Proxy proxy = null;
		for (Element link : links) {
			linkText = link.text().replaceAll(" ", "#");
			if(com.app.proxy.ProxyUtils.isUseable(url, linkText.split("#")[0], linkText.split("#")[1])){
				proxy.setIp(linkText.split("#")[0]);
				proxy.setPort(Integer.valueOf(linkText.split("#")[1]));
				data.add(proxy);
			}
		}
		return data;
	}
	public static JSONObject httpsRequest(String requestUrl, String requestMethod, String outputStr) {
        JSONObject jsonObject = null;
        StringBuffer buffer = new StringBuffer();
        try {
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
            TrustManager[] tm = { /*new MyX509TrustManager()*/ };
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            // 从上述SSLContext对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();

            URL url = new URL(requestUrl);
            HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
            httpUrlConn.setSSLSocketFactory(ssf);

            httpUrlConn.setDoOutput(true);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);
            // 设置请求方式（GET/POST）
            httpUrlConn.setRequestMethod(requestMethod);

            if ("GET".equalsIgnoreCase(requestMethod))
                httpUrlConn.connect();

            // 当有数据需要提交时
            if (null != outputStr) {
                OutputStream outputStream = httpUrlConn.getOutputStream();
                // 注意编码格式，防止中文乱码
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }

            // 将返回的输入流转换成字符串
            InputStream inputStream = httpUrlConn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
            // 释放资源
            inputStream.close();
            inputStream = null;
            httpUrlConn.disconnect();
            jsonObject = JSONObject.fromObject(buffer.toString());
        } catch (ConnectException ce) {
            System.out.println("Weixin server connection timed out.");
        } catch (Exception e) {
            System.out.println("https request error:{}");
        }
        return jsonObject;
    }
}
