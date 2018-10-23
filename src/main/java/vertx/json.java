package vertx;

import common.bean.Person;
import io.vertx.core.json.JsonObject;
import org.junit.Test;

/**
 * @author xuefei
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/10/1214:34
 */
public class json {
    @Test
    public void testBeanToJson(){
        Person p = new Person();
        JsonObject j = JsonObject.mapFrom(p);
        System.out.println(j.toString());
    }
}
