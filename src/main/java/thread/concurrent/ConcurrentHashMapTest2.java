package thread.concurrent;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xuefei
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/10/1115:48
 */
public class ConcurrentHashMapTest2 {
    public static void main  (String args[]) throws InterruptedException {
        HashMapDemo2 demo = new HashMapDemo2();
        demo.deviceIdFather = "2001";
//        if(HashMapDemo2.contentMap.contains(demo.deviceIdFather)) {
//            HashMapDemo2.contentMap.get(demo.deviceIdFather).getJSONArray(demo.deviceIdFather).add(Thread.currentThread().getName());
//        }
//        else {
//            HashMapDemo2.contentMap.put(demo.deviceIdFather,new JSONObject());
//        }




        new Thread(demo).start();
        Thread.sleep(100);
        new Thread(demo).start(); Thread.sleep(3);
        new Thread(demo).start(); Thread.sleep(3);
        new Thread(demo).start(); Thread.sleep(3);
        new Thread(demo).start(); Thread.sleep(3);
        new Thread(demo).start(); Thread.sleep(3);
        new Thread(demo).start(); Thread.sleep(3);
        HashMapDemo2 demo2 = new HashMapDemo2();
        demo2.deviceIdFather = "3002";
        new Thread(demo2).start(); Thread.sleep(3);
        new Thread(demo2).start(); Thread.sleep(3);
        HashMapDemo2 demo3 = new HashMapDemo2();
        demo3.deviceIdFather = "4003";
        new Thread(demo3).start(); Thread.sleep(3);
        new Thread(demo3).start(); Thread.sleep(3);
        new Thread(demo3).start(); Thread.sleep(3);
        new Thread(demo3).start(); Thread.sleep(3);



    }
}

class HashMapDemo2 implements Runnable{
    //private static ConcurrentHashMap<String,Integer> map = new ConcurrentHashMap();
    public static ConcurrentHashMap<String,JSONObject> contentMap = new ConcurrentHashMap();
    public String deviceIdFather;
    @Override
    public void run() {
        //String deviceIdFather = name.substring(0,name.length()-2);
        System.out.print(Thread.currentThread().getName());
        try {
            System.out.println(JSONObject.fromObject(contentMap));
            if(contentMap.contains(deviceIdFather)) {
                contentMap.get(deviceIdFather).getJSONArray(deviceIdFather).add(Thread.currentThread().getName());
            }
            else {
                JSONObject json = new JSONObject();
                contentMap.put(deviceIdFather, json);
                JSONArray arr = new JSONArray();
                json.put(deviceIdFather,arr);
                arr.add(Thread.currentThread().getName());
                arr.add("12");
                json.put(deviceIdFather,arr);

            }


            Thread.sleep(3000);
            synchronized (contentMap) {
                if(contentMap.containsKey(deviceIdFather))
                    System.out.println(contentMap.get(deviceIdFather).toString());
                //contentMap.remove(deviceIdFather);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

