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
        for (int i = 0; i < 1000; i++) {

            HttpClientUtilJson.httpPostRaw("http://localhost:9090/dynamic/policy/distribute/enable", "[24,32,33,34,35,36,37,38,42]", null, null);

        }
    }
}

