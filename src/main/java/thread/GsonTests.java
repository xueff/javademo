package thread;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.junit.Test;
import java.io.IOException;
import java.util.*;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class GsonTests {
    @Test
    public void jsonStringToModel() throws IOException {
        JsonObject returnData = new JsonParser().parse("jsonString").getAsJsonObject();
        Object model = new Gson().fromJson(returnData,Object.class);
    }

    @Test
    public void modeltoJsonString() throws IOException {
        Gson gson = new Gson();
        Object student1 = new Object();
        System.out.println("----------对象的转化json字符串-------------");
        // 简单的bean转为json
        String s1 = gson.toJson(student1);
        System.out.println("简单Bean转化为Json===" + s1);
    }

    @Test
    public void jsonStringTocomplexmodel() throws IOException {
        Gson gson = new Gson();
        Object student3 = new Object();
        List<Object> list = new ArrayList<Object>();
        list.add(student3);
        list.add(student3);

        System.out.println("----------jsonString转带泛型的List-------------");
        // 带泛型的list转化为json
        String s2 = gson.toJson(list);
        System.out.println("带泛型的list转化为json==" + s2);

        // json转为带泛型的list
        List<Object> retList = gson.fromJson(s2,
                new TypeToken<List<Object>>() {
                }.getType());
    }

    @Test
    public void stringToMap(){

        String jsonString = "";
        Gson gson = new Gson();
        Map<String, Object> map = new HashMap<String, Object>();
        map = gson.fromJson(jsonString, map.getClass());
        String goodsid=(String) map.get("goods_id");
        System.out.println("map的值为:"+goodsid);
    }

    @Test
    public void createJson(){
        JsonObject jsonObj = new JsonObject();
        jsonObj.addProperty("action","tvyuyin.channeldataupdate");
        jsonObj.addProperty("timestamp",new java.util.Date().getTime());
        jsonObj.addProperty("Key",UUID.randomUUID()+"");
        JsonObject jsonObj2 = new JsonObject();
        jsonObj2.addProperty("child","child");
        jsonObj.add("data",jsonObj2);

        System.out.println(new Gson().toJson(jsonObj));
    }


    @Test
    public void test(){
        Set<String> gateMessage = new HashSet<String>();
        gateMessage.add("as");
        gateMessage.add("as1");
        String[] sy =  gateMessage.toArray(new String[gateMessage.size()]);
        System.out.println(Arrays.toString(sy));

        gateMessage = new HashSet<String>();

        sy = (String[]) gateMessage.toArray();
        System.out.println(Arrays.toString(sy));
    }

    @Test
    public void  getKey(){
        JsonObject jsonObj = new JsonObject();
        com.google.gson.JsonObject info = jsonObj.getAsJsonObject("props");

    }


}
