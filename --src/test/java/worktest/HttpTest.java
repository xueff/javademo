package worktest;

import io.vertx.core.json.JsonObject;
import net.http.httpclient.HttpClientUtilForm;
import net.http.httpclient.HttpsUtils;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class HttpTest {

    @Test
    public void send() throws InterruptedException {
        for(int i=0;i<1;i++) {
            Map<String,String> map = new HashMap<>();
            map.put("value","hh");
            Thread.sleep(100000);
        }
    }

}
