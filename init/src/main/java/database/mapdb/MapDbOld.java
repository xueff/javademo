//package database.mapdb;
//
//import org.mapdb.BTreeMap;
//import org.mapdb.DB;
//import org.mapdb.DBMaker;
//
//import java.io.File;
//import java.util.Iterator;
//import java.util.Map;
//
///**
// *         <dependency>
// *             <groupId>org.mapdb</groupId>
// *             <artifactId>mapdb</artifactId>
// *             <version>2.0-beta5</version>
// *         </dependency>
// */
//
///**
// * @author xuefei
// * @description main
// * @date 2019/7/17
// */
//public class MapDbOld {
//
//    public static void main(String[] args) {
//        put();
////        get();
//        File file = new File("/opt/data/a.db");
//        DB db = DBMaker.fileDB(file)
//                .fileMmapEnable()
//                .make();
//        Map<String, Object> monitorDataMap = db.getAll();
//        Iterator iter = monitorDataMap.keySet().iterator();
//
//
//        while (iter.hasNext()) {
//
//            String key = (String) iter.next();
//
//            System.out.println(key);
//            System.out.println(monitorDataMap.get(key));
//        }
//
//        db.close();
//
//    }
//
//    private static void get(){
//        File file = new File("/opt/data/a.db");
//        DB db = DBMaker.fileDB(file)
//                .fileMmapEnable()
//                .make();
//        BTreeMap<String, String> monitorDataMap = db.getTreeMap("monitorDataMap");
//        Iterator iter = monitorDataMap.keySet().iterator();
//
//
//        while (iter.hasNext()) {
//
//            String key = (String) iter.next();
//
//            System.out.println(key);
//        }
//
//        db.close();
//
//    }
//
//    private static void put(){
//        File file = new File("/opt/data/a.db");
//        DB db = DBMaker.fileDB(file)
//                .fileMmapEnable()
//                .make();
//        Map  map = db.hashMap("A");
//
//        for(int i=0;i<5;i++) {
//            System.out.println(db.hashMap("A"));
//
//            map.put("name"+i, "Young");
//
//        }
//        db.commit();
//        db.close();
//
//    }
//}
