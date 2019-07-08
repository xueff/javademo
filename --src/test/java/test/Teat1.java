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
        int i=0;
        while (true){
            System.out.println(i++);
        }
//        String str = "{\n" +
//                "  \"type\": \"FILE\",\n" +
//                "  \"header\": {\n" +
//                "    \"EXTRATYPE\": \"WRITE\"\n" +
//                "  },\n" +
//                "  \"body\": {\n" +
//                "    \"path\": \"/.dev/47b9ab80559cce18bb3217129278f9f6/000c43131005\",\n" +
//                "    \"cmd\": {\n" +
//                "      \"cmdName\": \"SendWhiteList\",\n" +
//                "      \"cmdDetails\":{\n" +
//                "        \"deviceList\":[\n" +
//                "          {\n" +
//                "            \"deviceId\":\"asdasd\",\n" +
//                "            \"install_code\":\"asdasda\"\n" +
//                "          }\n" +
//                "          \n" +
//                "        ]\n" +
//                "      }\n" +
//                "    }\n" +
//                "  }\n" +
//                "}";
//
//        JsonObject json = new JsonObject(str);
//        StringBuffer s = new StringBuffer();
//        String code = getJsonObjectCode(json.getMap(),s).toString();
//        System.out.println(code);
//
//
//
//        JsonObject jsonout = new JsonObject().put("type","FILE").put("header",new JsonObject().put("EXTRATYPE","WRITE")).put("body",new JsonObject().put("path","/.dev/47b9ab80559cce18bb3217129278f9f6/000c43131005").put("cmd",new JsonObject().put("cmdName","SendWhiteList").put("cmdDetails",new JsonObject().put("deviceList",new JsonArray().add(new JsonObject().put("deviceId","asdasd").put("install_code","asdasda"))))));
//
//        System.out.println(jsonout.toString());

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

