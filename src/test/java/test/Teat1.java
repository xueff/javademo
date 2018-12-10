package test;

import io.vertx.core.json.JsonObject;
import javabase.ramdon.RamdonStudy;
import org.junit.Test;

import java.util.*;

/**
 * @author xuefei
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/10/239:15
 */
public class Teat1 {
    public static void main(String[] args) {
        JsonObject jsonObject = new JsonObject().put("deviceId","66");
        String jsonString = jsonObject.toString();
        String hostMac ="0cefafd20f3a";

        if(jsonString.contains("\"deviceId\""))
            jsonString = jsonString.replace("\"deviceId\":\"","\"deviceId\": \"/.dev/47b9ab80559cce18bb3217129278f9f6/"+hostMac+"@");
        jsonObject = new JsonObject(jsonString);

        System.out.println(jsonString);

        List<String> list = new ArrayList<>();

    }

    @Test public void getMax(){
        Map<String,Integer> fbu = new HashMap<>();
        String test = "";
        String[] array = test.split("=");
        for(int i=0;i<array.length;i++){
            if(fbu.containsKey(array[i])){
                fbu.put(array[i], fbu.get(array[i])+1);
            }else{
                fbu.put(array[i], 1);
            }
        }
        System.out.println(fbu);
    }

}

