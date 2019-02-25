package plug.getcode;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import net.sf.json.JSONObject;
import org.junit.Test;
import utils.NumberUtils;

import java.util.*;

/**
 * @author xuefei
 * @Title:  vertx Json 代码自动生成
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/10/239:15
 */

public class JsonObjectCode {
    public static void main(String[] args) {
        JsonArray json = new JsonArray(" [\n" +
                "        {\n" +
                "            \"path\": \"/.dentry/47b9ab80559cce18bb3217129278f001/000/灯\",\n" +
                "            \"cmdName\": \"TurnOn\",\n" +
                "            \"cmd\": {\n" +
                "              \"cmdDetails\": {\n" +
                "                \n" +
                "              }\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"path\": \"/.dentry/47b9ab80559cce18bb3217129278f001/000/门磁\",\n" +
                "            \"cmdName\": \"TurnOn\",\n" +
                "            \"cmd\": {\n" +
                "              \n" +
                "            }\n" +
                "          \n" +
                "        }\n" +
                "      ]");
        StringBuffer jsonCode = new StringBuffer("JsonObject json = ");
        jsonCode = getJsonArrayCode(json.getList(),jsonCode);
        jsonCode.append(";");
        System.out.println(jsonCode);


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


