package javabase.thread.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xuefei
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/10/1115:48
 */
public class ConcurrentHashMapTest implements  Runnable{
    public int i = 0;
    public static void main  (String args[]) throws InterruptedException {
        for(int k = 0;k<5;k++) {
            int j = 1;
            ConcurrentHashMapTest t = new ConcurrentHashMapTest((j++) * 100);
            ConcurrentHashMapTest t2 = new ConcurrentHashMapTest((j++) * 100);
            ConcurrentHashMapTest t3 = new ConcurrentHashMapTest((j++) * 100);
            ConcurrentHashMapTest t4 = new ConcurrentHashMapTest((j++) * 100);
            ConcurrentHashMapTest t5 = new ConcurrentHashMapTest((j++) * 100);
            ConcurrentHashMapTest t6 = new ConcurrentHashMapTest((j++) * 100);
            ConcurrentHashMapTest t7 = new ConcurrentHashMapTest((j++) * 100);
            ConcurrentHashMapTest t8 = new ConcurrentHashMapTest((j++) * 100);
            ConcurrentHashMapTest t9 = new ConcurrentHashMapTest((j++) * 100);
            ConcurrentHashMapTest t10 = new ConcurrentHashMapTest((j++) * 100);
            new Thread(t).start();
            new Thread(t2).start();
            new Thread(t3).start();
            new Thread(t4).start();
            new Thread(t5).start();
            new Thread(t6).start();
            new Thread(t7).start();
            new Thread(t8).start();
            new Thread(t9).start();
            new Thread(t10).start();
        }
        return;

    }

    public ConcurrentHashMapTest(int i) {
        this.i = i;
    }

    @Override
    public void run() {
        new Thread(new HashMapDemo("1", "key"+i++)).start();
        new Thread(new HashMapDemo("1", "key"+i++)).start();
        new Thread(new HashMapDemo("1", "key"+i++)).start();



        new Thread(new HashMapDemo("2", "key"+i++)).start();
        new Thread(new HashMapDemo("2", "key"+i++)).start();
        new Thread(new HashMapDemo("2", "key"+i++)).start();
        new Thread(new HashMapDemo("2", "key"+i++)).start();


        new Thread(new HashMapDemo("3", "key"+i++)).start();
        new Thread(new HashMapDemo("3", "key"+i++)).start();
        new Thread(new HashMapDemo("3", "key"+i++)).start();
        new Thread(new HashMapDemo("3", "key"+i++)).start();
        new Thread(new HashMapDemo("3", "key"+i++)).start();
        new Thread(new HashMapDemo("3", "key"+i++)).start();
        new Thread(new HashMapDemo("3", "key"+i++)).start();

    }

}

class HashMapDemo implements Runnable{
//    private static java.util.concurrent.ConcurrentHashMap<String,AtomicInteger> map = new ConcurrentHashMap();
    private static ConcurrentHashMap<String,List<String>> contentMap = new ConcurrentHashMap();
    public String name;
    public String key;

    public HashMapDemo(String name, String key) {
        this.name = name;
        this.key = key;
    }

    @Override
    public void run() {
//        if(!map.containsKey(name)){
//            map.put(name, new AtomicInteger());
//        }
//       map.get(name).getAndAdd(1);

        if(!contentMap.containsKey(name)){
            contentMap.put(name, new ArrayList<String>());
        }
        contentMap.get(name).add(key);


        try {
            Thread.sleep(9000);
            synchronized (contentMap) {
                if(contentMap.containsKey(name)) {
                    System.out.println(name);
                    System.out.println(contentMap.get(name).size());
                }
                contentMap.remove(name);
            }
//            synchronized (map) {
//                if(map.containsKey(name))
//                    Do.doJob(map.get(name).get());
//                map.remove(name);
//            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Do{
    public static void doJob(int value){
        System.out.println(value);
        System.out.println(value);
    }
}
