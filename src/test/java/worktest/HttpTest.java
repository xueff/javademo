package worktest;

import io.vertx.core.json.JsonObject;
import net.http.httpclient.HttpsUtils;
import org.junit.Test;

public class HttpTest {

    @Test
    public void send() throws InterruptedException {
        for(int i=0;i<1000;i++) {
            String res =  HttpsUtils.postJson("http://localhost:9090/host/getDcAddress", new JsonObject().put("userKey", "xcc").put("hostMac",i+"").toString());
            System.out.println(res);
            Thread.sleep(1000);
        }
    }

}
