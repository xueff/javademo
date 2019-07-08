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
        JsonObject json = new JsonObject("{\n" +
                "  \"type\": \"Scene\",\n" +
                "  \"operateType\": \"ReadAndWrite\",\n" +
                "  \"visibleType\": \"Visible\",\n" +
                "  \"sceneType\": \"PassiveTrigger\",\n" +
                "  \"props\": {\n" +
                "    \"sceneInfo\": {\n" +
                "      \"scenePath\": \"\",\n" +
                "      \"timerAttr\": {\n" +
                "        \"flag\": false,\n" +
                "        \"type\": 0\n" +
                "      },\n" +
                "      \"triggerCondition\": [\n" +
                "        {\n" +
                "          \"path\": \"/.dentry/bb545405-85b0-4243-873d-a52cfdf14cac/777/灯\",\n" +
                "          \"attributes\": {\n" +
                "            \"Switch\": \"Open\"\n" +
                "          }\n" +
                "        }\n" +
                "      ],\n" +
                "      \"priority\": 5,\n" +
                "      \"leaveTimeCondition\": [],\n" +
                "      \"extraCondition\": [],\n" +
                "      \"timeCondition\": [],\n" +
                "      \"executeType\": \"COMBINE\",\n" +
                "      \"executes\": [\n" +
                "        {\n" +
                "          \"executeType\": \"CONTROL\",\n" +
                "          \"prop\": {\n" +
                "            \"path\": \"/.dentry/bb545405-85b0-4243-873d-a52cfdf14cac/777/空调\",\n" +
                "            \"cmdName\": \"TurnOn\",\n" +
                "            \"delay\": 0,\n" +
                "            \"cmd\": {\n" +
                "              \n" +
                "            }\n" +
                "          }\n" +
                "        },\n" +
                "        {\n" +
                "          \"executeType\": \"CONTROL\",\n" +
                "          \"prop\": {\n" +
                "            \"path\": \"/.dentry/bb545405-85b0-4243-873d-a52cfdf14cac/777/调光灯\",\n" +
                "            \"cmdName\": \"TurnOn\",\n" +
                "            \"delay\": 0,\n" +
                "            \"cmd\": {\n" +
                "              \n" +
                "            }\n" +
                "          }\n" +
                "        },\n" +
                "        {\n" +
                "          \"executeType\": \"CONTROL\",\n" +
                "          \"prop\": {\n" +
                "            \"path\": \"/.dentry/bb545405-85b0-4243-873d-a52cfdf14cac/777/音响\",\n" +
                "            \"cmdName\": \"Play\",\n" +
                "            \"delay\": 0,\n" +
                "            \"cmd\": {\n" +
                "              \n" +
                "            }\n" +
                "          }\n" +
                "        },\n" +
                "        {\n" +
                "          \"executeType\": \"CONTROL\",\n" +
                "          \"prop\": {\n" +
                "            \"path\": \"/.dentry/bb545405-85b0-4243-873d-a52cfdf14cac/777/取电开关\",\n" +
                "            \"cmdName\": \"TurnOn\",\n" +
                "            \"delay\": 0,\n" +
                "            \"cmd\": {\n" +
                "              \n" +
                "            }\n" +
                "          }\n" +
                "        }\n" +
                "      ]\n" +
                "    },\n" +
                "    \"sceneId\": \"5c1c84534c9a6641c32a4964\"\n" +
                "  }\n" +
                "}");
        StringBuffer jsonCode = new StringBuffer("JsonObject json = ");
        jsonCode = getJsonObjectCode(json.getMap(),jsonCode);
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
            }else if(value instanceof Boolean){
                jsonCode.append(".put(\""+key+"\","+value);
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


