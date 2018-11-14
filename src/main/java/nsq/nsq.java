package nsq;

import com.github.brainlag.nsq.NSQConsumer;
import com.github.brainlag.nsq.NSQMessage;
import com.github.brainlag.nsq.NSQProducer;
import com.github.brainlag.nsq.callbacks.NSQMessageCallback;
import com.github.brainlag.nsq.exceptions.NSQException;
import com.github.brainlag.nsq.lookup.DefaultNSQLookup;
import com.github.brainlag.nsq.lookup.NSQLookup;
import io.vertx.core.json.JsonObject;
import org.apache.commons.pool2.impl.GenericKeyedObjectPoolConfig;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

/**
 * @author xuefei
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/11/69:23
 */
public class nsq {

    public static String nsqAddr = "39.106.196.5";
    public static int nsqPort = 3150;

    @Test
    public void producer(){
        EventModel e = new EventModel();


        GenericKeyedObjectPoolConfig config = new GenericKeyedObjectPoolConfig();
            config.setMinIdlePerKey(1);
            config.setMaxTotalPerKey(20);
            config.setMaxWaitMillis(30000);
        NSQProducer producer = new NSQProducer().setPoolConfig(config)
                    .addAddress(nsqAddr, nsqPort)
                    .start();
        try {
             for(int i = 0;i<100;i++) {
                Map cmd = new HashMap<>();
                cmd.put("UseACPower",499+i*10+"KW");
                e.setCmd(cmd);
                producer.produce("CountryGarden.PowerSocket.Electricity", JsonObject.mapFrom(e).toString().getBytes());
            }
        } catch (NSQException e1) {
            e1.printStackTrace();
        } catch (TimeoutException e1) {
            e1.printStackTrace();
        }
    }

    @Test
    public void comsume(){
        NSQLookup lookup = new DefaultNSQLookup();
        lookup.addLookupAddress("192.168.121.107", 4161);
        NSQConsumer consumerCmdInstance = new NSQConsumer(lookup, "CoreToDc.bgy.test", UUID.randomUUID().toString(),  new NSQMessageCallback(){

            @Override
            public void message(NSQMessage message) {
                byte b[] = message.getMessage();
                String s = new String(b);
                System.out.println(s);
                message.finished();
            }
        });
        consumerCmdInstance.start();

        //线程睡眠，让程序执行完
        try {
            Thread.sleep(40000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class EventModel {
    //设备地址
    private String deviceId="1";
    //网关地址
    private String devHost="28f366b6dc86";
    //设备类型
    private String deviceType="3";
    //事件名称
    private String event="4";
    //消息id
    private String msgId="5";
    //版本号
    private String version="6";
    //翻译器名
    private String transName="5";
    //下发管道名称
    private String channelName="6";
    //生产商名称
    private String factory="6";
    //消息实体
    private Object cmd = null;
    //额外属性
    private Map<String, Object> props = new HashMap<String,Object>();

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDevHost() {
        return devHost;
    }

    public void setDevHost(String devHost) {
        this.devHost = devHost;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getTransName() {
        return transName;
    }

    public void setTransName(String transName) {
        this.transName = transName;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public Object getCmd() {
        return cmd;
    }

    public void setCmd(Object cmd) {
        this.cmd = cmd;
    }

    public Map<String, Object> getProps() {
        return props;
    }

    public void setProps(Map<String, Object> props) {
        this.props = props;
    }
}


