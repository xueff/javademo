package vertx.vertx.mongo;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.FindOptions;
import org.junit.Test;
import utils.DateUtils;

import java.util.Date;
import java.util.List;

/**
 * @author xuefei
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/11/510:55
 */
public class VertxMongo {


    public static List<JsonObject> findDoc() throws InterruptedException {

        FindOptions findOptions = new FindOptions().setFields(new JsonObject().put("_id",0) );
        MongoClientTest.getMongoClient().findWithOptions("Xctrl_Home_DoubleControl",
                new JsonObject().put("dcCode","47b9ab80559cce18bb3217129278f9f6").put("hostMac","0CEFAFD20B06"),findOptions, new Handler<AsyncResult<List<JsonObject>>>() {
                    @Override
                    public void handle(AsyncResult<List<JsonObject>> event) {
                        {
                            List<JsonObject> list = event.result();
                            System.out.println(list);

                        }
                    }
                });
//        mongo.
        Thread.sleep(100000);
        return null;

    }

    @Test
    public void addTest(){
        long st = new Date().getTime();
        Double a = 1.0;
        for(int i=0;i<5;i++) {
            JsonObject add = new JsonObject()
                    .put("hotelId", "bb545405-85b0-4243-873d-a52cfdf14cac")
                    .put("roomNo", "002")
                    .put("timeStamp", DateUtils.getDayStartTime(new Date(st)).getTime())
                    .put("valueW", a+i)
                    .put("lastDayValueW", a+i)
                    .put("valueE", a+i+1)
                    .put("lastDayValueE", a+i)
                    .put("listW", new JsonArray())
                    .put("listE", new JsonArray());
            MongoClientTest.getMongoClient()
                    .insert("CountryGardenBattery", add
                            , new Handler<AsyncResult<String>>() {
                                @Override
                                public void handle(AsyncResult<String> event) {
//                                JsonObject r = event.result();
//                                System.out.println(r);
                                }
                            }
                    );
            st += DateUtils.oneDayTime;

        }
        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void EQList(){
        JsonObject query = new JsonObject().put("hotelId", "bb545405-85b0-4243-873d-a52cfdf14cac")
//            .put("hostMac","28f366b6dc86");
                //gte：大于等于；lte：小于等于
                .put("timeStamp", new JsonObject().put("$gte",1546272000000L).put("$lte",1548864000000L));
                //in
                //.put("$in", idList)
//            .put("date", 1541606400000L);//今天
//        List<JsonObject> list = MongoClientTest.getMongoClient().findWithOptions(query,new FindOptions().setSort(new JsonObject().put("timeStamp",1)));
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
        MongoClientTest.getMongoClient().find("CountryGardenEnvironment", query,res -> {
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

    @Test
    public void distinc(){
        JsonObject query = new JsonObject().put("key" ,"brand");
//        query.put("hostMac","28f366b6dc86");
//        query.put("date", 1541606400000L);//今天
            MongoClientTest.getMongoClient().distinct("TVInfrared","brand" ,String.class.getName(),res -> {
            if (res.succeeded()) {
                List list =  res.result().getList();
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


