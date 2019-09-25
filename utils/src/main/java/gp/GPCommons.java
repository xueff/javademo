package gp;

import com.common.utils.JSoupUtils;
import io.vertx.core.json.JsonObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author xuefei
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/10/239:15
 */
public class GPCommons {

    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println(getShenZhen());
    }


    public static Map<String,String> getShangHai() throws IOException, InterruptedException {

        Map<String,String> map = new HashMap<>();

        for(int i=1;;i++) {

            String url = "http://70.push2.eastmoney.com/api/qt/clist/get?pn="+i+"&pz=2000&fs=m:1+t:2&fields=f12,f14";

            String res = JSoupUtils.getJson(url);
            JsonObject json = new JsonObject(res);
            JsonObject data = json.getJsonObject("data");
            if(data != null) {
                JsonObject items = data.getJsonObject("diff");
                if (items.size() > 0) {
                    for (int j = 0; j <= items.size(); j++) {
                        JsonObject item = items.getJsonObject(j + "");
                        if (item != null) {
                            map.put(item.getString("f12"), item.getString("f14"));
                        }
                    }

                } else {
                    break;
                }

            }else {
                break;
            }
        }
        System.out.println(map.size());
        return map;
    }

    public static Map<String,String> getShenZhen() throws IOException, InterruptedException {

        Map<String,String> map = new HashMap<>();

        for(int i=1;;i++) {

            String url = "http://45.push2.eastmoney.com/api/qt/clist/get?pn="+i+"&pz=2000&fs=m:1+t:2&fields=f12,f14";

            String res = JSoupUtils.getJson(url);
            JsonObject json = new JsonObject(res);
            JsonObject data = json.getJsonObject("data");
            if(data != null) {
                JsonObject items = data.getJsonObject("diff");
                if (items.size() > 0) {
                    for (int j = 0; j <= items.size(); j++) {
                        JsonObject item = items.getJsonObject(j + "");
                        if (item != null) {
                            map.put(item.getString("f12"), item.getString("f14"));
                        }
                    }

                } else {
                    break;
                }

            }else {
                break;
            }
        }
        System.out.println(map.size());
        return map;
    }




}

