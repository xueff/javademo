package json.jsonlib;

import common.bean.Person;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import java.util.Collection;

public class Jsonlib {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Person p = new Person();
        System.out.println("JSONSerializer.toJSON()方法");
        JSON json = JSONSerializer.toJSON(p);
        System.out.println(json);

        System.out.println( "String转为json" );
        String jsonStr = "{author=\"tomas\",id:1,price:12,publishDate:\"\"}";

        JSONObject jsonObject = JSONObject.fromObject( json );

        System.out.println("JSONSerializer.toJava()方法");
        Object tempObj =  JSONSerializer.toJava(json);
        System.out.println(Object.class.cast(p).toString());


        JSONObject jsonnew = new JSONObject();
        jsonnew.put("1",2);
        jsonnew.put("1wqe","asfda");
        System.out.println(jsonnew);
        Collection c = jsonnew.values();
        System.out.println(c);
    }

}
