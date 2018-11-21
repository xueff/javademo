package nsq;

import com.github.brainlag.nsq.NSQConsumer;
import com.github.brainlag.nsq.NSQMessage;
import com.github.brainlag.nsq.NSQProducer;
import com.github.brainlag.nsq.callbacks.NSQMessageCallback;
import com.github.brainlag.nsq.exceptions.NSQException;
import com.github.brainlag.nsq.lookup.DefaultNSQLookup;
import com.github.brainlag.nsq.lookup.NSQLookup;
import io.vertx.core.json.JsonObject;
import javabase.ramdon.RamdonStudy;
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
            config.setMinIdlePerKey(100);
            config.setMaxTotalPerKey(2000);
            config.setMaxWaitMillis(300000000);
        NSQProducer producer = new NSQProducer().setPoolConfig(config)
                    .addAddress(nsqAddr, nsqPort)
                    .start();

        String[] items = new String[]{"Temperature","Humidity","PM25"};
        try {
             for(int i = 0;i<3;i++) {
                 e.setDevHost("999"+i);
                Map cmd = new HashMap<>();
                cmd.put("UseACPower",i*0.9+8+"");
//                cmd.put(items[RamdonStudy.getRamdonInt(3)],RamdonStudy.getRamdonInt(3)+i*0.3+"");
//                cmd.put(items[RamdonStudy.getRamdonInt(3)],RamdonStudy.getRamdonInt(3)+i*0.3+"");

//                 try {
//                     Thread.sleep(1000*5);
//                 } catch (InterruptedException e1) {
//                     e1.printStackTrace();
//                 }
                 e.setCmd(cmd);
//                producer.produce("CountryGarden.Environmenttest", JsonObject.mapFrom(e).toString().getBytes());
                producer.produce("CountryGarden.PowerSocket.Electricitytest", JsonObject.mapFrom(e).toString().getBytes());
            }
        } catch (NSQException e1) {
            e1.printStackTrace();
        } catch (TimeoutException e1) {
            e1.printStackTrace();
        }
        //线程睡眠，让程序执行完
        try {
            Thread.sleep(11140000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
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
    private String deviceId="213";
    //网关地址
    private String devHost="666";
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


