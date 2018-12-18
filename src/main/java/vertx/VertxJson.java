package vertx;

import common.bean.Person;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author xuefei
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/10/1214:34
 */
public class VertxJson {
    @Test
    public void testObjToJson(){
        Person p = new Person();
        JsonObject j = JsonObject.mapFrom(p);
        System.out.println(j.toString());
    }
    @Test
    public void testJsonToObj(){
        Person p = new Person();
        JsonObject j = JsonObject.mapFrom(p);
        System.out.println(j.toString());
    }

    @Test
    public void testStringToJson(){
        JsonObject j = new JsonObject("{\"xm\":90}");
        System.out.println(j.toString());
    }

    @Test
    public void testListToJson(){
        List<Person> list = new ArrayList<Person>();
        list.add(new Person());
        list.add(new Person());
        JsonArray l = new JsonArray(list);
        System.out.println(l.toString());
    }


    @Test
    public void forEach(){
        Person p = new Person();
        JsonObject json = JsonObject.mapFrom(p);
        Iterator<Map.Entry<String, Object>> items = json.iterator();
        while(items.hasNext()){
            Object item = items.next();

           System.out.println(""+((Map.Entry) item).getKey().toString().equals("name"));
           System.out.println(((Map.Entry) item).getValue() instanceof Double);
        }
    }

    @Test
    public void test() {
        JsonObject jsonObj = new JsonObject();
        jsonObj.put("action","tvyuyin.channeldataupdate");
        jsonObj.put("data",new JsonObject().put("child","child"));
        System.out.println(jsonObj);
        JsonObject j = (JsonObject) jsonObj.remove("data");
        System.out.println(j);
        System.out.println(jsonObj);

    }
}
