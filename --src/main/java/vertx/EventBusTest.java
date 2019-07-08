package vertx;

import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.MessageConsumer;

/**
 * @author xuefei
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/10/2910:04
 */
public class EventBusTest {
    public void testEventBus(){
        Vertx vertx = VertxHttp.getVertx();
        EventBus eb = vertx.eventBus();
        //方法A
        eb.consumer("news.uk.sport", message -> {
            System.out.println("I have received a message: " + message.body());
        });

        //方法B
        MessageConsumer<String> consumer = eb.consumer("news.uk.sport");
        consumer.handler(message
                -> {
            System.out.println("I have received a message: " + message.body());
        });
    }
}
