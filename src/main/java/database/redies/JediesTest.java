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
        Set<Integer> classHash = new HashSet<>();
        Map<Integer,Jedis> map = new HashMap<>();
        int nowCount = 0;
        for(int i = 0;;i++){
            nowCount = i;
            try {
                Jedis jedis = JedisPoolClient.getJedis();
                //最小连接数相同
                if(classHash.contains(jedis.hashCode())){
                    System.out.println("same:"+i);
                }else{
                    classHash.add(jedis.hashCode());
                }
                System.out.println("num:"+i);
                map.put(i,jedis);
                jedis.close();
//                System.out.println("info:"+jedis.info());  //db0-*信息:keys=422,expires=3,avg_ttl=63258750
//                System.out.println("db:"+jedis.getDB());//当前db
            }catch(Exception e){
                release(map, nowCount);
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
