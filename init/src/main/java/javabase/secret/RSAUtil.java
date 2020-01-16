package javabase.secret;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.crypto.Cipher;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.CertificateFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * 正常：公钥加密私钥解密   but是双向的，都可以加解密
  */

public class RSAUtil {
    private static final Logger LOGGER= LoggerFactory.getLogger(RSAUtil.class);
    /**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;
    /**
     * RSA最大解密密文大小（如果秘钥是1024bit,解密最大块是128,如果秘钥是2048bit,解密最大块是256）
     */
    private static final int MAX_DECRYPT_BLOCK = 128;
    private static PrivateKey globalPrivateKey = null;
    private static PublicKey globalPublicKey = null;

    public static PrivateKey getPrivateKey(String prikeypath) throws Exception {
        if(globalPrivateKey != null){
            return globalPrivateKey;
        }
        InputStream in = RSAUtil.class.getClassLoader().getResourceAsStream(prikeypath);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        String readLine = null;

        while((readLine = br.readLine()) != null) {
            if(readLine.charAt(0) != 45) {
                sb.append(readLine);
                sb.append('\r');
            }
        }

        in.close();
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(sb.toString()));
        try {
            KeyFactory factory = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = factory.generatePrivate(pkcs8EncodedKeySpec);
            globalPrivateKey = privateKey;
            return globalPrivateKey;
        } catch (Exception e) {
            LOGGER.error("获取私钥异常",e);
            throw e;
        }
    }

    public static PublicKey getPublicByPublic(String keypath) throws Exception {
        if(globalPublicKey != null){
            return globalPublicKey;
        }
        InputStream in = RSAUtil.class.getClassLoader().getResourceAsStream(keypath);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        String readLine = null;

        while((readLine = br.readLine()) != null) {
            if(readLine.charAt(0) != 45) {
                sb.append(readLine);
                sb.append('\r');
            }
        }

        in.close();
        CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
        X509EncodedKeySpec ks = new X509EncodedKeySpec(Base64.decodeBase64(sb.toString()));
        KeyFactory kf = KeyFactory.getInstance("RSA");
        globalPublicKey = kf.generatePublic(ks);
        return globalPublicKey;
    }


    /**
     * 从字符串中加载公钥
     *
     * @param publicKeyStr
     *            公钥数据字符串
     * @throws Exception
     *             加载公钥时产生的异常
     */
    public static PublicKey loadPublicKey(String publicKeyStr) throws Exception
    {
        try
        {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyStr.getBytes());
            return (RSAPublicKey) keyFactory.generatePublic(keySpec);
        } catch (NoSuchAlgorithmException e)
        {
            throw new Exception("无此算法");
        } catch (InvalidKeySpecException e)
        {
            throw new Exception("公钥非法");
        } catch (NullPointerException e)
        {
            throw new Exception("公钥数据为空");
        }
    }

    /**
     * 从字符串中加载私钥<br>
     * 加载时使用的是PKCS8EncodedKeySpec（PKCS#8编码的Key指令）。
     *
     * @param privateKeyStr
     * @return
     * @throws Exception
     */
    public static PrivateKey loadPrivateKey(String privateKeyStr) throws Exception
    {
        try
        {
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyStr.getBytes());
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
        } catch (NoSuchAlgorithmException e)
        {
            throw new Exception("无此算法");
        } catch (InvalidKeySpecException e)
        {
            throw new Exception("私钥非法");
        } catch (NullPointerException e)
        {
            throw new Exception("私钥数据为空");
        }
    }



    public static String dencrypt(String str,String privateKeyStr) throws Exception{
        //64位解码加密后的字符串
        byte[] encryptedData = Base64.decodeBase64(str.getBytes("UTF-8"));
        PrivateKey privateKey = getPrivateKey(privateKeyStr);
        //RSA加密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        int inputLen = encryptedData.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段解密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        String outStr = new String(decryptedData,"UTF-8");
        return outStr;
    }
    public static String dencryptByPrivateKey(String str,String privateKey) throws Exception{
        //64位解码加密后的字符串
        byte[] encryptedData = Base64.decodeBase64(str.getBytes("UTF-8"));

        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKey.toString()));
        try {
            KeyFactory factory = KeyFactory.getInstance("RSA");
            globalPrivateKey =  factory.generatePrivate(pkcs8EncodedKeySpec);
        } catch (Exception e) {
            LOGGER.error("获取私钥异常",e);
            throw e;
        }
        //RSA加密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, globalPrivateKey);
        int inputLen = encryptedData.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段解密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        String outStr = new String(decryptedData,"UTF-8");
        return outStr;
    }

    public static String encrypt(String data, String publicKeyPath) throws Exception{
        PublicKey pubKey = getPublicByPublic(publicKeyPath);
        //RSA加密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        byte[] dataByte = data.getBytes("UTF-8");
        int inputLen = dataByte.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(dataByte, offSet, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(dataByte, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();

        String outStr = Base64.encodeBase64String(encryptedData);
        return outStr;
    }
    public static String encryptByPublicKey(String data, String publicKey) throws Exception{
        CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
        X509EncodedKeySpec ks = new X509EncodedKeySpec(Base64.decodeBase64(publicKey));
        KeyFactory kf = KeyFactory.getInstance("RSA");
        PublicKey pubKey = kf.generatePublic(ks);
        //RSA加密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        byte[] dataByte = data.getBytes("UTF-8");
        int inputLen = dataByte.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(dataByte, offSet, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(dataByte, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();

        String outStr = Base64.encodeBase64String(encryptedData);
        return outStr;
    }

    public static void main(String[] args) throws Exception {
        //现在生成公私钥对,http://web.chacuo.net/netrsakeypair
        String data = "我是打酱油的";
        //使用公钥加密,第二个参数classpath下的公钥文件
        data = encryptByPublicKey(data,"MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCwnJPPrOCD3OQ9M38vkRE2+pjWQlu9O52jtb7+706RF3/YCzaTj6Ds8BU6op8BCnWVNzzwsfwHh7IKxYrlHNaDc6zhBF+5+5OT8hxdkotxsa7ZWJysP7OoN/62Ih0D/EBzb31QqH9dux7NrD0GdtcJdvtz2BJDGNJjjMsnq1R3DQIDAQAB");
        System.out.println("加密后的数据:"+data);
        //使用私钥解密,第二个参数classpath下的私钥文件
        data = dencryptByPrivateKey(data,"MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBALCck8+s4IPc5D0zfy+RETb6mNZCW707naO1vv7vTpEXf9gLNpOPoOzwFTqinwEKdZU3PPCx/AeHsgrFiuUc1oNzrOEEX7n7k5PyHF2Si3GxrtlYnKw/s6g3/rYiHQP8QHNvfVCof127Hs2sPQZ21wl2+3PYEkMY0mOMyyerVHcNAgMBAAECgYBHW/ydPMy6etX9pIEPRx4diMOYTteibiQTsauqdibYmRYexOkYVkA/fAIX3v0//mnkEJ2e40th/4IAuDCWwkAp0O9xOBIOcv8N+s3lnj0TSDl2V8/OL5zz9YiCGcpxWhe7B5icIQUA5bfHRJ7hul0LuEHgM6EsE2w5uFUu3xJcQQJBANzXSx1l9kS/DVOOZnbqmDQw7WcR5Hlz47l+SyrYKh8Dgk14IpfPprMNQ5HGkZQjwl5pRP13EDNo9HToNZV7/C8CQQDMuqZhc+afpuKPb30Zvv99rZPqrTd2BAaFCE9o/oRveuuSIUvTa8pq+0tWQ/f3mCZAgJ9BTWVu2DbmPnK9goWDAkEAsMBrsTLbQTh8TQTrA4BNo3AJgnXynDZh7COlu7vZMotXbfOaFFExRZt3GiSIfb2FIpU0j30M7WiyRyNXR7Y9JwJBAIhzXrFVLkD6WcZ/6EFgq6h4KD4hdPWTeUTxZPv1pw8mPhwHVSJ0lVT4aB327y52mggjsgE0NIbMVMrglFj6xUECQQDUqNozdWYffjsNogEMTEwPwUKrO7wWiXbCzB/SEWd2AUFLlHeuSmKPKAR7CP21XyPTH6FGZtwFeF6VCBilYxGx");
        System.out.println("解密后的数据:"+data);
    }
}

