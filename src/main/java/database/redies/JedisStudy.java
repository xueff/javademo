package database.redies;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xuefei
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/10/2317:31
 */
public class JedisStudy {

    private static Jedis connection = null;
    private static JedisPoolConfig config = new JedisPoolConfig();


    private static String host = "";
    private static int port = 0;
    private static Map<Integer, JedisPool> dbPool = null;

    static {
        if(host.length()==0) {
            host = "192.168.2.207";
            port = 6379;
            dbPool = new ConcurrentHashMap<Integer, JedisPool>();
        }

        JedisStudy.config.setMaxTotal(50); //连接实例最大数目
        //RedisCache.Companion.config.maxIdle = RedisCache.Companion.properties.getInt("redis.max_idle") //连接实例最大空闲数目
        JedisStudy.config.setMinIdle(5);
        JedisStudy.config.setTestOnBorrow(true); //获取连接之前是否测试可用

    }

    //redis构造方法
    public static Jedis getInstance(Integer db) {
        JedisPool pool = null;
        if (JedisStudy.dbPool.containsKey(db)) {
            pool = JedisStudy.dbPool.get(db);
        } else {
            pool = new JedisPool(config, host, port,1000, "123456", db);
            JedisStudy.dbPool.put(db, pool);
        }
        connection = pool.getResource();

        try {
            connection.set("test", "1324654");
        } catch (Exception e) {
            //连接出现异常
            System.out.println("redis 链接异常，请检查网络...");
            JedisStudy.dbPool.remove(db);
            throw new RuntimeException("redis 链接异常，请检查网络...", e);
        }
        return connection;
    }

    public Set<String> listAllKeys(String like)
    {
        return connection.keys(like);
    }
}
