package database.mapdb;

import io.vertx.core.json.JsonObject;
import javabase.ramdon.RamdonStudy;
import org.junit.Test;
import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.Serializer;

import java.util.*;
import java.util.concurrent.ConcurrentMap;

/**
 * @author xuefei
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/10/2913:51
 * @herf https://blog.csdn.net/cnhome/article/details/75467000
 */
public class MapDB {
    static DB db = null;

    public static void main(String[] args) {
        DB db = DBMaker
                .fileDB("file.db")
                .fileMmapEnable()
                .make();
        ConcurrentMap<String,Long> map = db
                .hashMap("map", Serializer.STRING, Serializer.LONG)
                .createOrOpen();
        map.put("something", 111L);

        db.close();
    }

    @Test
    public void testMemoryMapdb(){
        //它打开内存中的HashMap，它使用堆栈存储，它不受垃圾收集的限制：
        DB db = DBMaker.memoryDB().make();
        ConcurrentMap map = db.hashMap("map").createOrOpen();
        map.put("something", "here");
        map.put("something1", 111L);
        db.close();
    }
    @Test
    public void getMapDb(){
        //HashMap（和其他集合）也可以存储在文件中。
        // 在这种情况下，可以在JVM重新启动之间保留内容。
        // 有必要调用DB.close（）来保护文件免受数据损坏。其他选项是使用写入日志来启用事务。
        Map<String,Integer> map  = db.get("map");
//        Map<String,Object> map = new HashMap<>();
//        while(true) {
            System.out.println(map.size());
//        }
            //db.close();
    }

    @Test
    public void addMapDb(){
        //HashMap（和其他集合）也可以存储在文件中。
        // 在这种情况下，可以在JVM重新启动之间保留内容。
        // 有必要调用DB.close（）来保护文件免受数据损坏。其他选项是使用写入日志来启用事务。
        DB db = DBMaker.fileDB("D:/DC/.nat").fileLockDisable().closeOnJvmShutdown().make();
        Iterator<Map.Entry<String,Object>> its = db.getAll().entrySet().iterator();
//        Map<String,Object> map = new HashMap<>();
        while(true) {
            while (its.hasNext()) {
                Map.Entry<String, Object> it = its.next();
                System.out.println(((JsonObject) it.getValue()).size());

            }
        }
        //db.close();
    }
    @Test
    public void testfileSerlizMapdb(){
        //MapDB使用泛型序列化，可以序列化任何数据类型。
        // 使用专门的串行器，速度更快，记忆效率更高。
        // 此外，我们还可以在64位操作系统上启用更快速的内存映射文件：
        db = DBMaker.fileDB("file.db").fileMmapEnable().make();
        ConcurrentMap<String,Integer> map =
                db.hashMap("map", Serializer.STRING, Serializer.INTEGER)
                        .createOrOpen();
        while (true) {
            map.put(UUID.randomUUID().toString(), RamdonStudy.getRamdonInt(100000));
            db.commit();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            getMapDb();
        }
//        db.close();
    }

}
