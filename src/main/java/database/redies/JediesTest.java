package database.redies;

import io.vertx.core.json.JsonArray;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;

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

    /**
     * 批量处理
     */
    public void pipe(){
        Jedis jedis = JedisStudy.getInstance(8);
        Pipeline pipe= jedis.pipelined();
        for (int i = 0; i < 10; i++) {
            try {
                pipe.set(UUID.randomUUID().toString(),"000"+i);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        pipe.sync();
    } /**
     * 批量处理结果
     */
    public void piperesult(){
        Jedis jedis = JedisStudy.getInstance(8);
        Pipeline pip = jedis.pipelined();
        pip.multi();
        for (int i = 0; i < 100000; i++) {
            pip.set(i + "", UUID.randomUUID().toString());
        }

        Response<List<Object>> r = pip.exec();
        pip.multi();
        for (int i = 0; i < 100000; i++) {
            pip.get("" + i);
        }

        r = pip.exec();
        pip.sync();// 调用syn会关闭管道，所以在调用syn之后就不可以在使用管道了
        System.out.println(r.get().get(0));
        System.out.println(r.get().size());
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
