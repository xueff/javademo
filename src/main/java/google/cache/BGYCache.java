package google.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import common.bean.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;





class Test{
    public static void main(String[] args) {
//        Cache<String, Object> c1 = BGYCache.getInstance("BGYInfraredLearningCache",30);
//        Cache<String, Object> c2 = BGYCache.getInstance("BGYNewDeviceCache",30);
        List<Person> list = new ArrayList<>();
        Person p1 = new Person();
        list.add(p1);
        list.add(p1);



        BGYCache.cacheMap.get("BGYInfraredLearningCache").put("a",list);

        BGYCache.cacheMap.get("BGYNewDeviceCache").put("b","8");
        list = (List<Person>)BGYCache.cacheMap.get("BGYInfraredLearningCache").getIfPresent("a");
        String de = (String)BGYCache.cacheMap.get("BGYNewDeviceCache").getIfPresent("b");

        try {
            list = (List<Person>)BGYCache.getCacheValue("BGYInfraredLearningCache","a");
            System.out.println(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        de = (String)BGYCache.cacheMap.get("BGYNewDeviceCache").getIfPresent("b");

        System.out.println(list);
        System.out.println(de);
    }
}

/**
 * @author xuefei
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/11/815:47
 */
public class BGYCache {
    public static Map<String,Cache<String, Object>> cacheMap = new ConcurrentHashMap<>();

    public static Cache<String, Object> getInstance(String cacheName, int secondTime) {
        if(cacheMap.size()==0 || cacheMap.get(cacheName) == null){
            cacheMap.put(cacheName,getCache(secondTime));
        }
        return cacheMap.get(cacheName);
    }

    private BGYCache() {

    }
    static {
        getInstance("BGYInfraredLearningCache",1);
        getInstance("BGYNewDeviceCache",1);
    }


    /*
     * 初始化缓存池
     * */
    private static Cache<String, Object> getCache(int secondTime){
        return CacheBuilder
                .newBuilder()
                .maximumSize(100000)
                .expireAfterWrite(secondTime, TimeUnit.SECONDS)
                .build();
    }

    public static Object  getCacheValue(String cacheName,String key){
        try {
            return cacheMap.get(cacheName).get(key, new Callable<Object>() {
                @Override
                public Object call() {
                    return null;
                }
            });
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return null;
    }
}

