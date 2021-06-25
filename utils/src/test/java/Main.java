
//集合的交集并集差集，首先建立三个集合，有两个集合从来输入保存数字，一个用来保存结果输出。
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {

    public static void main(String[] args) {

        Set<Integer>list1= new HashSet<>();
        for (int i = 0; i<15 ; i++) {
            list1.add(i);
        }

        Set<Integer>list2= new HashSet<>();
        for (int j = 10; j <20 ; j++) {
            list2.add(j);
        }

        Set<Integer>result= new HashSet<>();

        result.addAll(list1);
        //向空集合result添加集合list1的全部数据
        result.retainAll(list2);
        //此时result集合含有list1的全部数据，仅保留集合result同时在list2同时存在的数据。
        System.out.println("交集为："+result);


        result.clear();
        result.addAll(list1);
        result.addAll(list2);
        //向空集合result添加集合list2的全部数据,由于Hashset集合里面的几何元素是不会重复，所以并集就出来了。
        System.out.println("并集为："+result);

        result.clear();
        result.addAll(list1);
        result.removeAll(list2);
        //此时result集合含有list1的全部数据，从result集合中移除同时包含在list2集合中相同的元素。
        System.out.println("list1-list2的差集为："+result);


        result.clear();
        result.addAll(list2);
        //向空集合result添加集合list2的全部数据
        result.removeAll(list1);
        //此时result集合含有list2的全部数据，从result集合中移除同时包含在list1集合中相同的元素。
        System.out.println("list2-list1的差集为："+result);
    }
}

