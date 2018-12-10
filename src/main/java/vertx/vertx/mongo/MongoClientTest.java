package vertx.vertx.mongo;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

/**
 * @author xuefei
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/11/1310:37
 */
public class MongoClientTest {
    public static io.vertx.ext.mongo.MongoClient mongo;
    public static  String host = "192.168.2.213";
    public static  int port = 27017;
    public static  String db_name = "house_keeper";
    public static  String username = "keeperUser";
    public static  String password = "123456";
    public static  String authMechanism = "SCRAM-SHA-1";
    public static  boolean keepAlive = true;

    public static io.vertx.ext.mongo.MongoClient getMongoClient() {
        if (mongo == null) {
            JsonObject json = new JsonObject()
                    .put("host", host)
                    .put("port", port)
                    .put("db_name", db_name)
                    .put("username", username)
                    .put("password", password)
                    .put("authMechanism", authMechanism)
                    .put("keepAlive", keepAlive);
            mongo = io.vertx.ext.mongo.MongoClient.createShared(Vertx.vertx(), json);
        }
        return mongo;
    }
}
