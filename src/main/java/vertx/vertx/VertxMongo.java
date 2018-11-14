package vertx.vertx;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;
import org.junit.Test;
import utils.CommonUtils;

import java.util.List;

/**
 * @author xuefei
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/11/510:55
 */
public class VertxMongo {


    public List<JsonObject> findDoc(String name, JsonObject query){
//        mongo.
        return null;

    }


    @Test
    public void EQList(){
        JsonObject query = new JsonObject().put("dcCode","47b9ab80559cce18bb3217129278f9f6")
            .put("hostMac","28f366b6dc86")
                //gte：大于等于；lte：小于等于
                //.put("date", new JsonObject().put("$gte","").put("$lte",""))
                //in
                //.put("$in", idList)
            .put("date", 1541606400000L);//今天
        doQuery(query);
    }

    public void doQuery(JsonObject query){
        MongoClientTest.getMongoClient().find("CountryGardenBattery", query,res -> {
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
        MongoClientTest.getMongoClient().find("CountryGardenBattery", query,res -> {
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


