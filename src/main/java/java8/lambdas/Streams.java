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

    public void streams(){
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        //1...10
//        Comsume ->只接受数据，不返回（消费数据）
        //Supplier -> 没有结束数据（生产者）
        //Predicate ->判断（1..10选偶数）
        //Function->Integer ->Integer*2
        list.stream()
                .filter(value->value %2 == 0)  //过滤选出偶数
                .map(value -> value*2)      //乘以2
                ;

    }

    public void flatMap(){
        List<String> list = Arrays.asList("1,2,3","4,5");

        list.stream()
                .map(value->value.split(","))  //一维变二维
                .flatMap(values -> Arrays.stream(values)) //二维变一维
                .forEach(System.out::println);

    }
}
