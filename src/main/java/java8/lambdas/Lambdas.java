package java8.lambdas;

import com.google.gson.JsonObject;
import common.bean.Person;
import data.common.CommonData;
import net.sf.json.JSONObject;
import utils.CommonUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author xuefei
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/10/1911:27
 */
public class Lambdas {

    //Sort
    public static void Sort(){
        List<Person> list= CommonData.getPersonList(5);
        CommonUtils.printAsJson("开始：",list,null);
//        Collections.sort( list, new Comparator<Person>() {
//            public int compare(Person p1, Person p2) {
//                return Double.compare(p1.getMoney(), p2.getMoney());
//                //return p1.getAge() - p2.getAge();
//            }
//        });


        list.sort((Person inv1, Person inv2)
                ->
//                        Integer.compare(inv2.getAge() - inv1.getAge())
                Double.compare(inv2.getMoney(), inv1.getMoney())

        );

        //list.sort((o1, o2) -> -o1.getAge().compareTo(o2.getAge()));
        //list.sort(comparingDouble(Person::getAmount).reversed());

        CommonUtils.printAsJson("结果：",list,null);
    }
}
