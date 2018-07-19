package com.cyb.jiami;
import java.math.BigInteger;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2018年7月19日
 */
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

public class RSADemo {
    public RSADemo() {
    }
    PublicKey pbkey;
    PrivateKey prkey;
    public void generateKey() {
        try {
            KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
            kpg.initialize(1024);
            KeyPair kp = kpg.genKeyPair();
            pbkey = kp.getPublic();
            prkey = kp.getPrivate();
        } catch (Exception e) {
        }
    }

    //加密，需要公钥
    public byte[] encrypt(byte[] ptext) throws Exception {
        // 获取公钥及参数e,n
        RSAPublicKey pbk = (RSAPublicKey)pbkey;
        BigInteger e = pbk.getPublicExponent();
        BigInteger n = pbk.getModulus();
        // 获取明文m
        BigInteger m = new BigInteger(ptext);
        // 计算密文c
        BigInteger c = m.modPow(e, n);
        return c.toByteArray();
    }

    //使用私钥进行解密
    public byte[] decrypt(byte[] ctext) throws Exception {
        // 读取密文
        BigInteger c = new BigInteger(ctext);
        // 读取私钥
        RSAPrivateKey prk = (RSAPrivateKey)prkey;
        BigInteger d = prk.getPrivateExponent();

        // 获取私钥参数及解密
        BigInteger n = prk.getModulus();
        BigInteger m = c.modPow(d, n);

        // 显示解密结果
        byte[] mt = m.toByteArray();
        return mt;
    }
    public static void main(String args[]) 
    {
        try {
            RSADemo rsa=new RSADemo();
            rsa.generateKey();
            byte[] data=rsa.encrypt("iechenyb中文测试".getBytes());
            
            System.out.println(new String(Base64Util.encode(data)));
            System.out.println("公钥加密："+new String(data));
            byte[] data1=rsa.decrypt(data);
            String str=new String(data1);
            System.out.println("私钥解密："+str);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
