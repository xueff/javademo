package nsq;

import com.github.brainlag.nsq.NSQConsumer;
import com.github.brainlag.nsq.NSQMessage;
import com.github.brainlag.nsq.callbacks.NSQMessageCallback;
import com.github.brainlag.nsq.lookup.DefaultNSQLookup;
import com.github.brainlag.nsq.lookup.NSQLookup;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class NsqComsure {
    public static String nsqAddr = "";
    public static Map<String,NSQConsumer> consumerMap = new ConcurrentHashMap<>();

    /**
     * 临时管道标记"#ephemeral"
     */
    @Test
    public void comsume(){
        NSQLookup lookup = new DefaultNSQLookup();
            buildComsume(lookup, "123321", "test"+ UUID.randomUUID().toString()+"#ephemeral",new NSQMessageCallback(){
                        @Override
                        public void message(NSQMessage message) {
                            byte[] bytes =  message.getMessage();
                            System.out.println(new String(bytes));
                            throw new RuntimeException("不收");
                        }
            });
        try {
            Thread.sleep(500000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String buildComsume(NSQLookup lookup, final String topic, final String channel, final NSQMessageCallback callback){
        lookup.addLookupAddress("39.106.196.5",3161);
        NSQConsumer consumerCmdInstance1 = new NSQConsumer(lookup, topic, "test", new NSQMessageCallback() {

            @Override
            public void message(NSQMessage message) {

                try {
                    callback.message(message);
                    message.finished();
//                    message.touch();
                } catch (Exception e) {
                    message.requeue(); //还回
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
            }
        });
        consumerCmdInstance1.start();
        consumerMap.put(channel,consumerCmdInstance1);
        return channel;
    }
}
