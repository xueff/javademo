package remoting;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

/**
 * @author xuefei
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/10/2216:02
 */
public class JProtoPerson {
    @Protobuf(fieldType = FieldType.STRING, order=1, required = true)
    public String name = "da";
    @Protobuf(fieldType = FieldType.INT32, order=2, required = true)
    public int id = 10;
    @Protobuf(fieldType = FieldType.STRING, order=3, required = false)
    public String email = "656";

    @Protobuf(fieldType = FieldType.DOUBLE, order=4, required = false)
    public Double doubleF = 1.0;


    @Protobuf(fieldType = FieldType.FLOAT, order=5, required = false)
    public Float floatF ;

    @Protobuf(fieldType = FieldType.BYTES, order=6, required = false)
    public byte[] bytesF;

    @Protobuf(fieldType=FieldType.BOOL, order=7, required=false)
    public Boolean boolF;
}
