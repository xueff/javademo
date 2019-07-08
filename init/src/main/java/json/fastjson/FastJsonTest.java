package json.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import common.bean.Person;

import java.util.HashMap;
import java.util.Map;

//https://www.w3cschool.cn/fastjson/fastjson-serializefilter.html
public class FastJsonTest {
    //TODO
    //Fastjson 处理超大JSON文本

    public static void main(String[] args) {
        ser();
    }
    //定制序列化名称，注解
    //{"userName":"a","Age":18}
    //Person{name='a', age=18, money=0.3530492034117335}
    public static void ser() {
        JSONObject json = new JSONObject();
        json.put("userName","a");
        json.put("Age",18);
        System.out.println(json.toString());
        Person p = JSON.toJavaObject(json,Person.class);
        System.out.println(p.toString());
    }
    public static void baseSer() {
        JSONObject json = new JSONObject();
        json.put("a","a");
        System.out.println("json字符串:"+json.toJSONString());

        json = JSONObject.parseObject(json.toJSONString());
        System.out.println("json字符串转json结果:"+json.toJSONString());

        Person student1 = new Person();
        student1.setName("hh");
        System.out.println("bean转json Str结果:"+JSONObject.toJSONString(student1));
        System.out.println("bean转jsonObj or jsonArray结果:"+JSONObject.toJSON(student1));
    }
    public static void mpAndJson() {
        //map转json对象
        Map<String,Object> map = new HashMap<>();
        map.put("age", 24);
        map.put("name", "cool_summer_moon");
        JSONObject json = new JSONObject(map);
        //json对象转Map
        Map<String,Object> map2 = (Map<String,Object>)json;
    }
}
