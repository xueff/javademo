package nsq;

import com.github.brainlag.nsq.NSQConsumer;
import com.github.brainlag.nsq.NSQMessage;
import com.github.brainlag.nsq.callbacks.NSQMessageCallback;
import com.github.brainlag.nsq.lookup.DefaultNSQLookup;
import com.github.brainlag.nsq.lookup.NSQLookup;
import javabase.thread.threadtest.ThreadTest;
import org.junit.Test;

public class NsqComsure {
    public static String nsqAddr = "192.168.2.206";
    public static int nsqPort = 4150;
    @Test
    public void comsume(){
        NSQLookup lookup = new DefaultNSQLookup();
        lookup.addLookupAddress(nsqAddr, 4161);
        NSQConsumer consumerCmdInstance1 = new NSQConsumer(lookup, "xfTest2", "1",  new NSQMessageCallback(){

            @Override
            public void message(NSQMessage message) {
                byte[] b = message.getMessage();
                ThreadTest.check(b);
                message.finished();
            }
        });
        consumerCmdInstance1.start();

        NSQConsumer consumerCmdInstance = new NSQConsumer(lookup, "test", "1",  new NSQMessageCallback(){

            @Override
            public void message(NSQMessage message) {
                byte b[] = message.getMessage();
                String s = new String(b);
                System.out.println("B"+s);
                message.finished();
            }
        });
        consumerCmdInstance.start();

        //线程睡眠，让程序执行完
        try {
            Thread.sleep(400000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
