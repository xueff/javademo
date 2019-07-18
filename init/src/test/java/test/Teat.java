package test;

import io.vertx.core.json.JsonObject;
import javabase.ramdon.RamdonStudy;
import net.http.httpclient.HttpClientUtilJson;
import net.http.httpclient.HttpClientUtils;

import java.util.*;

/**
 * @author xuefei
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/10/239:15
 */
public class Teat {
    static List<JsonObject> historyList = Collections.synchronizedList(new ArrayList<>());
    public static void main(String[] args) {
        for(int i=0;i<1000;i++) {

            HttpClientUtilJson.httpPostRaw("http://localhost:9090/dynamic/policy/distribute/enable", "[24,32,33,34,35,36,37,38,42]", null, null);

        }

//        System.out.println("st");
//        for (int i = 0; i < 5000; i++) {
//            System.out.println("('192.168.101.203', '2181', 'Simple', '/opt/idss/keytab/1545802006067_', '', '1', '2018-12-26 13:26:46', 'admin"+i+"', NULL, NULL, '192.168.101.203')," );
////            System.out.println(" ( 'hive"+i+"', 'jdbc:hive2://172.16.0.139:10000/default', '172.16.0.139 ', '10000', 'admindddddd', '!QAZXSW@3', 'Simple', '', '', NULL, '1', '1', '2019-07-15 06:01:52', 'admin', '2019-07-15 06:10:06', 'admin'),");
//
//        }
//        System.out.println("ed");
    }










//
//        Comparator<JsonObject> comparator = new Comparator<JsonObject>() {
//            @Override
//            public int compare(JsonObject o1, JsonObject o2) {
//                return (int) (o2.getJsonObject("cmd").getLong("TimeStamp") - o1.getJsonObject("cmd").getLong("TimeStamp"));
//            }
//        };
//
//        for(int i=0;i<300;i++) {
//            new Thread(new Add()).start();
//        }
//        try {
//            Thread.sleep(8);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println(historyList.size());
//        historyList.sort(comparator.reversed());
//        System.out.println(historyList);
//        try {
//            Thread.sleep(10);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//
//
//        System.out.println(historyList.size());
//    }
//
//    private static String subString(String str){
//        String sb = str.substring(1);
//        return subString(sb);
//    }
}

//class Add implements Runnable{
//    @Override
//    public void run() {
//        for(int i=0;i<100;i++) {
//            Teat.historyList.add(new JsonObject().put("cmd", new JsonObject().put("TimeStamp", RamdonStudy.getRamdonInt(10002))));
//        }
//    }
//}

