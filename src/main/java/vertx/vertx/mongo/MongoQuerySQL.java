package vertx.vertx.mongo;

/**
 * @author xuefei
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/11/1515:45
 */
public interface MongoQuerySQL {
    String IN = "$in";  // in == {a: {$in: [10, “hello”]}}
    String ALL = "$all";  // in contains {a: {$all: [10, “hello”]}}
    //String AB = "a.b";  // {"a.b": 10} 级联

}
