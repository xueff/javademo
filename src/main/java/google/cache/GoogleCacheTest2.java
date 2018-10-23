package google.cache;

import com.google.common.cache.*;
import common.bean.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author xuefei
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/10/2213:33
 */
public class GoogleCacheTest2 {


    private static Cache<String, List<Person>> cacheFormCallable;

    private static GoogleCacheTest2 single = new GoogleCacheTest2();

    public static GoogleCacheTest2 getInstance() {
        return single;
    }

    /*
     * 初始化缓存池
     * */
    private GoogleCacheTest2(){
        this.cacheFormCallable = CacheBuilder
                .newBuilder()
                // 设置并发级别为8，并发级别是指可以同时写缓存的线程数
                .concurrencyLevel(8)
                .maximumSize(100000)
                // .expireAfterWrite(5, TimeUnit.SECONDS)//给定时间内没有写访问，则回收。
                // .expireAfterAccess(3, TimeUnit.SECONDS)// 缓存过期时间为3秒
                .expireAfterWrite(30, TimeUnit.SECONDS)//过期时间
                // 设置缓存容器的初始容量为10
                .initialCapacity(2)
                // 设置缓存最大容量为100，超过100之后就会按照LRU最近虽少使用算法来移除缓存项
                .maximumSize(2)
                // 设置要统计缓存的命中率
                .recordStats()
                // 设置缓存的移除通知
                .removalListener(new RemovalListener<Object, Object>() {
                    public void onRemoval(RemovalNotification<Object, Object> notification) {
                        System.out.println(
                                notification.getKey() + " was removed, cause is " + notification.getCause());
                    }
                })
        // 自动 刷数据
                .build(new CacheLoader<String, List<Person>>() {
                    @Override
                    /**  当本地缓存命没有中时，调用load方法获取结果并将结果缓存
                     */
                    public List<Person> load(String key) throws ExecutionException {
                        System.out.println(key + " load in cache");
                        return getPerson(key);
                    }

                    // 此时一般我们会进行相关处理，如到数据库去查询
                    private List<Person> getPerson(String key) throws ExecutionException {
                                return new ArrayList<>();
                    }
                });


    }
    /*
     * 存储缓存对象
     * */
    public void createCache(String key, List<Person> value) {
        cacheFormCallable.put(key,value);
    }
    /*
     * 数据手动过期处理
     * */
    public void invalidCache(String key){
        cacheFormCallable.invalidate(key);
    }
    /*
     * 获取缓存对象,不能反悔null
     * */
    public List<Person>  getCache(String key){
        try {
            return cacheFormCallable.get(key, new Callable<List<Person>>() {
                @Override
                public List<Person> call() {
                    return new ArrayList<Person>();
                }
            });
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }
}
