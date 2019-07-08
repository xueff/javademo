package remoting;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import java.util.Date;
import java.util.List;

//@ProtobufClass   //自动匹配类型
//必须public
public class JProtoModel{

    @Protobuf(fieldType = FieldType.STRING, order = 1, required = true)
    private String username = "";
    @Protobuf(fieldType = FieldType.INT64, order = 2, required = true)
    private long timeStamp = new Date().getTime();
    @Protobuf(fieldType = FieldType.STRING, order = 3, required = true)
    private String id = "BGY";
    @Protobuf(fieldType = FieldType.STRING, order = 4, required = true)
    private String grant_type = "password";


    @Protobuf(fieldType = FieldType.OBJECT, order=5, required = false)
    public JProtoPerson person;

    @Protobuf(fieldType = FieldType.OBJECT, order=6, required = false)
    public List<JProtoPerson> personList;



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGrant_type() {
        return grant_type;
    }

    public void setGrant_type(String grant_type) {
        this.grant_type = grant_type;
    }

    public JProtoModel() {

    }
}