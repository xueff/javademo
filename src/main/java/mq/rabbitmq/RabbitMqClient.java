package mq.rabbitmq;

import com.rabbitmq.client.Channel;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.rabbitmq.RabbitMQClient;
import io.vertx.rabbitmq.RabbitMQOptions;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;

public class RabbitMqClient {
    protected static RabbitMQClient client;
    protected Channel channel;
    protected Vertx vertx;
    public static RabbitMQClient getClient(){
        if(client == null) {
            RabbitMQOptions config = new RabbitMQOptions();
            config.setUser("admin");
            config.setPassword("admin");
            config.setHost("192.168.2.42");
            config.setPort(5672);
//            config.setVirtualHost("");
            config.setConnectionTimeout(6000); // in milliseconds
            config.setRequestedHeartbeat(60); // in seconds
            config.setHandshakeTimeout(6000); // in milliseconds
            config.setRequestedChannelMax(5);
            config.setNetworkRecoveryInterval(500); // in milliseconds
            config.setAutomaticRecoveryEnabled(true);
            //集群
            //config.setAddresses(Arrays.asList(Address.parseAddresses("firstHost,secondHost:5672")));

            client = RabbitMQClient.create(Vertx.vertx(), config);
            CompletableFuture<Void> latch = new CompletableFuture<>();
            client.start(ar -> {
                if (ar.succeeded()) {
                    latch.complete(null);
                } else {
                    latch.completeExceptionally(ar.cause());
                }
            });
        }
        return client;
    }

    public static void main(String[] args)  throws InterruptedException {
        JsonObject config = new JsonObject();

        config.put("x-dead-letter-exchange", "my.deadletter.exchange");
        config.put("alternate-exchange", "my.alternate.exchange");
// ...
        RabbitMqClient.getClient().exchangeDeclare("fanout.test", "fanout", true, false, config, onResult -> {
            if (onResult.succeeded()) {
                System.out.println("Exchange successfully declared with config");
            } else {
                onResult.cause().printStackTrace();
            }
        });
        Thread.sleep(10000);
    }
}

