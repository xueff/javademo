package vertx.vertx.mongo;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import org.junit.Test;

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
    public void addTest(){
        JsonObject add =new JsonObject()
                .put("hotelId", "bb545405-85b0-4243-873d-a52cfdf14cac")
                .put("roomNo", "001")
                .put("cuid", "1")
                .put("openUid", "1")
                .put("appId", "1")
                .put("appSecret", "1")
                .put("clientId","1")
                .put("clientSecret","1");
        MongoClientTest.getMongoClient()
                .insert("XiaoDuRobot",add
                                ,new Handler<AsyncResult<String>>() {
                            @Override
                            public void handle(AsyncResult<String> event) {
//                                JsonObject r = event.result();
//                                System.out.println(r);
                            }
                        }
                );
        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void EQList(){
        JsonObject query = new JsonObject().put("dcCode","1")
            .put("hostMac","28f366b6dc86");
                //gte：大于等于；lte：小于等于
                //.put("date", new JsonObject().put("$gte","").put("$lte",""))
                //in
                //.put("$in", idList)
//            .put("date", 1541606400000L);//今天
        doQuery(query);
    }

    @Test
    public void addList(){
        //db.getCollection('CountryGardenBattery').update({"_id":"5bee84b8d987f53c7c7586d6"},
        //{$inc:{"electricList.0":1}})
        MongoClientTest.getMongoClient()
                .findOneAndUpdate("CountryGardenEnvironment", new JsonObject()
                                .put("_id", "5bee5be0d987f531ac73a8f9")
                        , new JsonObject()
                                .put("$inc",new JsonObject()
                                        .put("dataList.6",10.0)),
                        new Handler<AsyncResult<JsonObject>>() {
                            @Override
                            public void handle(AsyncResult<JsonObject> event) {

                                JsonObject r = event.result();
                                System.out.println(r);
                            }
                        }
                );
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
    public void testReturn(){
        JsonObject query = new JsonObject().put("date" ,1542297600000L);
//        query.put("hostMac","28f366b6dc86");
//        query.put("date", 1541606400000L);//今天
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






}


