package com.kiiik.web.rsa.controller;

import java.io.IOException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class RSAUtils5 {
	// 生成秘钥对
	public static KeyPair getKeyPair() throws Exception {
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
		keyPairGenerator.initialize(512);//2048
		KeyPair keyPair = keyPairGenerator.generateKeyPair();
		return keyPair;
	}

	// 获取公钥(Base64编码)
	public static String getPublicKey(KeyPair keyPair) {
		PublicKey publicKey = keyPair.getPublic();
		byte[] bytes = publicKey.getEncoded();
		return byte2Base64(bytes);
	}

	// 获取私钥(Base64编码)
	public static String getPrivateKey(KeyPair keyPair) {
		PrivateKey privateKey = keyPair.getPrivate();
		byte[] bytes = privateKey.getEncoded();
		return byte2Base64(bytes);
	}

	// 将Base64编码后的公钥转换成PublicKey对象
	public static PublicKey string2PublicKey(String pubStr) throws Exception {
		byte[] keyBytes = base642Byte(pubStr);
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PublicKey publicKey = keyFactory.generatePublic(keySpec);
		return publicKey;
	}

	// 将Base64编码后的私钥转换成PrivateKey对象
	public static PrivateKey string2PrivateKey(String priStr) throws Exception {
		byte[] keyBytes = base642Byte(priStr);
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
		return privateKey;
	}

	// 公钥加密
	public static byte[] publicEncrypt(byte[] content, PublicKey publicKey) throws Exception {
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		byte[] bytes = cipher.doFinal(content);
		return bytes;
	}

	// 私钥解密
	public static byte[] privateDecrypt(byte[] content, PrivateKey privateKey) throws Exception {
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		byte[] bytes = cipher.doFinal(content);
		return bytes;
	}

	// 字节数组转Base64编码
	public static String byte2Base64(byte[] bytes) {
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(bytes);
	}

	// Base64编码转字节数组
	public static byte[] base642Byte(String base64Key) throws IOException {
		BASE64Decoder decoder = new BASE64Decoder();
		return decoder.decodeBuffer(base64Key);
		//return Base64Utils.decode(base64Key);
	}

	public static String decode(String privateKeyStr, String byte2Base64Content) throws Exception {
		// 将Base64编码后的私钥转换成PrivateKey对象
		PrivateKey privateKey = RSAUtils5.string2PrivateKey(privateKeyStr);
		// 加密后的内容Base64解码
		byte[] base642Byte = RSAUtils5.base642Byte(byte2Base64Content);
		// 用私钥解密
		byte[] privateDecrypt = RSAUtils5.privateDecrypt(base642Byte, privateKey);
		// 解密后的明文
		System.out.println("解密后的明文: " + new String(privateDecrypt));
		return new String(privateDecrypt);
	}

	static String pub = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAJ8ZeIfGRqSrEBDXfl7ICONn5emcBKdU"
			+ "NIhPnct3EtgfulmwhyB5uUJzwpQQ8MsW1mTk9T/GawIVjwJtBZ6iwQUCAwEAAQ==";

	static String pri = "MIIBVAIBADANBgkqhkiG9w0BAQEFAASCAT4wggE6AgEAAkEAnxl4h8ZGpKsQENd+"
			+ "XsgI42fl6ZwEp1Q0iE+dy3cS2B+6WbCHIHm5QnPClBDwyxbWZOT1P8ZrAhWPAm0F"
			+ "nqLBBQIDAQABAkAF3ri1ubkNhko3dNvyv0qkqUawUdNSISc3CEj4ItKNKr9gDUh2"
			+ "JsWk8pQXzwX3VjXRBROucrM6v+/YdStEEabZAiEA8fZlhvrP+UuypWRPLFUEx2Wu"
			+ "ydBpB6vvSnJy1FrfLm8CIQCoVGavUf+9c99IjadSt0T3kyB/ViBwheFjOUQe7G2B"
			+ "ywIgOmZoNt4OmHMm2TiOy3pxvtbSCy/cSde719Ir2Qld2M0CIBKEHjuA0Jndah1H"
			+ "/7TYlgyJsZRwO+dEwtMwVG72YOFbAiEAhj1jQfIqtwat7GkukVV2yLsODC7/H1Lj"
			+ "i5jLTYhJlGw=";

	public static void test() {
		System.out.println(pri);
		System.out.println();
		// pub="-----BEGIN PUBLIC
		// KEY-----↵MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAJ8ZeIfGRqSrEBDXfl7ICONn5emcBKdU↵NIhPnct3EtgfulmwhyB5uUJzwpQQ8MsW1mTk9T/GawIVjwJtBZ6iwQUCAwEAAQ==↵-----END
		// PUBLIC KEY-----";
		// pri="-----BEGIN PRIVATE
		// KEY-----↵MIIBVAIBADANBgkqhkiG9w0BAQEFAASCAT4wggE6AgEAAkEAnxl4h8ZGpKsQENd+↵XsgI42fl6ZwEp1Q0iE+dy3cS2B+6WbCHIHm5QnPClBDwyxbWZOT1P8ZrAhWPAm0F↵nqLBBQIDAQABAkAF3ri1ubkNhko3dNvyv0qkqUawUdNSISc3CEj4ItKNKr9gDUh2↵JsWk8pQXzwX3VjXRBROucrM6v+/YdStEEabZAiEA8fZlhvrP+UuypWRPLFUEx2Wu↵ydBpB6vvSnJy1FrfLm8CIQCoVGavUf+9c99IjadSt0T3kyB/ViBwheFjOUQe7G2B↵ywIgOmZoNt4OmHMm2TiOy3pxvtbSCy/cSde719Ir2Qld2M0CIBKEHjuA0Jndah1H↵/7TYlgyJsZRwO+dEwtMwVG72YOFbAiEAhj1jQfIqtwat7GkukVV2yLsODC7/H1Lj↵i5jLTYhJlGw=↵-----END
		// PRIVATE KEY-----";

		// pub = pub.replace("-----BEGIN PUBLIC KEY-----↵",
		// "").replace("↵-----END PUBLIC KEY-----", "");
		// pri = pri.replace("-----BEGIN PRIVATE KEY-----↵",
		// "").replace("↵-----END PRIVATE KEY-----","");

		try {
			// ===============生成公钥和私钥，公钥传给客户端，私钥服务端保留==================
			String publicKeyStr;
			String privateKeyStr;
			// 生成RSA公钥和私钥，并Base64编码
			
			  KeyPair keyPair = RSAUtils5.getKeyPair(); 
			   publicKeyStr =
			  RSAUtils5.getPublicKey(keyPair); 
			   privateKeyStr =
			  RSAUtils5.getPrivateKey(keyPair);
			   
			   
			 System.out.println("pubkey:"+publicKeyStr);
			 System.out.println("prikey:"+privateKeyStr);
			 
			publicKeyStr = pub;
			privateKeyStr = pri;

			System.out.println(publicKeyStr);

			System.out.println(privateKeyStr);

			System.out.println("RSA公钥Base64编码:" + publicKeyStr);
			System.out.println("RSA私钥Base64编码:" + privateKeyStr);

			// =================客户端=================
			// hello, i am infi, good night!加密hello, i am infi, good
			String message = "欢迎来到chacuo.net";
			// 将Base64编码后的公钥转换成PublicKey对象
			PublicKey publicKey = RSAUtils5.string2PublicKey(publicKeyStr);
			// 用公钥加密
			byte[] publicEncrypt = RSAUtils5.publicEncrypt(message.getBytes(), publicKey);
			// 加密后的内容Base64编码
			String byte2Base64 = RSAUtils5.byte2Base64(publicEncrypt);
			System.out.println(byte2Base64.length() + "  公钥加密并Base64编码的结果：" + byte2Base64);

			// ############## 网络上传输的内容有Base64编码后的公钥 和 Base64编码后的公钥加密的内容
			// #################

			// ===================服务端================
			// 将Base64编码后的私钥转换成PrivateKey对象
			PrivateKey privateKey = RSAUtils5.string2PrivateKey(privateKeyStr);
			// 加密后的内容Base64解码
			byte[] base642Byte = RSAUtils5.base642Byte(byte2Base64);
			// 用私钥解密
			byte[] privateDecrypt = RSAUtils5.privateDecrypt(base642Byte, privateKey);
			// 解密后的明文
			System.out.println("解密后的明文: " + new String(privateDecrypt));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		try {
			test();
			pri="MIIBVAIBADANBgkqhkiG9w0BAQEFAASCAT4wggE6AgEAAkEApUt0u8q1eK+LdmY3JGC1RfFI4sUu"+
"8k9Xf0CLZrD1m4FG//8cJVMKG74IdqqUK8qsL4uJA7yk54tYdlWGKXU1JwIDAQABAkAgSJyEERNg"+
"fui+NziN4lWFU5ror5p7uQNd1z1i0S0nMcb3NDVd1UpF5SyjGkyM2HVPz5NNv9h4+fxgOp3djzAB"+
"AiEA9xL53l9LhZbyuRCbgsiXOxLepVee72rHRUtEYWWvFy0CIQCrRCgwnNsyDhXRywpGXVc9G0wv"+
"4sXnjQloZ2g4wt9yIwIhAN6eQHl2z6tcqPLK3fPnHSxjAi1kV5ZNVaNAACrp3es5AiB+maJP1RNf"+
"G/RbIYjV3NfJDu2xlCOQ97uVlqL/GmZMGwIgehFyZEbLnWPcZxP/OMcsuRx6uSmwvrx5uB1FsXEy"+
"3Hs=";
			String enstring = "a5fVewIRT/L1drj1oF0YBwLU/cQRHh7daZu6aWJR85GcIC/4x/eEbViOw+e9AN60GOz8evPy5qk5Dt+SbESi3g==";//自己生成
			String res = decode(pri,enstring);
			//System.out.println(res);
			//enstring = "BnZRMCWxT/6gsP6iuRvOVCFTF7slEaM0oL+nzaBxC2rr9zvI9Y3pY2P1yLLGPIoIuJc7exmwyrCUQXAy8dOCoQ==";//朱
			enstring="cFjuVhzc1nA7FFcZrpaadjJ2/nu5+CHY34T64E6hx31kucBtI/e9x19VB1hjalCAOHgMMxVSAC00ZTveejyD5A==";
			
			BASE64Decoder de = new BASE64Decoder();
			String aaa = new String(de.decodeBuffer(enstring));
			res = decode(pri,enstring);
			System.out.println(res);
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}
}
