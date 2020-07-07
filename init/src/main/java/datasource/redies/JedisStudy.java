package datasource.redies;

import javabase.ramdon.RamdonStudy;
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
            host = "";
//            host = "0000";
            port = 7369;
            dbPool = new ConcurrentHashMap<Integer, JedisPool>();
        }

        JedisStudy.config.setMaxTotal(300); //连接实例最大数目
        //RedisCache.Companion.config.maxIdle = RedisCache.Companion.properties.getInt("redis.max_idle") //连接实例最大空闲数目
        JedisStudy.config.setMaxIdle(80);
        JedisStudy.config.setMinIdle(40);
        JedisStudy.config.setMaxWaitMillis(60000);
        JedisStudy.config.setTestOnBorrow(true); //获取连接之前是否测试可用

    }

    //redis构造方法
    public static Jedis getInstance(Integer db) {
        JedisPool pool = null;
        if (JedisStudy.dbPool.containsKey(db)) {
            pool = JedisStudy.dbPool.get(db);
        } else {
            pool = new JedisPool(config, host, port,50000, "!@#$", db);
//            pool = new JedisPool(config, host, port,1000, "Xiezhu1234!@#$", db);
            JedisStudy.dbPool.put(db, pool);
        }


        try {
            connection = pool.getResource();
//            connection.set("test", "1324654");
        } catch (Exception e) {
            try {
                Thread.sleep(RamdonStudy.getRamdonInt(1000));
                connection = pool.getResource();
//            connection.set("test", "1324654");
            } catch (Exception ex) {}
            //连接出现异常
            System.out.println("redis 链接异常，请检查网络...");
            //JedisStudy.dbPool.remove(db);
            throw new RuntimeException("redis 链接异常，请检查网络...", e);
        }
        return connection;
    }

    public Set<String> listAllKeys(String like)
    {
        return connection.keys(like);
    }
}
