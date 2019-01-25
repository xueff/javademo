package json.fastjson;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import common.bean.Person;
import org.junit.Test;
import org.junit.runner.RunWith;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class FastJsonTest {

    public static void main(String[] args) {
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
}
