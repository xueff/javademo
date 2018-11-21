package test;

import common.bean.Person;
import io.netty.util.internal.SystemPropertyUtil;
import io.vertx.core.json.JsonObject;
import net.sf.json.JSONObject;
import javabase.ramdon.RamdonStudy;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @author xuefei
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/10/239:15
 */
public class Teat {
    public static void main(String[] args) {

        boolean windows = SystemPropertyUtil.get("os.name", "").toLowerCase(Locale.US).contains("win");


        long st = System.currentTimeMillis();
        long flag = 0L;
        String b= "12345678901234567890123467890";
        for(long i=0;i<10000L;i++){
            try {
                subString(b);
            }catch (Exception e){
//                e.printStackTrace();
            }
        }
        long two = System.currentTimeMillis();
        b= "1";
        for(long i=0;i<100000L;i++){
            try {
                subString(b);
            }catch (Exception e){
//                flag++;
            }
        }
//        for(long i=0;i<10000000L;i++){
//            b.substring(2);
//        }
        long sec = System.currentTimeMillis();
        System.out.println("A"+(two-st));
        System.out.println("A"+(sec-two));

    }

    private static String subString(String str){
        String sb = str.substring(1);
        return subString(sb);
    }
}
