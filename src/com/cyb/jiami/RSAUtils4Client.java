package com.cyb.jiami;
import java.io.ByteArrayOutputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.spec.X509EncodedKeySpec;
import java.util.Map;

import javax.crypto.Cipher;

import sun.misc.BASE64Encoder;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2018年7月19日
 */
public class RSAUtils4Client {
	//私钥
	static String privateKey="MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAJEq5BlUar57utzY+9Tha8GLK/ufni6qdhKZYmCdA6ES8tRvE2sBu4+9nKyK/lgv5QqWh19ygoWhDPmnZvoPGcbeGcHbMZ4YlXGyEgOjmIR33NqG9BrqixOcNPWEfme7Jn5f35iL2pkmMeGZd8aeGYdxDUjpIUgLkctrdyvt3NVhAgMBAAECgYBUaKcnH1HOHq3B2p1b5BM+/8h8UAyvP8jV+cAdQ08n6pet9ERLNT+1TeB653sLFhZM+MgQNMo2HzYnODKFdiBa/bBCjiDFft0xUXlbtXUSBsTFQEiepGii5ILRnSDqEvcpcQ/3sbhZ5q8RKXXcZBignzGN3rcizFJRxodMiFTQAQJBAPiiDY3g4XsAr4KiuLwzjeWKMUnFcabZLQdZ2z0ky82zk7Qr6KnaSMP0tJDqmqtZuset4iNq+2lYE3XMFQd25OECQQCVeAZuyeI33fgh6enl9U7YoJoye0JtHaQKF6MVOVrfN/9rvpeE3RS95E/t8sSStan8IS36JvZVB2u7e95l44CBAkEA1Tyu4ULAP20MGb8TLx4MEZRex0VWPuG987MGC7+WJzpfcEPETIBQrfceMbdzpYfUYFLqQrQLIYMPVZUNaBR5IQJBAIT2F2rYhjdCaufoSFx7Ip+MBn9frJCabIFZ04Ye1lp5WurCydC0Ri5B+mRmsDz+A2+5KEg9/qVXC5vlLcqfXYECQH77xj2FjxWJJR3j2Dwxdq9XTFzVwVjFxqoH25K9YvpFj1cfJc3SdSKxUs8++i2KMIuWugSSA0VCGSQG4GwNWKs=";

    /** *//**
     * 加密算法RSA
     */
    public static final String KEY_ALGORITHM = "RSA";


    /** *//**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;



    /** *//**
     * <p>
     * 公钥加密
     * </p>
     *
     * @param data 源数据
     * @param publicKey 公钥(BASE64编码)
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPublicKey(byte[] data, String publicKey)
            throws Exception {
        byte[] keyBytes = Base64Util.decodeString(publicKey);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicK = keyFactory.generatePublic(x509KeySpec);
        // 对数据加密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, publicK);
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        return encryptedData;
    }

    public static void main(String[] args) throws Exception {
    	Map<String, Object> keys = RSAUtils.genKeyPair();
    	System.out.println(keys);
        String source = "helloiechenyb";
        System.out.println("原文字：\r\n" + source);
        byte[] data = source.getBytes();
        byte[] encodedData = RSAUtils.encryptByPrivateKey(data, RSAUtils.getPrivateKey(keys));
        System.out.println("私钥加密后：\r\n" + new String(encodedData));
        String base64EncodedData = Base64Util.encodeByte(encodedData);
        System.out.println("二次加密后BASE64：\r\n" + base64EncodedData);
        System.out.println("二次加密后BASE64：\r\n" + new BASE64Encoder().encode(encodedData));
    
       /* String orign = new String(RSAUtils.
        		decryptByPrivateKey(base64EncodedData.getBytes(), RSAUtils.getPrivateKey(keys)));
        System.out.println("解密后："+orign);*/
    }
}