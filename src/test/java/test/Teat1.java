package test;

import io.vertx.core.json.JsonObject;
import javabase.ramdon.RamdonStudy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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

    }
}

