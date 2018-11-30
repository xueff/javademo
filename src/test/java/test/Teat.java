package test;

import common.bean.Person;
import io.netty.util.internal.SystemPropertyUtil;
import io.vertx.core.json.JsonObject;
import net.http.httpclient.HttpsUtils;
import net.sf.json.JSONObject;
import javabase.ramdon.RamdonStudy;

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
        Comparator<JsonObject> comparator = new Comparator<JsonObject>() {
            @Override
            public int compare(JsonObject o1, JsonObject o2) {
                return (int) (o2.getJsonObject("cmd").getLong("TimeStamp") - o1.getJsonObject("cmd").getLong("TimeStamp"));
            }
        };

        for(int i=0;i<300;i++) {
            new Thread(new Add()).start();
        }
        try {
            Thread.sleep(8);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(historyList.size());
        historyList.sort(comparator.reversed());
        System.out.println(historyList);
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



        System.out.println(historyList.size());
    }

    private static String subString(String str){
        String sb = str.substring(1);
        return subString(sb);
    }
}

class Add implements Runnable{
    @Override
    public void run() {
        for(int i=0;i<100;i++) {
            Teat.historyList.add(new JsonObject().put("cmd", new JsonObject().put("TimeStamp", RamdonStudy.getRamdonInt(10002))));
        }
    }
}

