package javabase.secret;

import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
/**
 * AES工具
 */
public class AesUtil {
    private final static String secretKey = "123456789012345";
    private final static String encoding = "UTF-8";
    /**
     * AES加密为base 64 code
     *
     * @param content
     *            待加密的内容
     * @return 加密后的base 64 code
     * @throws Exception
     */
    public static String aesEncrypt(String content) throws Exception {
        return encryptAES(content, secretKey);
    }
    /**
     * 将base 64 code AES解密
     *
     * @param encryptStr
     *            待解密的base 64 code
     * @return 解密后的string
     * @throws Exception
     */
    public static String aesDecrypt(String encryptStr) throws Exception {
        return decrypt(encryptStr, secretKey);
    }
    /**
     * AES加密
     *
     * @param content
     * @param password
     * @return
     * @throws Exception
     */
    public static String encryptAES(String content, String password)
            throws Exception {
        byte[] encryptResult = encrypt(content, password);
        String encryptResultStr = parseByte2HexStr(encryptResult);
        // BASE64位加密
        encryptResultStr = ebotongEncrypto(encryptResultStr);
        return encryptResultStr;
    }
    /**
     * AES解密
     *
     * @param encryptResultStr
     * @param password
     * @return
     * @throws Exception
     */
    public static String decrypt(String encryptResultStr, String password)
            throws Exception {
        // BASE64位解密
        String decrpt = ebotongDecrypto(encryptResultStr);
        byte[] decryptFrom = parseHexStr2Byte(decrpt);
        byte[] decryptResult = decrypt(decryptFrom, password);
        return new String(decryptResult);
    }
    /**
     * AES解密
     *
     * @param encryptResultStr
     * @param password
     * @return
     * @throws Exception
     */
    public static String decrypt(String encryptResultStr) throws Exception {
        // BASE64位解密
        String decrpt = ebotongDecrypto(encryptResultStr);
        byte[] decryptFrom = parseHexStr2Byte(decrpt);
        byte[] decryptResult = decrypt(decryptFrom, secretKey);
        return new String(decryptResult);
    }
    /**
     * 加密字符串
     */
    public static String ebotongEncrypto(String str) {
        BASE64Encoder base64encoder = new BASE64Encoder();
        String result = str;
        if (str != null && str.length() > 0) {
            try {
                byte[] encodeByte = str.getBytes(encoding);
                result = base64encoder.encode(encodeByte);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // base64加密超过一定长度会自动换行 需要去除换行符
        return result.replaceAll("\r\n", "").replaceAll("\r", "")
                .replaceAll("\n", "");
    }
    /**
     * 解密字符串
     */
    public static String ebotongDecrypto(String str) throws Exception {
        BASE64Decoder base64decoder = new BASE64Decoder();
        byte[] encodeByte = base64decoder.decodeBuffer(str);
        return new String(encodeByte, encoding);
    }
    /**
     * 加密
     *
     * @param content
     *            需要加密的内容
     * @param password
     *            加密密码
     * @return
     */
    private static byte[] encrypt(String content, String password)
            throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        // 防止linux下 随机生成key
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        secureRandom.setSeed(password.getBytes());
        kgen.init(128, secureRandom);
        // kgen.init(128, new SecureRandom(password.getBytes()));
        SecretKey secretKey = kgen.generateKey();
        byte[] enCodeFormat = secretKey.getEncoded();
        SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
        Cipher cipher = Cipher.getInstance("AES");// 创建密码器
        byte[] byteContent = content.getBytes(encoding);
        cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
        byte[] result = cipher.doFinal(byteContent);
        return result; // 加密
    }
    /**
     * 解密
     *
     * @param content
     *            待解密内容
     * @param password
     *            解密密钥
     * @return
     */
    private static byte[] decrypt(byte[] content, String password)
            throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        // 防止linux下 随机生成key
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        secureRandom.setSeed(password.getBytes());
        kgen.init(128, secureRandom);
        // kgen.init(128, new SecureRandom(password.getBytes()));
        SecretKey secretKey = kgen.generateKey();
        byte[] enCodeFormat = secretKey.getEncoded();
        SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
        Cipher cipher = Cipher.getInstance("AES");// 创建密码器
        cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
        byte[] result = cipher.doFinal(content);
        return result; // 加密
    }
    /**
     * 将二进制转换成16进制
     *
     * @param buf
     * @return
     */
    public static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }
    /**
     * 将16进制转换为二进制
     *
     * @param hexStr
     * @return
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2),
                    16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }
    public static void main(String[] args) throws Exception {
        // String content = Calendar.getInstance().getTimeInMillis();
        // System.out.println(aesEncrypt(content));
        // System.out.println(aesDecrypt(aesEncrypt(content)));
        // Thread.sleep(100);
        // content = Calendar.getInstaance().getTime().toLocaleString();
        // System.out.println(aesEncrypt(content));
        // System.out.println(aesDecrypt(aesEncrypt(content)));
        System.out.println(aesEncrypt("1234567890"));
        System.out.println(aesDecrypt( "MkY1NEFCQUUwQzJFQzg5OTQ2RDVBMEVEOEYwQkI5REE=") );
    }
}
