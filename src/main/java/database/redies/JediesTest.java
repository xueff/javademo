package database.redies;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.*;

/**
 * @author xuefei
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/10/3115:36
 */
public class JediesTest {

    // 初始最小连接数，不会close
    public static void main(String[] args) {
        for(int i = 0;;i++){
            Jedis jedis = JedisStudy.getInstance(8);
            try {
                jedis.set("BGY0a0362677910b188d490ed2c1a03424759220870617373776f7264","1");


                jedis.close();
//                System.out.println("info:"+jedis.info());  //db0-*信息:keys=422,expires=3,avg_ttl=63258750
//                System.out.println("db:"+jedis.getDB());//当前db
                Thread.sleep(1000);
            }catch(Exception e){
            }finally {
                jedis.close();
            }

        }


    }

    private static void release(Map<Integer,Jedis> map,int nowCount){
        if(map.size()>0){
            Iterator<Map.Entry<Integer, Jedis>> it = map.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<Integer, Jedis> entry = it.next();
                //System.out.println("nowCount:"+entry.getKey());
                if(entry.getKey()<nowCount)
                    entry.getValue().close();
                it.remove();
            }
        }
    }
}
