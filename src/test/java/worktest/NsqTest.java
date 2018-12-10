package worktest;

import com.github.brainlag.nsq.exceptions.NSQException;
import io.vertx.core.json.JsonObject;
import nsq.NsqClient;
import org.junit.Test;
import testmodel.EventModel;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

public class NsqTest {

    @Test
    public void testSend(){
        EventModel e = new EventModel();
        String[] items = new String[]{"Temperature","Humidity","PM25"};
        try {
            for(int i = 0;i<1;i++) {
                e.setDevHost("9998");
                Map cmd = new HashMap<>();
                cmd.put("UseACPower",0.9+i*0.5+"");
                long da = new Date().getTime()-1000000000+40000000*i;
                cmd.put("TimeStamp",da);
//                cmd.put(items[RamdonStudy.getRamdonInt(3)],RamdonStudy.getRamdonInt(3)+i*0.3+"");
//                cmd.put(items[RamdonStudy.getRamdonInt(3)],RamdonStudy.getRamdonInt(3)+i*0.3+"");

                e.setCmd(cmd);
                System.out.println(new Date(da));
                NsqClient.getInstance().produce("CountryGarden.PowerSocket.Electricitytest",JsonObject.mapFrom(e).toString().getBytes());
            }
        } catch (NSQException e1) {
            e1.printStackTrace();
        } catch (TimeoutException e1) {
            e1.printStackTrace();
        }
    }
}


