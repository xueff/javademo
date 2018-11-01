package vertx;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import utils.CommonUtils;

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
        router.post("/hander/*").handler(it->new Filter().handler(it));
        router.post("/hander/it").handler(it -> new Handle().handler(it) );
        router.get("/*").handler(it -> new Handle().handler(it) );
        server.requestHandler(it -> router.accept(it) ).listen(8080);
    }
}

class Filter{
    static ExecutorService cachedThreadPool
            = Executors.newCachedThreadPool();

    public void handler(RoutingContext context) {
        //String authorization = context.request().getHeader("Authorization");
        //TODO

        System.out.println("filter");
        send("====");
       context.next();
    }

    public void send(String json){
        cachedThreadPool.execute(new Runnable() {

            @Override
            public void run() {
                try {
                    System.out.println("one-start");
                    Thread.sleep(5000);
                    System.out.println("one-end");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("one go");
            }
        });
    }
}

class Handle{
    public void handler(RoutingContext context) {
        //String authorization = context.request().getHeader("Authorization");
        //TODO
        System.out.println("end");
        context.response().end("end", "UTF-8");
    }
}
