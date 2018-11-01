package vertx;

import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import org.junit.Test;

/**
 * @author xuefei
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/10/299:51
 */
public class VertxTimer {
    @Test
    public void timerTest(){
        Vertx vertx = VertxHttp.getVertx();
        System.out.println("st:"+System.currentTimeMillis());
        vertx.setTimer(1000,new Handler<Long>(){

            @Override
            public void handle(Long event) {
                System.out.println("delayend:"+System.currentTimeMillis());
            }
        });
        System.out.println("mainend:"+System.currentTimeMillis());


        try {
            Thread.sleep(10000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
