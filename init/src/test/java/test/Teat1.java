package test;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import net.sf.json.JSONObject;
import org.junit.Test;
import utils.NumberUtils;

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
        Optional<EventModel>  optional = ports.stream().filter(s->s.getPort().equals(port)).findFirst();

    }

    @Test
    public static  StringBuffer getJsonObjectCode(Map<String, Object> map,StringBuffer jsonCode){
        Iterator<Map.Entry<String, Object>> it = map.entrySet().iterator();
        jsonCode.append("new JsonObject()");
        while(it.hasNext()){
            Object o = it.next();
            String key = (String)((Map.Entry) o).getKey();
            Object value = ((Map.Entry) o).getValue();
            StringBuffer valueCode = new StringBuffer();
            if(value instanceof Number){
                jsonCode.append(".put(\""+key+"\","+value);
            }else if(value instanceof String){
                jsonCode.append(".put(\""+key+"\",\""+value+"\"");
            }else if(value instanceof Map){
                getJsonObjectCode((Map<String, Object>) value,jsonCode.append(".put(\""+key+"\","));
            }else if(value instanceof List){
                getJsonArrayCode((List<Object>) value,jsonCode.append(".put(\""+key+"\","));
            }
            jsonCode.append(")");
        }
        return  jsonCode;
    }

    @Test
    public static  StringBuffer getJsonArrayCode(List<Object> list,StringBuffer jsonCode) {
        jsonCode.append("new JsonArray()");
        for (Object o : list) {
            if (o instanceof Number) {
                jsonCode.append(".add(" + 0 );
            } else if (o instanceof String) {
                jsonCode.append(".add(\"" + o + "\"");
            } else if (o instanceof Map) {
                getJsonObjectCode((Map<String, Object>) o, jsonCode.append(".add("));
            }
            jsonCode.append(")");
        }

        return jsonCode;

    }


}

