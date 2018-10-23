package vertx;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import utils.CommonUtils;

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
        Router router = Router.router(vertx);

        router.route().handler(BodyHandler.create());//body
        router.post("/hander/*").blockingHandler(it->new Filter().handler(it));
        router.post("/hander/it").blockingHandler(it -> new Handle().handler(it) );
        server.requestHandler(it -> router.accept(it) ).listen(8080);
    }
}

class Filter{
    public void handler(RoutingContext context) {
        //String authorization = context.request().getHeader("Authorization");
        //TODO
        System.out.println("filter");
       context.next();
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
