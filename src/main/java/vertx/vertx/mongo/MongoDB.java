package vertx.vertx.mongo;

import co.paralleluniverse.strands.channels.Channel;
import co.paralleluniverse.strands.channels.Channels;
import io.vertx.core.AsyncResult;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.BulkOperation;
import io.vertx.ext.mongo.FindOptions;
import io.vertx.ext.mongo.MongoClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author wz
 * 程序运行之时，Mongo连接池不需要关闭
 * 从连接池获取的连接，以及连接中获取的collection都不需要手动释放，事实上也并没有close()代码
 * 同步操作用于查询或者需要知道结果的操作
 * 异步操作用于不需要知道结果的操作，如日志插入（不管插入成功与否，均不影响代码执行）
 * */

public class MongoDB {
    private Vertx vertx;
    private final JsonObject config = new JsonObject();
    private MongoClient client;

    /**
     * 构造函数
     * @param vertx vertx
     * @param  host ip
     * @param port 端口
     * @param db_name 数据库名
     * @param username 用户名
     * @param password 密码
     * @param authMechanism 认证
     * @param serverSelectionTimeoutMS 从连接池获取连接超时时间
     * @param keepAlive 是否长连接
     * */
    public MongoDB(Vertx vertx, String host, int port, String db_name, String username,
                   String password, String authMechanism, int serverSelectionTimeoutMS, boolean keepAlive) {
        this.vertx = vertx;
        config.put("host", host);
        config.put("port", port);
        config.put("db_name", db_name);
        config.put("username", username);
        config.put("password", password);
        config.put("authMechanism", authMechanism);
        config.put("serverSelectionTimeoutMS", serverSelectionTimeoutMS);
        config.put("keepAlive", keepAlive);
    }

    /**
     * 查询 同步操作
     * @param collection 集合
     * @param query 查询参数
     * */
    public List<JsonObject> find(String collection, JsonObject query) {
        Channel<List<JsonObject>> channel = Channels.newChannel(0);
        getClient().find(collection, query, res -> putQueryResultInChannel(res, channel));
        try {
            //设置30秒超时时间，不能让channel无限等待
            return channel.receive(30, TimeUnit.SECONDS);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 查询 同步操作
     * @param collection 集合
     * @param query 查询参数
     * */
    public List<JsonObject> findWithOptions(String collection, JsonObject query, FindOptions options) {
        Channel<List<JsonObject>> channel = Channels.newChannel(0);
        getClient().findWithOptions(collection, query, options, res -> putQueryResultInChannel(res, channel));
        try {
            //设置30秒超时时间，不能让channel无限等待
            return channel.receive(30, TimeUnit.SECONDS);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 同步批量插入，_id已存在会报错
     * 非事务操作，中途报错后操作终止，已经执行过的操作不会被撤销
     * @param collection 集合
     * @param data 要插入的数据
     * */
    public boolean inserts_sync(String collection, List data) {
        List<BulkOperation> operations = new ArrayList<>();
        for (Object item : data) {
            JsonObject addItem;
            if (item instanceof JsonObject){
                addItem = (JsonObject) item;
            }else if(item instanceof Map) {
                addItem = new JsonObject((Map<String, Object>) item);
            }else if(item instanceof String) {
                addItem = new JsonObject((String) item);
            }else {
                addItem = JsonObject.mapFrom(item);
            }
            operations.add(BulkOperation.createInsert(addItem));
        }
        Channel<Boolean> channel = Channels.newChannel(0);
        getClient().bulkWrite(collection, operations, res -> putOprResultInChannel(res, channel));
        try {
            //设置20秒超时时间，不能让channel无限等待
            return channel.receive(20, TimeUnit.SECONDS);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 同步存储，_id存在则覆盖，_id不存在则插入
     * @param collection 集合
     * @param data 要存储的数据
     * */
    public boolean save_sync(String collection, JsonObject data) {
        Channel<Boolean> channel = Channels.newChannel(0);
        getClient().save(collection, data, res -> putOprResultInChannel(res, channel));
        try {
            //设置20秒超时时间，不能让channel无限等待
            return channel.receive(20, TimeUnit.SECONDS);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 同步替换 替换一个数据
     * @param collection 集合
     * @param query 被替换的数据
     * @param data 新数据
     * */
    public boolean replace_sync(String collection, JsonObject query, JsonObject data) {
        Channel<Boolean> channel = Channels.newChannel(0);
        getClient().findOneAndReplace(collection, query, data, res -> putOprResultInChannel(res, channel));
        try {
            //设置20秒超时时间，不能让channel无限等待
            return channel.receive(20, TimeUnit.SECONDS);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 同步删除
     * @param collection 集合
     * @param query 删除参数
     * */
    public boolean remove_sync(String collection, JsonObject query) {
        Channel<Boolean> channel = Channels.newChannel(0);
        getClient().removeDocuments(collection, query, res -> putOprResultInChannel(res, channel));
        try {
            //设置20秒超时时间，不能让channel无限等待
            return channel.receive(20, TimeUnit.SECONDS);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 异步批量插入，_id已存在会报错
     * 非事务操作，中途报错后操作终止，已经执行过的操作不会被撤销
     * @param collection 集合
     * @param data 要插入的数据
     * */
    public void inserts_async(String collection, List<JsonObject> data) {
        List<BulkOperation> operations = new ArrayList<>();
        for (JsonObject item : data) {
            operations.add(BulkOperation.createInsert(item));
        }
        getClient().bulkWrite(collection, operations, res -> {
            if (res.failed()){ res.cause().printStackTrace();
            }
        });
    }

    /**
     * 异步存储，_id存在则覆盖，_id不存在则插入
     * @param collection 集合
     * @param data 要存储的数据
     * */
    public void save_async(String collection, JsonObject data) {
        getClient().save(collection, data, res -> {
            if (res.failed()) {
                res.cause().printStackTrace();
            }
        });
    }

    /**
     * 异步删除
     * @param collection 集合
     * @param query 删除参数
     * */
    public void remove_async(String collection, JsonObject query) {
        getClient().removeDocuments(collection, query, res -> {
            if (res.failed()) {
                res.cause().printStackTrace();
            }
        });
    }

    /**
     * 获取连接池
     * */
    private MongoClient getClient() {
        if (client == null) {
            client = MongoClient.createShared(vertx, config);
        }
        return client;
    }
    /**
     * 将Mongo异步查询结果放入Channel中
     * */
    private void putQueryResultInChannel(AsyncResult<List<JsonObject>> result, Channel<List<JsonObject>> channel) {
        if (result.succeeded()) {
            try {
                if (result.result() == null) {
                    channel.send(new ArrayList<>());
                }else {
                    channel.send(result.result());
                }
            } catch (Exception e) {
                e.printStackTrace(); //已经成功了，channel上的异常无需抛出
            }
        } else {
            try {
                client.close();
                client = null;
                channel.send(new ArrayList<>());
            } catch (Exception e) {
                e.printStackTrace(); //失败了，需要抛出的是下面的失败原因
            }
            throw new RuntimeException(result.cause().getMessage());
        }
    }
    /**
     * 将Mongo异步操作结果放入Channel中
     * */
    private void putOprResultInChannel(AsyncResult result, Channel<Boolean> channel) {
        if (result.succeeded()) {
            try {
                channel.send(true);
            } catch (Exception e) {
                e.printStackTrace(); //已经成功了，channel上的异常无需抛出
            }
        } else {
            try {
                client.close();
                client = null;
                channel.send(false);
            } catch (Exception e) {
                e.printStackTrace(); //失败了，需要抛出的是下面的失败原因
            }
            throw new RuntimeException(result.cause().getMessage());
        }
    }
}