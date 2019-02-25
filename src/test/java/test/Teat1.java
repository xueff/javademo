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
        String str = "{\n" +
                "  \"type\": \"Scene\",\n" +
                "  \"operateType\": \"ReadAndWrite\",\n" +
                "  \"visibleType\": \"Visible\",\n" +
                "  \"sceneType\": \"ActiveTrigger\",\n" +
                "  \"props\": {\n" +
                "    \"sceneInfo\": {\n" +
                "      \"scenePath\": \"\",\n" +
                "      \"timerAttr\": {\n" +
                "        \"flag\": false,\n" +
                "        \"type\": 0\n" +
                "      },\n" +
                "      \"triggerCondition\": [],\n" +
                "      \"priority\": 5,\n" +
                "      \"leaveTimeCondition\": [],\n" +
                "      \"extraCondition\": [],\n" +
                "      \"timeCondition\": [],\n" +
                "      \"executeType\": \"CONTROL\",\n" +
                "      \"executes\": [\n" +
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
                "      ]\n" +
                "    }\n" +
                "  }\n" +
                "}";

        JsonObject json = new JsonObject(str);
        JsonObject props = json.getJsonObject("props");
        JsonObject sceneInfo = props.getJsonObject("sceneInfo");
        sceneInfo.put("aa","aa");
        sceneInfo.put("scenePath","/path");


        System.out.println(json.toString());

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

