package remoting;

import com.baidu.bjf.remoting.protobuf.Codec;
import com.baidu.bjf.remoting.protobuf.ProtobufProxy;
import org.junit.Test;
import utils.CommonUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xuefei
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/10/2215:38
 */
public class JProtobuf {
    //list类型
    @Test
    public void serial(){
        Codec<JProtoModel> codec = ProtobufProxy.create(JProtoModel.class);
        JProtoModel test = new JProtoModel();
        test.setUsername("xming");
        JProtoPerson p =new JProtoPerson();
        List<JProtoPerson> pl =new ArrayList<JProtoPerson>();
        p.name = "大";
        test.person = new JProtoPerson();
        pl.add(p);
        test.personList =pl;

        try {
            byte[] byteArray = codec.encode(test);

            JProtoModel newStt = codec.decode(byteArray);
            CommonUtils.printAsJson(newStt);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
