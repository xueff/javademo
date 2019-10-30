package vertx;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import javabase.ramdon.RamdonStudy;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author xuefei
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/10/2214:59
 */
public class VertxHttp {
    public static Vertx getVertx(){
        return Vertx.vertx();
    }

    public static void main(String[] args) {

        Vertx vertx = Vertx.vertx();
        HttpServer server = vertx.createHttpServer();
        if(server == vertx.createHttpServer()){//不同
            System.out.println("相同。");
        }
        Router router = Router.router(vertx);

        router.route().handler(BodyHandler.create());//body
        //router.post("/hander/*").handler(it->new Filter().handler(it));
        router.get("/*").handler(it->new Filter().handler(it));
        router.post("/hander/it").handler(it -> new Handle().handler(it) );
        router.get("/file/*").handler(it -> new FileHandle().handler(it) );
        server.requestHandler(it -> router.accept(it) ).listen(9090);
    }
}

class Filter{
    static ExecutorService cachedThreadPool
            = Executors.newCachedThreadPool();

    public void handler(RoutingContext context) {
        System.out.println("filter");
        send("====");
       context.next();
    }

    public void send(String json){
        cachedThreadPool.execute(new Runnable() {

            @Override
            public void run() {

                System.out.println("one go");
            }
        });
    }
}

class Handle{
    public void handler(RoutingContext context) {
        System.out.println("end2");
        //context.response().end("end", "UTF-8");
//        String file = "";
//        if (context.request().path().equals("/")) {
//            file = "index.html";
//        } else if (!context.request().path().contains("..")) {
//            file = context.request().path();
//        }
//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        String f = "kasdcashioobjbjhcuioashcuiosajkb";
//        for(int a= 0;a<10;a++){
//            f +=f;
//        }
//        context.response().putHeader("1",f);
        context.response().end("ok2");
    }

}

class FileHandle {
    public void handler(RoutingContext context) {
        String file = "";
        if (context.request().path().equals("/")) {
            file = "index.html";
        } else if (!context.request().path().contains("..")) {
            file = context.request().path();
        }
        context.response().sendFile("D:/apache-tomcat-7.0.57-windows-x64.zip");
    }
}
