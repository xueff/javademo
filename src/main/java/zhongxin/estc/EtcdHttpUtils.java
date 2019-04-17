package zhongxin.estc;

import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class EtcdHttpUtils {
    public static final String etcdUrl = "http://127.0.0.1:2379/";
    public static final String baseUrl = etcdUrl+"v2/keys/"+ "com/xiezhu/monitor/";
    public static final String electionUrl = baseUrl+"election"; //选举
    public static final String masterUrl = baseUrl+"master";
    static Logger logger = LoggerFactory.getLogger(EtcdHttpUtils.class);
    public static JsonObject getKey(String url){
        String res = EtcdHttoClient.httpGet(url);
        logger.info("请求"+url+"\n返回"+res);
        return new JsonObject(res);
    }
    public static JsonObject addKey(String url, String value){
        Map<String, String> map = new HashMap<>();
        map.put("value",value);
        logger.info("请求"+url+"\n"+map);
        String res = EtcdHttoClient.putForm(url,map);
        logger.info("返回"+res);
        return new JsonObject(res);
    }

    public static JsonObject addTtlKey(String url, String value, long time){
        Map<String, String> map = new HashMap<>();
        map.put("value",value);
        map.put("ttl", time+"");
        logger.info("请求"+url+"\n"+map);
        String res = EtcdHttoClient.putForm(url,map);
        logger.info("返回"+res);
        return new JsonObject(res);
    }
    public static JsonObject addDir(String url){
        Map<String, String> map = new HashMap<>();
        map.put("dir",true+"");
        logger.info("请求"+url+"\n"+map);
        String res = EtcdHttoClient.putForm(url,map);
        logger.info("返回"+res);
        return new JsonObject(res);
    }

    public static JsonObject addTtlDir(String url, long time){
        Map<String, String> map = new HashMap<>();
        map.put("dir",true+"");
        map.put("ttl", time+"");
        logger.info("请求"+url+"\n"+map);
        String res = EtcdHttoClient.putForm(url,map);
        logger.info("返回"+res);
        return new JsonObject(res);
    }
    public static JsonObject watchMaster(){
        String res = EtcdHttoClient.httpGet(masterUrl+"?wait=true");
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
