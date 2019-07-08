package zhongxin.etcd;

import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * etcd 工具类
 ** @author keriezhang
 * @date 2019/04/19
 */
public class EtcdHttpUtils {
    public static final String ETCD_URL = "http://127.0.0.1:2379/";
    public static final String BASE_URL = ETCD_URL+"v2/keys/";
    static Logger logger = LoggerFactory.getLogger(EtcdHttpUtils.class);
    public static JsonObject getKey(String url){
        String res = EtcdHttoClient.httpGet(url);
        logger.info("请求"+url+"\n返回"+res);
        return new JsonObject(res);
    }
    public static JsonObject addKey(String url, String value){
        Map<String, String> map = new HashMap<>(1);
        map.put("value",value);
        logger.info("请求"+url+"\n"+map);
        String res = EtcdHttoClient.putForm(url,map);
        logger.info("返回"+res);
        return new JsonObject(res);
    }

    public static JsonObject addTtlKey(String url, String value, long time){
        Map<String, String> map = new HashMap<>(2);
        map.put("value",value);
        map.put("ttl", time+"");
        logger.info("请求"+url+"\n"+map);
        String res = EtcdHttoClient.putForm(url,map);
        logger.info("返回"+res);
        return new JsonObject(res);
    }
    public static JsonObject addDir(String url){
        Map<String, String> map = new HashMap<>(1);
        map.put("dir",true+"");
        logger.info("请求"+url+"\n"+map);
        String res = EtcdHttoClient.putForm(url,map);
        logger.info("返回"+res);
        return new JsonObject(res);
    }

    public static JsonObject addTtlDir(String url, long time){
        Map<String, String> map = new HashMap<>(2);
        map.put("dir",true+"");
        map.put("ttl", time+"");
        logger.info("请求"+url+"\n"+map);
        String res = EtcdHttoClient.putForm(url,map);
        logger.info("返回"+res);
        return new JsonObject(res);
    }
    public static JsonObject watchMaster(String url){
        String res = EtcdHttoClient.httpGet(url+"?wait=true");
        logger.info("监听master返回"+res);
        return new JsonObject(res);
    }

    public static JsonObject delEmptyDir(String url){
        String res = EtcdHttoClient.httpDelete(url+"?dir=true");
        logger.info("删除空目录"+url+"\n返回"+res);
        return new JsonObject(res);
    }

    public static JsonObject delDir(String url){
        String res = EtcdHttoClient.httpDelete(url+"?dir=true&recursive=true");
        logger.info("删除目录"+url+"\n返回"+res);
        return new JsonObject(res);
    }
}
