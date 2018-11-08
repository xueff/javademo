package javabase.ramdon;

import java.util.Random;

/**
 * @author xuefei
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/10/1911:50
 */
public class RamdonStudy {
    public static int getRamdonInt(int max){
        Random rand =new Random();
        return rand.nextInt(max);
    }


    // 生成的字符串每个位置都有可能是STR中的一个字母或数字，需要导入的包是import java.util.Random;
    //length用户要求产生字符串的长度
    public static String getRandomString(int length) {
        //62
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(length);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    public static double getRamdonDouble(){
        Random rand =new Random();
        return rand.nextDouble();
    }

}
