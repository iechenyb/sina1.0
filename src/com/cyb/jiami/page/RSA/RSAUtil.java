package com.cyb.jiami.page.RSA;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2018年8月24日
 */
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.DSAPublicKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

import javax.crypto.Cipher;

import com.cyb.jiami.ConvertUtil;  
  
/** 
 * RSA 工具类。提供加密，解密，生成密钥对等方法。 
 * 需要到http://www.bouncycastle.org下载bcprov-jdk14-123.jar。 
 *  
 */  
public class RSAUtil {  
      
    private static String RSAKeyStore = "d:/data/rsa/RSAKey.txt";  
    /** 
     * * 生成密钥对 * 
     *  
     * @return KeyPair * 
     * @throws EncryptException 
     */  
    public static KeyPair generateKeyPair(String rsaKeyStore) throws Exception {  
        try {  
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA",  
                    new org.bouncycastle.jce.provider.BouncyCastleProvider());  
            final int KEY_SIZE = 1024;// 没什么好说的了，这个值关系到块加密的大小，可以更改，但是不要太大，否则效率会低  
            keyPairGen.initialize(KEY_SIZE, new SecureRandom());  
            KeyPair keyPair = keyPairGen.generateKeyPair();  
            System.out.println(keyPair.getPrivate());  
            System.out.println(keyPair.getPublic());  
            saveKeyPair(keyPair,rsaKeyStore);  
            return keyPair;  
        } catch (Exception e) {  
            throw new Exception(e.getMessage());  
        }  
    }  
  
    public static KeyPair getKeyPair(String rsaKeyStore) throws Exception {  
        FileInputStream fis = new FileInputStream(rsaKeyStore);  
        ObjectInputStream oos = new ObjectInputStream(fis);  
        KeyPair kp = (KeyPair) oos.readObject();  
        oos.close();  
        fis.close();  
        return kp;  
    }  
  
    public static void saveKeyPair(KeyPair kp,String rsaKeyStore) throws Exception {  
  
        FileOutputStream fos = new FileOutputStream(rsaKeyStore);  
        ObjectOutputStream oos = new ObjectOutputStream(fos);  
        // 生成密钥  
        oos.writeObject(kp);  
        oos.close();  
        fos.close();  
    }  
  
    /** 
     * * 生成公钥 * 
     *  
     * @param modulus * 
     * @param publicExponent * 
     * @return RSAPublicKey * 
     * @throws Exception 
     */  
    public static RSAPublicKey generateRSAPublicKey(byte[] modulus,  
            byte[] publicExponent) throws Exception {  
        KeyFactory keyFac = null;  
        try {  
            keyFac = KeyFactory.getInstance("RSA",  
                    new org.bouncycastle.jce.provider.BouncyCastleProvider());  
        } catch (NoSuchAlgorithmException ex) {  
            throw new Exception(ex.getMessage());  
        }  
  
        RSAPublicKeySpec pubKeySpec = new RSAPublicKeySpec(new BigInteger(  
                modulus), new BigInteger(publicExponent));  
        try {  
            return (RSAPublicKey) keyFac.generatePublic(pubKeySpec);  
        } catch (InvalidKeySpecException ex) {  
            throw new Exception(ex.getMessage());  
        }  
    }  
  
    /** 
     * * 生成私钥 * 
     *  
     * @param modulus * 
     * @param privateExponent * 
     * @return RSAPrivateKey * 
     * @throws Exception 
     */  
    public static RSAPrivateKey generateRSAPrivateKey(byte[] modulus,  
            byte[] privateExponent) throws Exception {  
        KeyFactory keyFac = null;  
        try {  
            keyFac = KeyFactory.getInstance("RSA",  
                    new org.bouncycastle.jce.provider.BouncyCastleProvider());  
        } catch (NoSuchAlgorithmException ex) {  
            throw new Exception(ex.getMessage());  
        }  
  
        RSAPrivateKeySpec priKeySpec = new RSAPrivateKeySpec(new BigInteger(  
                modulus), new BigInteger(privateExponent));  
        try {  
            return (RSAPrivateKey) keyFac.generatePrivate(priKeySpec);  
        } catch (InvalidKeySpecException ex) {  
            throw new Exception(ex.getMessage());  
        }  
    }  
  
    /** 
     * * 加密 * 
     *  
     * @param key 
     *            加密的密钥 * 
     * @param data 
     *            待加密的明文数据 * 
     * @return 加密后的数据 * 
     * @throws Exception 
     */  
    public static byte[] encrypt(PublicKey pk, byte[] data) throws Exception {  
        try {  
            Cipher cipher = Cipher.getInstance("RSA",  
                    new org.bouncycastle.jce.provider.BouncyCastleProvider());  
            cipher.init(Cipher.ENCRYPT_MODE, pk);  
            int blockSize = cipher.getBlockSize();// 获得加密块大小，如：加密前数据为128个byte，而key_size=1024  
            // 加密块大小为127  
            // byte,加密后为128个byte;因此共有2个加密块，第一个127  
            // byte第二个为1个byte  
            int outputSize = cipher.getOutputSize(data.length);// 获得加密块加密后块大小  
            int leavedSize = data.length % blockSize;  
            int blocksSize = leavedSize != 0 ? data.length / blockSize + 1  
                    : data.length / blockSize;  
            byte[] raw = new byte[outputSize * blocksSize];  
            int i = 0;  
            while (data.length - i * blockSize > 0) {  
                if (data.length - i * blockSize > blockSize)  
                    cipher.doFinal(data, i * blockSize, blockSize, raw, i  
                            * outputSize);  
                else  
                    cipher.doFinal(data, i * blockSize, data.length - i  
                            * blockSize, raw, i * outputSize);  
                // 这里面doUpdate方法不可用，查看源代码后发现每次doUpdate后并没有什么实际动作除了把byte[]放到  
                // ByteArrayOutputStream中，而最后doFinal的时候才将所有的byte[]进行加密，可是到了此时加密块大小很可能已经超出了  
                // OutputSize所以只好用dofinal方法。  
  
                i++;  
            }  
            return raw;  
        } catch (Exception e) {  
            throw new Exception(e.getMessage());  
        }  
    }  
  
    /** 
     * * 解密 * 
     *  
     * @param key 
     *            解密的密钥 * 
     * @param raw 
     *            已经加密的数据 * 
     * @return 解密后的明文 * 
     * @throws Exception 
     */  
    public static byte[] decrypt(PrivateKey pk, byte[] raw) throws Exception {  
        try {  
            Cipher cipher = Cipher.getInstance("RSA",  
                    new org.bouncycastle.jce.provider.BouncyCastleProvider());  
            cipher.init(cipher.DECRYPT_MODE, pk);  
            int blockSize = cipher.getBlockSize();  
            ByteArrayOutputStream bout = new ByteArrayOutputStream(64);  
            int j = 0;  
  
            while (raw.length - j * blockSize > 0) {  
                bout.write(cipher.doFinal(raw, j * blockSize, blockSize));  
                j++;  
            }  
            return bout.toByteArray();  
        } catch (Exception e) {  
            throw new Exception(e.getMessage());  
        }  
    }  
  
    /** 
     * * * 
     *  
     * @param args * 
     * @throws Exception 
     * https://blog.csdn.net/zengdejie123/article/details/69568981
     */  
    public static void main(String[] args) throws Exception {  
       // RSAPublicKey rsap = (RSAPublicKey) RSAUtil5.generateKeyPair().getPublic();  
    	String rsaKeyStore = RSAKeyStore;//"d:/data/rsa/"+"aaa"+".key";
    	decRequest(rsaKeyStore);
    	String test = "helloworld";  
    	//BCRSAPrivateCrtKey x;
    	System.out.println(getKeyPair(rsaKeyStore).getPrivate());
    	
    	System.out.println(getKeyPair(rsaKeyStore).getPublic());
    	
        /*String pubk = new String (getKeyPair(rsaKeyStore).getPublic().getEncoded());
        System.out.println("公钥信息："+(RSAPublicKey)getKeyPair(rsaKeyStore).getPublic());
        System.out.println("\tpublic key:"+pubk);//注意model作为参数给前台
        String hexstring=ConvertUtil.stringToHexString(pubk);
        System.out.println(hexstring);
        String prik = new String (getKeyPair(rsaKeyStore).getPrivate().getEncoded());
        System.out.println("\tprivate key:"+prik);
        byte[] en_test = encrypt(getKeyPair(rsaKeyStore).getPublic(), test.getBytes());  
        System.out.println("\n公钥加密："+new String(en_test)); 
        System.out.println("\n公钥加密："+ConvertUtil.stringToHexString(prik));
        byte[] de_test = decrypt(getKeyPair(rsaKeyStore).getPrivate(), en_test);  
        System.out.println(new String(de_test));  
        decRequest(rsaKeyStore);
        String mo = "8246a46f44fc4d961e139fd70f4787d272d374532f4d2d9b7cbaad6a15a8c1301319aa6b3f30413b859351c71938aec516fa7147b69168b195e81df46b6bed7950cf3a1c719d42175f73d7c97a85d7d20a9e83688b92f05b3059bb2ff75cd7190a042cd2db97ebc2ab4da366f2a7085556ed613b5a39c9fdd2bb2595d1dc23b5";
        getModulus((RSAPublicKey)getKeyPair(rsaKeyStore).getPublic());
        //如何将生成的公钥和秘钥写入文件！
        
        RSAPublicKey pk = generateRSAPublicKey(mo.getBytes(),"10001".getBytes());
        System.out.println("生成公钥："+pk);//不对
        
        System.out.println("根据m和e生成公钥："+getPublicKey(mo,"10001"));
        en_test = encrypt(getPublicKey(mo,"10001"), test.getBytes());  
        System.out.println("\n公钥加密："+new String(en_test));*/
    }  
    /**
     * 32132!1321321gfedcba
     * @ #不行
     *作者 : iechenyb<br>
     *方法描述: 说点啥<br>
     *创建时间: 2017年7月15日
     *@param rsaKeyStore
     *@throws Exception
     */
    public static void decRequest(String rsaKeyStore) throws Exception{
    	String encodeStr = "896f38a20a21888c63b7ea15e431564345c86da06e9e0219ebd4afdb5086daddf36a41648b33edf14cb2f8fc15a7e59dafbd4421ad59085e8000e8c93ff7af24960eb1d59353ef6bef662eaba2649cf930dbdc628ba240336a02d775474f5fde732c121bd7674dcd199bf6e367a958f3d1d2239668c9f85cd66767d929788a5c";
    	byte[] en_result = new BigInteger(encodeStr, 16).toByteArray();
    	byte[] de_result = decrypt(getKeyPair(rsaKeyStore).getPrivate(),
				en_result);
    	//倒叙输出123456789-> 987654321
    	System.out.println("将请求进行解密:"+new String(de_result));
    }
    
    
    public static String getModulus(RSAPublicKey publickey) throws NoSuchAlgorithmException, InvalidKeySpecException{
    	String algorithm = publickey.getAlgorithm(); // 获取算法 
    	String modulus = publickey.getModulus().toString();
    	System.out.println("modulus:"+modulus);
    	KeyFactory keyFact = KeyFactory.getInstance(algorithm); 
    	BigInteger prime = null; 
    	BigInteger exponent = null;
    	if ("RSA".equals(algorithm)) { // 如果是RSA加密 
    	    RSAPublicKeySpec keySpec = (RSAPublicKeySpec)keyFact.getKeySpec(publickey, RSAPublicKeySpec.class); 
    	    prime = keySpec.getModulus(); 
    	    exponent = keySpec.getPublicExponent();
    	} else if ("DSA".equals(algorithm)) { // 如果是DSA加密 
    	    DSAPublicKeySpec keySpec = (DSAPublicKeySpec)keyFact.getKeySpec(publickey, DSAPublicKeySpec.class); 
    	    prime = keySpec.getP(); 
    	} 
    	int len = prime.toString(2).length(); // 转换为二进制，获取公钥长度
    	BigInteger bigIntModulus = new BigInteger(prime.toString(),16);
    	//System.out.println("16进制数据："+bigIntModulus);
    	System.out.println("16进制数据："+prime);
    	System.out.println("公钥模量:"+exponent);
		return prime.toString();
    }
    
    public static PublicKey getPublicKey(String modulus, String publicExponent)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        BigInteger bigIntModulus = new BigInteger(modulus,16);
        BigInteger bigIntPrivateExponent = new BigInteger(publicExponent,16);
        RSAPublicKeySpec keySpec = new RSAPublicKeySpec(bigIntModulus, bigIntPrivateExponent);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(keySpec);
        return publicKey;
    }
}  