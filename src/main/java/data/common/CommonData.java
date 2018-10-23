package data.common;

import common.bean.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xuefei
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/10/1911:33
 */
public class CommonData {

    public static List<Person> getPersonList(int size){
        List<Person> list = new ArrayList<Person>();
        for(int i = 0;i<size;i++){
            Person p = new Person();
            list.add(p);
        }
        return list;
    }

}
