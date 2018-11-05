package vertx;

import common.bean.Person;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import jdk.nashorn.internal.scripts.JS;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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
    public void testListToJson(){
        List<Person> list = new ArrayList<Person>();
        list.add(new Person());
        list.add(new Person());
        JsonArray l = new JsonArray(list);
        System.out.println(l.toString());
    }
}
