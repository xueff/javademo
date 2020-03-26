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
        Map<String,String> header = new HashMap<>();
        header.put("token","KKXnZ13f0Gd1mv1wAEQbIWf4db8c06GmnuxXFQ5hkFMox6x05DnLlDVu-am89W2RAMOProY1WPMTz8TihkbP2g");
        Map<String,String> map = new HashMap<>();
        map.put("domainId",4+"");
        while (true) {
            for(int i=0;i<=2;i++) {
                try {
                    map.put("processorId", i+"");
                    System.out.println(i);
//                    System.out.println(HttpsUtils.post("https://172.16.0.33:8084/devops/setDomain",header, map,null));
                    System.out.println(HttpsUtils.postForm("https://localhost:8084/devops/setDomain", map));
                    Thread.sleep(5000);
                }catch (Exception e){
                    System.err.println(i);
                }
            }
        }
    }

}
