package vertx;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import sun.security.provider.SHA;
import utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xuefei
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/11/510:55
 */
public class VertxMongo {
    public static  MongoClient mongo;
    public static  String host = "39.106.196.5";
    public static  int port = 27017;
    public static  String db_name = "house_keeper";
    public static  String username = "house_keeper";
    public static  String password = "house_keeper";
    public static  String authMechanism = "SCRAM-SHA-1";
    public static  boolean keepAlive = true;

    public static MongoClient  getMongoClient() {
        if (mongo == null) {
            JsonObject json = new JsonObject()
                    .put("host", host)
                    .put("port", port)
                    .put("db_name", db_name)
                    .put("username", username)
                    .put("password", password)
                    .put("authMechanism", authMechanism)
                    .put("keepAlive", keepAlive);
             mongo = MongoClient.createShared(Vertx.vertx(), json);
        }
        return mongo;
    }

    public List<JsonObject> findDoc(String name, JsonObject query){
//        mongo.
        return null;

    }


    @Test
    public void test(){
        JsonObject query = new JsonObject().put("dcCode","47b9ab80559cce18bb3217129278f9f6");
            query.put("hostMac","28f366b6dc86");
            query.put("date", 1541606400000L);//今天

        VertxMongo.getMongoClient().find("CountryGardenBattery", query,res -> {
            if (res.succeeded()) {
                List<JsonObject> list =  res.result();
                System.out.println(list);
            } else {
                res.cause().printStackTrace();
            }
        });
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public List<JsonObject> testReturn(){
        JsonObject query = new JsonObject().put("dcCode","47b9ab80559cce18bb3217129278f9f6");
        query.put("hostMac","28f366b6dc86");
        query.put("date", 1541606400000L);//今天
        VertxMongo.getMongoClient().find("CountryGardenBattery", query,res -> {
            if (res.succeeded()) {
                List<JsonObject> list =  res.result();
                System.out.println(list);
            } else {
                res.cause().printStackTrace();
            }
        });
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}


