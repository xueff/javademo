package dataformat.json.gson;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import common.bean.Person;
import net.sf.json.JSONArray;
import org.junit.Test;

import java.lang.reflect.Type;
import java.util.*;

public class GsonTests {

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
    public void jsonArrayToModelList(){

        Person List1 = new Person();
        Person List2 = new Person();
        Person List3 = new Person();
        List<Person> list = new ArrayList<>();
        list.add(List1);
        list.add(List2);
        JSONArray jsonObj = JSONArray.fromObject(list);

        //jsonArray转modelList

        Type type = new TypeToken<ArrayList<Person>>() {}.getType();
        Gson gson = new Gson();
        ArrayList arrayList = gson.fromJson(jsonObj.toString(), type);
        System.out.println(arrayList);

    }

    public static void main(String[] args) {
        Gson gson = new Gson();

        Person student1 = new Person();
        student1.setName("hh");

        // //////////////////////////////////////////////////////////
        System.out.println("----------简单对象之间的转化-------------");
        // 简单的bean转为json
        String s1 = gson.toJson(student1);
        System.out.println("简单Bean转化为Json===" + s1);

        // json转为简单Bean
        Person student = gson.fromJson(s1, Person.class);
        System.out.println("Json转为简单Bean===" + student);
        // 结果:
        // 简单Bean转化为Json==={"id":1,"name":"李坤","birthDay":"Jun 22, 2012 8:27:52 AM"}
        // Json转为简单Bean===Student [birthDay=Fri Jun 22 08:27:52 CST 2012, id=1,
        // name=李坤]
        // //////////////////////////////////////////////////////////

        Person student2 = new Person();
        student2.setName("r");


        Person student3 = new Person();
        student3.setName("r");
        List<Person> list = new ArrayList<Person>();
        list.add(student1);
        list.add(student2);
        list.add(student3);

        System.out.println("----------带泛型的List之间的转化-------------");
        // 带泛型的list转化为json
        String s2 = gson.toJson(list);
        System.out.println("带泛型的list转化为json==" + s2);

        // json转为带泛型的list
        List<Person> retList = gson.fromJson(s2,
                new TypeToken<List<Person>>() {
                }.getType());
        for (Person stu : retList) {
            System.out.println(stu);
        }

        // 结果:
        // 带泛型的list转化为json==[{"id":1,"name":"李坤","birthDay":"Jun 22, 2012 8:28:52 AM"},{"id":2,"name":"曹贵生","birthDay":"Jun 22, 2012 8:28:52 AM"},{"id":3,"name":"柳波","birthDay":"Jun 22, 2012 8:28:52 AM"}]
        // Student [birthDay=Fri Jun 22 08:28:52 CST 2012, id=1, name=李坤]
        // Student [birthDay=Fri Jun 22 08:28:52 CST 2012, id=2, name=曹贵生]
        // Student [birthDay=Fri Jun 22 08:28:52 CST 2012, id=3, name=柳波]

    }

    @Test
    public void createJson(){
        JsonObject jsonObj = new JsonObject();
        jsonObj.addProperty("action","tvyuyin.channeldataupdate");
        jsonObj.addProperty("timestamp",new Date().getTime());
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
        JsonObject info = jsonObj.getAsJsonObject("props");

    }


}
