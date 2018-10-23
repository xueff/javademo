package vertx;

/**
 * 设备在线状态模型
 */
class DeviceOnlineStatusModel{
    //上报消息类型
    private int pushMsgType;
    //设备类型
    private String pushMsg;
    //主机MAC
    private String pushCcuid;
    //设备ID
    private String devNo;
    //消息实体
    private String msg;
    //状态
    private String status;

    public DeviceOnlineStatusModel(int pushMsgType,String devNo) {

        this.pushMsgType = pushMsgType;
        this.pushMsg = pushMsgType+"";
        this.pushCcuid =  pushMsgType+"";
        this.devNo =  devNo+"";
        this.msg =  pushMsgType+"";
        this.status =  pushMsgType+"";
    }

    public int getPushMsgType() {
        return pushMsgType;
    }

    public void setPushMsgType(int pushMsgType) {
        this.pushMsgType = pushMsgType;
    }

    public String getPushMsg() {
        return pushMsg;
    }

    public void setPushMsg(String pushMsg) {
        this.pushMsg = pushMsg;
    }

    public String getPushCcuid() {
        return pushCcuid;
    }

    public void setPushCcuid(String pushCcuid) {
        this.pushCcuid = pushCcuid;
    }

    public String getDevNo() {
        return devNo;
    }

    public void setDevNo(String devNo) {
        this.devNo = devNo;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

