package java8.lambdas;

import common.bean.Person;
import data.common.CommonData;
import org.junit.Test;
import utils.CommonUtils;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Comparator.comparingDouble;
import static java.util.Comparator.comparingInt;

/**
 * @author xuefei
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/10/1911:27
 */
public class Streams {
    /**
     *filter:筛选行
     * map：筛选列
     * sorted
     * limit
     * collect
     * @return
     */
    @Test
    public List<Integer> getPersonListAsAgeList(List<Person> list){
        List<Integer> ids = list.stream()
                .filter(inv -> inv.getAge() > 10)
                //----sort
                .sorted(comparingInt(Person::getAge).reversed())
                .sorted((p1, p2) -> Integer.valueOf(p1.getAge())
                        .compareTo(Integer.valueOf(p2.getAge())))
                .map(Person::getAge)
                .limit(10)
                .collect(Collectors.toList());
        return  ids;
    }

    public static void main(String[] args) {
        List<Person> list = CommonData.getPersonList(20);
        CommonUtils.printAsJson("start:",list,null);
        Streams s = new Streams();

        List<Integer> list2 = s.getPersonListAsAgeList(list);




        CommonUtils.printAsJson("end:",list,null);
    }
}
