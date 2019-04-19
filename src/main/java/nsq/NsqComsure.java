package nsq;

import com.github.brainlag.nsq.NSQConsumer;
import com.github.brainlag.nsq.NSQMessage;
import com.github.brainlag.nsq.callbacks.NSQMessageCallback;
import com.github.brainlag.nsq.lookup.DefaultNSQLookup;
import com.github.brainlag.nsq.lookup.NSQLookup;
import org.junit.Test;

import java.io.IOException;
import java.util.UUID;

public class NsqComsure {
    public static String nsqAddr = "";
    public static int nsqPort = 3150;

    /**
     * 临时管道标记"#ephemeral"
     */
    @Test

    public void comsume(){
        NSQLookup lookup = new DefaultNSQLookup();
        for(;;){
            lookup.addLookupAddress(nsqAddr, 3161);
            NSQConsumer consumerCmdInstance1 = new NSQConsumer(lookup, "xctrl_monitor.send.task", "test"+ UUID.randomUUID().toString()+"#ephemeral", new NSQMessageCallback() {

                @Override
                public void message(NSQMessage message) {
//                byte[] b = message.getMessage();
//                ThreadTest.check(b);
                    try {
                        Thread.sleep(30);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    message.finished();
                }
            });
            consumerCmdInstance1.start();
                    try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
            try {
                consumerCmdInstance1.close();
                consumerCmdInstance1.shutdown();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

//        NSQConsumer consumerCmdInstance = new NSQConsumer(lookup, "test", "1",  new NSQMessageCallback(){
//
//            @Override
//            public void message(NSQMessage message) {
//                byte b[] = message.getMessage();
//                String s = new String(b);
//                System.out.println("B"+s);
//                message.finished();
//            }
//        });
//        consumerCmdInstance.start();
//
//        //线程睡眠，让程序执行完
//        try {
//            Thread.sleep(400000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
}
