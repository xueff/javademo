package testmodel;

import java.util.HashMap;
import java.util.Map;

public class EventModel {
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