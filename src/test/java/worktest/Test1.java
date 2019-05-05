package worktest;

import io.vertx.core.json.JsonObject;
import javabase.ramdon.RamdonStudy;
import net.http.httpclient.HttpsUtils;
import org.junit.Test;
import vertx.vertx.mongo.VertxMongo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Test1 {

    private static volatile String workerStart ="";

    public static synchronized String getAndUpdateWorkerStart(String workerId, List<String> list){
        String old = workerStart;
        int index = list.indexOf(workerId);
        int next = (index == (list.size()-1))?0:index+1;
        workerStart =  list.get(next);
        return workerStart;
    }


    public static void main(String[] args) {

           String it1 = "12a".getBytes().toString();
           String it2 = "a21".getBytes().toString();

           System.out.println(it1 + "   "+it2);


    }

}
