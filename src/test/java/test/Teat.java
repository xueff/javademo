package test;

import common.bean.Person;
import ramdon.RamdonStudy;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xuefei
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/10/239:15
 */
public class Teat {
    public static void main(String[] args) {
        List<Person> pl = new ArrayList<>();
        for(int i=0;i<5;i++){
            Person p = new Person();
            p.setName("A"+i);
            p.setAge(RamdonStudy.getRamdonInt(10));
            pl.add(p);
        }
    }
}
