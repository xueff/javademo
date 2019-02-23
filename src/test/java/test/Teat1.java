package test;

import org.junit.Test;
import utils.NumberUtils;

import java.util.*;

/**
 * @author xuefei
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/10/239:15
 */
public class Teat1 {
    public static void main(String[] args) {
//        Set<Object> set = new HashSet<>();
//        set.add("1");
//        set.add(1);
//        List<Object> list = new ArrayList<Object>(set);
//       Arrays.copyOf(set,10,Object[].class)
        UUID.randomUUID().toString();

    }

    @Test public void getMax(){
        Map<String,Integer> fbu = new HashMap<>();
        String test = "";
        String[] array = test.split("=");
        for(int i=0;i<array.length;i++){
            if(fbu.containsKey(array[i])){
                fbu.put(array[i], fbu.get(array[i])+1);
            }else{
                fbu.put(array[i], 1);
            }
        }
        System.out.println(fbu);
    }

}

