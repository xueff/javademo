package nsq;

import com.github.brainlag.nsq.NSQProducer;
import org.apache.commons.pool2.impl.GenericKeyedObjectPoolConfig;

/**
 * @author xuefei
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/11/69:23
 */
public class NsqClient {

    public static String nsqAddr = "192.168.2.206";
    public static int nsqPort = 4150;
    static NSQProducer producer = null;

//    private NsqClient(){}
    public static NSQProducer getInstance(){
        if(producer == null) {
            synchronized (NsqClient.class) {
                if(producer == null) {
                    GenericKeyedObjectPoolConfig config = new GenericKeyedObjectPoolConfig();
                    config.setMinIdlePerKey(5);
                    config.setMaxTotalPerKey(50);
                    config.setMaxWaitMillis(6000);
                    producer = new NSQProducer().setPoolConfig(config)
                            .addAddress(nsqAddr, nsqPort)
                            .start();
                }
            }
        }
        return producer;
    }


}




